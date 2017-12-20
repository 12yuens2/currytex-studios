package objs.activities.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;

import game.DrawEngine;
import game.GameModifiers;
import game.states.GameState;
import objs.Studio;
import objs.activities.Activity;
import objs.factories.ProjectFactory.Difficulty;
import objs.factories.ProjectFactory.ProjectType;
import objs.workers.Level;
import objs.workers.Skill;
import objs.workers.Worker;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import ui.menus.impl.ProjectMenu;

public class ProjectActivity extends Activity {
	
	/* 
	 * Default time needed per work load.
	 * Increases with difficulty and as game progresses?
	 */
	public static int HOURS_PER_WORK = 48;
	
	public static final float LEVEL_MODIFIER = 0.2f;
	
	public static Random random = new Random();

	public int workRequired, revenue, reputation, timePerWork, timeLeft;
	public boolean finished;

	public ArrayList<Skill> skillsRequired;
	public ArrayList<Worker> activeWorkers;
	
	public final Difficulty difficulty;
	public final ProjectType type;
	
	
	public ProjectActivity(int workRequired, int revenue, int reputation, int timePerWork, int timeLeft,
			ProjectType type, Difficulty difficulty, ArrayList<Skill> skillsRequried) {
		super(null);

		this.difficulty = difficulty;
		this.type = type;
		this.activeWorkers = new ArrayList<>();
		this.skillsRequired = skillsRequried;
		
		this.finished = false;

		this.workRequired = workRequired;
		this.revenue = revenue;
		this.reputation = reputation;
		this.timePerWork = timePerWork;
		this.timeLeft = timeLeft;
	}	
	

	@Override
	public Activity start(Worker worker) {
		double timeNeeded = timePerWork;
		for (Skill s : worker.getSkills().keySet()) {
			if (skillsRequired.contains(s)) {
				timeNeeded -= (timeNeeded * LEVEL_MODIFIER * worker.skills.get(s).level);
			}
		}
		
		worker.setWorkTimer(1 + (int) timeNeeded);
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
			int exp = (int) ((difficulty.minExp() + random.nextInt(10)) * GameModifiers.expModifier);
			worker.updateSkills(s, exp);
		}
		
		/* Add stress to worker */
		worker.addStress(-5);
		
		return super.finish(worker, currentState);
	}

	/**
	 * Finish project for the studio
	 * @param studio
	 */
	public void finish(Studio studio) {
		studio.currency += revenue;
		studio.totalReputation += reputation;
		for (Skill skill : skillsRequired) {
			studio.addReputation(skill, reputation);
		}
	}
	

	/**
	 * Failed this project for the studio
	 */
	public void fail(Studio studio) {
		switch(type) {
			case CORPORATE:
				/* Lose reputation */
				studio.totalReputation -= Math.abs(reputation) * 3;
				for (Skill s : skillsRequired) {
					studio.addReputation(s, -Math.abs(reputation) * 3);
				}
				
				/* Lose money */
				studio.currency -= revenue/2;
				break;
				
			case FREE:
				/* Lose reputation */
				studio.totalReputation -= Math.abs(reputation);
				for (Skill s : skillsRequired) {
					studio.addReputation(s, -Math.abs(reputation));
				}
				break;
				
			case NORMAL:
				/* Lose reputation */
				studio.totalReputation -= Math.abs(reputation) * 2;
				for (Skill s : skillsRequired) {
					studio.addReputation(s, -Math.abs(reputation) * 2);
				}
				
			default:
				break;
		}
		
	}
	
	
	
	public ProjectMenu getMenu() {
		return new ProjectMenu(this);
	}
	
	public ArrayList<String> getProperties() {
		ArrayList<String> projectProperties = new ArrayList<String>();
		projectProperties.add("Number of features: " + workRequired);
		projectProperties.add("Time limit: " + timeLeft + " days");
		projectProperties.add("Difficulty: " + difficulty);
		projectProperties.add("Type: " + type); 
		
		return projectProperties;
	}
	
	

	public void manualDecrement(int amount) {
		for (Worker worker : activeWorkers) {
			worker.work(amount);
		}		
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
		
		/* Draw skills required */
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Skills required: " + skillsRequired.toString(), 
				xPos, propertiesYPos, DrawEngine.BLACK);
		
		
		/* Draw money and reputation */
		int iconYPos = yPos + 30;
		
		int moneyXPos = xPos + 300;
		drawEngine.drawImage(PConstants.CENTER, drawEngine.moneyIcon, moneyXPos, iconYPos);
		drawEngine.drawText(16, revenue+"", moneyXPos, iconYPos+30, DrawEngine.BLACK);

		int repXPos = moneyXPos + 150;
		drawEngine.drawImage(PConstants.CENTER, drawEngine.reputationIcon, repXPos, iconYPos);
		drawEngine.drawText(16, reputation+"", repXPos, iconYPos+30, DrawEngine.BLACK);
		
		
	}


	@Override
	public String name() {
		return "Working on project...";
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
