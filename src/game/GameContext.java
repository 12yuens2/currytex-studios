package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

import game.states.GameState;
import objs.Studio;
import objs.WorldTrend;
import objs.activities.impl.ProjectActivity;
import objs.workers.Worker;

public class GameContext {
	
	public GameTime gameTime;
	public Studio studio;
	public WorldTrend trends;

	public boolean gameOver, firstYear, reachedGoal;
	
	/* Tutorial tooltip reveals */
	public boolean projectReveal, coffeeReveal, moreMoneyReveal, moreRepReveal, multipleReveal;

	public LinkedList<GameState> nextStates;
	
	public ArrayList<Worker> workers;
	public ArrayList<ProjectActivity> activeProjects;
	
	public GameContext() {
		this.gameTime = new GameTime();
		this.studio = new Studio();
		this.trends = new WorldTrend();

		this.firstYear = true;
		this.gameOver = false;
		this.reachedGoal = false;
		this.projectReveal = false;
		this.coffeeReveal = false;
		this.moreMoneyReveal = false;
		this.moreRepReveal = false;
		this.multipleReveal = false;
		
		this.nextStates = new LinkedList<>();
		this.workers = new ArrayList<>();
		this.activeProjects = new ArrayList<>();
	}
	
	/**
	 * Update the time and game objects.
	 * @param currentState
	 */
	public void timeStep(GameState currentState) {
		/* Integrate game time */
		gameTime.incrementTimestep(this);
		
		/* Integrate workers */
		for (Worker worker : workers) {
			Optional<GameState> nextState = worker.integrate(currentState);
			
			if (nextState.isPresent()) {
				nextStates.add(nextState.get());
			}
		}
		
		/* Integrate projects */
		Iterator<ProjectActivity> projectIt = activeProjects.iterator();
		while (projectIt.hasNext()) {
			ProjectActivity project = projectIt.next();
			if (project.finished) {
				project.finish(studio);
				projectIt.remove();
			}
		}
		
		/* Integrate trends */
		trends.integrate(gameTime);
		
	}

	/**
	 * Function to do all things that need to be done on a new in-game day.
	 * Update worker salaries and project time limits. 
	 */
	public void newDay() {
		for (Worker worker : workers) {
			worker.updateSalary();
		}
		
		Iterator<ProjectActivity> projectIt = activeProjects.iterator();
		while(projectIt.hasNext()) {
			ProjectActivity project = projectIt.next();
			
			project.timeLeft--;
			if (project.timeLeft <= 0) {
				project.fail(studio);
				projectIt.remove();
				
				for (Worker worker : project.activeWorkers) {
					worker.resetActivity();
				}
			}
		}
		
	}
	
	/**
	 * Function to do all things that need to be done on a new in-game month. 
	 * Pay salaries and check for game over. 
	 */
	public void newMonth() {
		/* Make work more difficult as time progresses */
		ProjectActivity.HOURS_PER_WORK += 5 + (studio.totalReputation/50);
		
		if (studio.currency < 0) {
			gameOver = true;
		}
		else {
			for (Worker worker : workers) {
				worker.paySalary(studio);
			}
		}
	}
	
	/**
	 * Function to do all things that need to be done on a new in-game year.
	 * Check for reputation goal and game over.
	 */
	public void newYear() {
		if (studio.totalReputation >= studio.reputationGoal) {
			studio.reputationGoal += studio.reputationGoal;
			reachedGoal = true;
		}
		else {
			gameOver = true;
		}
	}

}
