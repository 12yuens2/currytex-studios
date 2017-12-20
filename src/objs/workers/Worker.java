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

public class Worker {
	
	public static final int BASE_WAGE = 5;

	public final String name;
	public Addiction addictionLevel;
	
	public HashMap<Skill, Level> skills;
	public StatLevel moreMoney, moreReputation; //TODO better names
	
	public int wage; /* How much this worker gets paid per hour */
	public int salary; /* How much money this worker is owed */

	
	public int stressPercent;
	
	public float workTimerStart;
	public float workTimer;
	
	public Activity currentActivity;
	
	public Worker(String name) {
		this.name = name;
		this.addictionLevel = Addiction.NONE;
		this.salary = 0;
		this.stressPercent = 0;
		this.workTimer = 0;
		this.workTimerStart = 1;
		
		this.skills = new HashMap<>();
		this.moreMoney = new StatLevel();
		this.moreReputation = new StatLevel();
		
		calculateWage();
	}
	
	public Worker(String name, int moreMoneyLevel, int moreReputationLevel, Addiction addictionLevel, HashMap<Skill, Level> skills) {
		this(name);
		this.addictionLevel = addictionLevel;
		this.moreMoney.level = moreMoneyLevel;
		this.moreReputation.level = moreReputationLevel;
		
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
	
	public Optional<GameState> integrate(GameState currentState) {
		if (currentActivity != null) {
			workTimer -= GameTime.HOURS_PER_TIMESTEP;
			
			if (currentActivity instanceof ProjectActivity && addictionLevel != Addiction.NONE) {
				chanceToDrinkCoffee(currentState.context.studio);
			}
			
			
			if (workTimer <= 0) {
				Optional<GameState> nextState = currentActivity.finish(this, currentState);
				currentActivity = null;
				return nextState;
			}
		}
		
		return Optional.empty();
	}
	
	private void chanceToDrinkCoffee(Studio studio) {
		int chance = 0;
		
		switch (addictionLevel) {
			case ADDICTED:
				chance = 100;
				break;
				
			case FREQUENT:
				chance = 150;
				break;
				
			case SOMETIMES:
				chance = 250;
				break;
		}
		
		Random random = new Random();
		if (random.nextInt(chance) == 0) {
			if (studio.coffee > 0) {
				studio.coffee--;
			}
			else {
				workTimer += 1;
				addStress(1);
			}
		}		
	}

	public void addMoreMoneyLevel() {
		moreMoney.addProgress();
		calculateWage();
	}
	
	public void addMoreReputationLevel() {
		moreReputation.addProgress();
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
	
	
	public HashMap<Skill, Level> getSkills() {
		LinkedHashMap<Skill, Level> sortedSkills = skills.entrySet()
												.stream()
												.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
												.collect(Collectors.toMap(
														Map.Entry::getKey, Map.Entry::getValue, 
														(e1, e2) -> e1,
														LinkedHashMap::new));
	
		return sortedSkills.entrySet().stream()
				.limit(2)
				.collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
	}
	
	public void updateSkills(Skill skill, int expGain) {
		if (skills.containsKey(skill)) {
			skills.get(skill).gainExp(expGain);
		} 
		else {
			skills.put(skill, new Level());						
		}
		calculateWage();
	}
	
	private void calculateWage() {
		wage = BASE_WAGE + (moreMoney.level * 3)
						 + (moreReputation.level * 2);
		
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

	
	public ArrayList<String> getProperties() {
		ArrayList<String> workerProperties = new ArrayList<String>();
		workerProperties.add("Name: " + name);

		//TODO naming more more/reputation level
		workerProperties.add("More Money Level: " + moreMoney.level);
		workerProperties.add("More Reputation Level: " + moreReputation.level);
		workerProperties.add("Caffine addiction: " + addictionLevel.toString());
		workerProperties.add("Stress: " + stressPercent + "%");
		workerProperties.add("Wage: " + wage);
		
		return workerProperties;
	}

	public void resetActivity() {
		currentActivity = null;
		workTimer = 0;		
	}
}
