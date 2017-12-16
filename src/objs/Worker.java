package objs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;

import game.GameTime;
import game.states.GameState;
import objs.activities.Activity;
import objs.activities.impl.ProjectActivity;
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
	
	public float workTimer;
	
	public Activity currentActivity;
	
	public Worker(String name) {
		this.name = name;
		this.addictionLevel = Addiction.NONE;
		this.salary = 0;
		this.stressPercent = 0;
		this.workTimer = 0;
		
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
			currentActivity = activity.start(this);
			location.addWorker(this);
		}
		
	}
	
	public Optional<GameState> integrate(GameState currentState) {
		if (currentActivity != null) {
			workTimer -= GameTime.MINUTES_PER_TIMESTEP;
			
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
				workTimer += 5;
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
	
	
	public void updateSkills(Skill skill, int expGain) {
		if (skills.containsKey(skill)) {
			skills.get(skill).gainExp(expGain);
		} 
		else {
			skills.put(skill, new Level());						
		}
	}
	
	private void calculateWage() {
		wage = BASE_WAGE + (moreMoney.level * 3)
						 + (moreReputation.level * 2);
		
		for (Entry<Skill, Level> entry : skills.entrySet()) {
			wage += entry.getValue().level;
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

	
	
	public ArrayList<String> getProperties() {
		ArrayList<String> workerProperties = new ArrayList<String>();
		workerProperties.add("Name: " + name);
		for (Entry<Skill, Level> entry : skills.entrySet()) {
			workerProperties.add(entry.getKey() + ": " + entry.getValue().level);
		}
		//TODO naming more more/reputation level
		workerProperties.add("More Money Level: " + moreMoney.level);
		workerProperties.add("More Reputation Level: " + moreReputation.level);
		workerProperties.add("Caffine addiction: " + addictionLevel.toString());
		workerProperties.add("Stress: " + stressPercent + "%");
		workerProperties.add("Wage: " + wage);
		
		return workerProperties;
	}
}
