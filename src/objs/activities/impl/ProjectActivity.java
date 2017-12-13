package objs.activities.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import game.states.GameState;
import objs.Level;
import objs.Skill;
import objs.Studio;
import objs.Worker;
import objs.activities.Activity;
import objs.factories.ProjectFactory.Difficulty;
import objs.factories.ProjectFactory.ProjectType;
import ui.menus.impl.ProjectMenu;

public class ProjectActivity extends Activity {
	
	/* 
	 * Default time needed per work load.
	 * Increases with difficulty and as game progresses?
	 */
	public static int MINUTES_PER_WORK = 120;
	
	public static Random random = new Random();


	public String name;
	public int workRequired, revenue, timePerWork, expGain, reputation;
	public boolean finished;

	public ArrayList<Skill> skillsRequired;
	public ArrayList<Worker> activeWorkers;
	
	public final Difficulty difficulty;
	public final ProjectType type;
	
	
	public ProjectActivity(String name, int workRequired, int revenue, int reputation, int timePerWork,
			ProjectType type, Difficulty difficulty) {
		super(null);
		this.name = name;
		this.difficulty = difficulty;
		this.type = type;
		this.activeWorkers = new ArrayList<>();
		this.skillsRequired = new ArrayList<>();
		
		this.finished = false;
		
		//TODO create projects with skills based on type of project
		skillsRequired.add(Skill.JAVA);

		this.workRequired = workRequired;
		this.revenue = revenue;
		this.reputation = reputation;
		this.timePerWork = timePerWork;
		this.expGain = 20; //TODO
	}	
	

	@Override
	public Activity start(Worker worker) {
		double timeNeeded = timePerWork;
		for (Skill s : worker.skills.keySet()) {
			if (skillsRequired.contains(s)) {
				//TODO better formula on reducing time needed based on worker skill level
				timeNeeded *= (1.0 / worker.skills.get(s).level);
			}
		}
		
		worker.workTimer = 1 + (int) timeNeeded;
		activeWorkers.add(worker);
		
		Random random = new Random();
		revenue += random.nextInt(worker.moreMoney.level) * random.nextInt(worker.moreMoney.level);
		reputation += random.nextInt(worker.moreReputation.level) * random.nextInt(worker.moreReputation.level);
		
		return this;
	}

	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		activeWorkers.remove(worker);
		
		if (workRequired <= 0 && activeWorkers.isEmpty()) {
			finished = true;
		}
		
		/* Add skills to workers after they finish a workload of this project */
		for (Skill s : skillsRequired) {
			worker.updateSkills(s, expGain);
		}
		
		/* Add stress to worker */
		worker.stressPercent = Math.min(100, worker.stressPercent + 10);
		
		return super.finish(worker, currentState);
	}

	/**
	 * Finish project for the studio
	 * @param studio
	 */
	public void finish(Studio studio) {
		studio.currency += revenue;
		for (Skill skill : skillsRequired) {
			studio.addReputation(skill, reputation);
		}
	}
	
	
	public ProjectMenu getMenu() {
		return new ProjectMenu(this);
	}
	
	public ArrayList<String> getProperties() {
		ArrayList<String> projectProperties = new ArrayList<String>();
		projectProperties.add("Project name: " + name);
		projectProperties.add("Skills required:" + skillsRequired.toString());
		projectProperties.add("Number of features: " + workRequired);
		projectProperties.add("Money: " + revenue);
		projectProperties.add("Reputation: " + reputation);
		projectProperties.add("Difficulty: " + difficulty);
		projectProperties.add("Type: " + type); 
		
		return projectProperties;
	}
	
	

	public void manualDecrement(int amount) {
		for (Worker worker : activeWorkers) {
			worker.workTimer -= amount;
		}		
	}
	
	
	
	
//	/**
//	 *  Create a random project 
//	 */
//	public static ProjectActivity randomProject() {
//		Random random = new Random();
//		Difficulty difficulty = Arrays.asList(Difficulty.values()).get(random.nextInt(Difficulty.values().length));
//		
//		return new ProjectActivity("Java 1", random.nextInt(10) + 1, random.nextInt(100) + 15, difficulty);
//	}
}
