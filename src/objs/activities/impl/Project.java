package objs.activities.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import objs.Level;
import objs.Skill;
import objs.Studio;
import objs.Worker;
import objs.activities.Activity;
import ui.menus.impl.ProjectMenu;

public class Project extends Activity {
	
	/* 
	 * Default time needed per work load.
	 * Increases with difficulty and as game progresses.
	 */
	public static int MINUTES_PER_WORK = 120;
	
	public static Random random = new Random();
	
	
	
	public enum Difficulty {VERY_EASY, EASY, NORMAL, HARD, VERY_HARD};
	
	public String name;
	public int workRequired, revenue, timePerWork, expGain;
	public boolean finished;

	public ArrayList<Skill> skillsRequired;
	public ArrayList<Worker> activeWorkers;
	
	public final Difficulty difficulty;
	
	
	public Project(String name, int workRequired, int revenue, Difficulty difficulty) {
		this.name = name;
		this.difficulty = difficulty;
		this.activeWorkers = new ArrayList<>();
		this.skillsRequired = new ArrayList<>();
		
		this.finished = false;
		
		//TODO create projects with skills based on type of project
		skillsRequired.add(Skill.JAVA);

		this.workRequired = getModifiedWorkRequired(workRequired);
		this.revenue = getModifiedRevenue(revenue);
		this.timePerWork = getModifiedTime();
		this.expGain = 20; //TODO
	}
	
	private int getModifiedWorkRequired(int workRequired) {
		switch (difficulty) {
			case VERY_EASY:
				return (int) (workRequired * 0.7);
				
			case EASY:
				return (int) (workRequired * 0.85);
				
			case NORMAL:
				return workRequired;
				
			case HARD:
				return (int) (workRequired * 1.5);
				
			case VERY_HARD:
				return (int) (workRequired * 2.5);
				
			default:
				return workRequired;
		}
	}
	
	private int getModifiedRevenue(int revenue) {
		switch(difficulty) {
			case VERY_EASY:
				return (int) (revenue * 0.8);
				
			case EASY:
				return (int) (revenue * 0.9);
				
			case NORMAL:
				return revenue;
				
			case HARD:
				return (int) (revenue * 2);
				
			case VERY_HARD:
				return (int) (revenue * 3);
				
			default:
				return revenue;
			
		}
	}

	private int getModifiedTime() {
		switch(difficulty) {
			case VERY_EASY:
				return (int) (MINUTES_PER_WORK * 0.5);
				
			case EASY:
				return (int) (MINUTES_PER_WORK * 0.85);

			case NORMAL:
				return MINUTES_PER_WORK;
				
			case HARD:
				return (int) (MINUTES_PER_WORK * 1.5);
				
			case VERY_HARD:
				return (int) (MINUTES_PER_WORK * 2);
				
			default:
				return MINUTES_PER_WORK;
		
		}
	}
	
	

	@Override
	public void start(Worker worker) {
		double timeNeeded = timePerWork;
		for (Skill s : worker.skills.keySet()) {
			if (skillsRequired.contains(s)) {
				//TODO better formula on reducing time needed based on worker skill level
				timeNeeded *= (1.0 / worker.skills.get(s).level);
			}
		}
		
		worker.workTimer = 1 + (int) timeNeeded;
		activeWorkers.add(worker);
	}

	@Override
	public void finish(Worker worker) {
		activeWorkers.remove(worker);
		
		if (workRequired <= 0 && activeWorkers.isEmpty()) {
			finished = true;
		}
		/* Add skills to workers after they finish a workload of this project */
		for (Skill s : skillsRequired) {
			worker.updateSkills(s, expGain);
		}
	}
	
	
	/**
	 * Mouseclick to manual decrement project time
	 * @param amount
	 */
	public void manualDecrement(int amount) {
		for (Worker worker : activeWorkers) {
			worker.workTimer -= amount;
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
		projectProperties.add("Difficulty: " + difficulty);
		
		return projectProperties;
	}
	
	
	
	
	/**
	 *  Create a random project 
	 */
	public static Project randomProject() {
		Random random = new Random();
		Difficulty difficulty = Arrays.asList(Difficulty.values()).get(random.nextInt(Difficulty.values().length));
		
		return new Project("Java 1", random.nextInt(10) + 1, random.nextInt(100) + 15, difficulty);
	}
}
