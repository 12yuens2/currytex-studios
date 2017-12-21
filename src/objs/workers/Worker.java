package objs.workers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Random;

import game.DrawEngine;
import game.GameTime;
import game.states.GameState;
import objs.Studio;
import objs.activities.Activity;
import objs.activities.impl.ProjectActivity;
import processing.core.PConstants;
import processing.core.PVector;
import ui.locations.Location;
import ui.menus.impl.WorkerMenu;


/**
 * Class that represents a worker in the game.
 *
 */
public class Worker {
	
	public static final int BASE_WAGE = 5;

	public final String name;
	public Addiction addictionLevel;
	
	public HashMap<Skill, Level> skills;
	public StatLevel entrepreneur, fame;
	
	public Studio studio;
	
	public int wage; /* How much this worker gets paid per hour */
	public int salary; /* How much money this worker is owed */
	
	public int stressPercent;
	
	public float workTimerStart;
	public float workTimer;
	
	public boolean canWork;
	
	public Activity currentActivity;
	
	public Worker(String name, Studio studio) {
		this.name = name;
		this.studio = studio;
		this.addictionLevel = Addiction.NONE;
		this.salary = 0;
		this.stressPercent = 0;
		this.canWork = true;
		this.workTimer = 0;
		this.workTimerStart = 1;
		
		this.skills = new HashMap<>();
		this.entrepreneur = new StatLevel();
		this.fame = new StatLevel();
		
		calculateWage();
	}
	
	public Worker(String name, Studio studio, int moreMoneyLevel, int moreReputationLevel, 
			Addiction addictionLevel, HashMap<Skill, Level> skills) {
		this(name, studio);
		
		this.addictionLevel = addictionLevel;
		this.entrepreneur.level = moreMoneyLevel;
		this.fame.level = moreReputationLevel;
		
		this.skills = skills;
		
		calculateWage();
	}
	
	
	
	public void startNewActivity(Location location) {
		Activity activity = location.getActivity();
		if (activity.canStart(this)) {
			location.addWorker(this);
			currentActivity = activity.start(this);
		}
	}
	
	/**
	 * Update step for this worker. 
	 * @param currentState
	 * @return - May return a game state if an activity triggers a new game state.
	 */
	public Optional<GameState> integrate(GameState currentState) {
		if (currentActivity != null) {
			/* Reduce work timer */
			work(GameTime.HOURS_PER_TIMESTEP);
			
			/* Drink coffee if working on project */
			if (currentActivity instanceof ProjectActivity && addictionLevel != Addiction.NONE) {
				chanceToDrinkCoffee(currentState.context.studio);
			}
			
			/* Finish current activity */
			if (workTimer <= 0) {
				Optional<GameState> nextState = currentActivity.finish(this, currentState);
				currentActivity = null;
				return nextState;
			}
		}
		
		return Optional.empty();
	}
	
	private void chanceToDrinkCoffee(Studio studio) {
		int chance = addictionLevel.drinkChance();
		
		Random random = new Random();
		if (random.nextInt(chance) == 0) {
			if (studio.coffee > 0) {
				/* Drink coffee */
				canWork = true;
				studio.coffee--;
				work(1);
			}
			else {
				/* No more coffee */
				if (random.nextInt(addictionLevel.stallChance()) == 0) {
					canWork = false;
				}
				work(-1);
				addStress(1);
			}
		}		
	}

	/**
	 * Add progress to the entrepreneur stat and recalulate wages.
	 */
	public void addMoreMoneyLevel() {
		entrepreneur.addProgress();
		calculateWage();
	}
	
	/**
	 * Add progress to the fame stat and recalulate wages.
	 */
	public void addMoreReputationLevel() {
		fame.addProgress();
		calculateWage();
	}
	
	public void addStress(int amount) {
		stressPercent = Math.min(100, stressPercent + amount);
	}
	
	public void loseStress(int amount) {
		stressPercent = Math.max(0, stressPercent - amount);
	}
	
	public void setWorkTimer(float time) {
		/* More time needed based on stress */
		time += time * stressPercent/80f;
		
		workTimer = time;
		workTimerStart = time;
	}
	
	/**
	 * Reduce work timer and drink coffee if needed.
	 * The work timer will not reduce if canWork is false.
	 * @param amount to work. 
	 */
	public void work(float amount) {
		if (canWork) {	
			workTimer = Math.max(0, workTimer - amount);
		}
		else {
			
			/* Cannot continue to work automatically until the worker gets coffee */
			if (studio.coffee > 0) {
				canWork = true;
				studio.coffee--;
			}
		}
		
	}
	
	/**
	 * Get the 2 best skills for this worker which are the worker's active skills.
	 * @return
	 */
	public HashMap<Skill, Level> getSkills() {
		/* Sort the skills hashmap */
		LinkedHashMap<Skill, Level> sortedSkills = skills.entrySet()
												.stream()
												.sorted(Entry.comparingByValue(Collections.reverseOrder()))
												.collect(Collectors.toMap(
														Entry::getKey, Entry::getValue, 
														(e1, e2) -> e1,
														LinkedHashMap::new));
	
		/* Get the first two from the sorted hashmap */
		return sortedSkills.entrySet().stream()
				.limit(2)
				.collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
	}
	
	/**
	 * Update skills and recalculate wage. 
	 * @param skill
	 * @param expGain
	 */
	public void updateSkills(Skill skill, int expGain) {
		if (skills.containsKey(skill)) {
			skills.get(skill).gainExp(expGain);
		} 
		else {
			skills.put(skill, new Level());						
		}
		calculateWage();
	}
	
	/**
	 * Calculate the worker's wage based on skill and stat levels.
	 */
	private void calculateWage() {
		wage = BASE_WAGE + (entrepreneur.level * 3)
						 + (fame.level * 2);
		
		for (Entry<Skill, Level> entry : skills.entrySet()) {
			wage += 2.5 * entry.getValue().level;
		}
	}
	
	public void paySalary(Studio studio) {
		studio.currency -= salary;
		salary = 0;
	}
	
	public void updateSalary() {
		this.salary += wage;
	}

	public WorkerMenu getMenu() {
		return new WorkerMenu(this);
	}
	
	/**
	 * 
	 * @param drawEngine
	 * @param position
	 */
	public void menuDisplay(DrawEngine drawEngine, PVector position) {
		int xPos = (int) position.x;
		int yPos = (int) position.y;
		
		/* Draw properties */
		int propertiesYPos = yPos;
		for (String s : getProperties()) {
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, s,
					xPos, propertiesYPos, DrawEngine.BLACK);
			propertiesYPos += 30;
		}
		
		/* Draw skills */
		int skillsXPos = xPos + 300;
		int skillsYPos = yPos + 30;
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Skills", skillsXPos, yPos, DrawEngine.BLACK);
		for (Entry<Skill, Level> entry : getSkills().entrySet()) {
			/* Skill name */
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, entry.getKey() + ": " ,
					skillsXPos, skillsYPos, DrawEngine.BLACK);
			
			/* Skill level */
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, entry.getValue().level+",",
					skillsXPos + 75, skillsYPos, DrawEngine.BLACK);
			
			/* Skill exp to level */
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, entry.getValue().exp + "/" + entry.getValue().expToLevel,
					skillsXPos + 125, skillsYPos, DrawEngine.BLACK);
			
			skillsYPos += 30;
		}
	}

	/**
	 * Get properties of this worker.
	 * @return - List of properties. 
	 */
	public ArrayList<String> getProperties() {
		ArrayList<String> workerProperties = new ArrayList<String>();
		workerProperties.add("Name: " + name);

		workerProperties.add("Entreprenuer Level: " + entrepreneur.level);
		workerProperties.add("Fame Level: " + fame.level);
		workerProperties.add("Caffeine addiction: " + addictionLevel.toString());
		workerProperties.add("Stress: " + stressPercent + "%");
		workerProperties.add("Wage: " + wage);
		
		return workerProperties;
	}

	/* Reset this worker's activity */
	public void resetActivity() {
		currentActivity = null;
		workTimer = 0;		
	}


}
