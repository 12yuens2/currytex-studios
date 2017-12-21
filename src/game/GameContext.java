package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import app.CurryTeXStudios;
import game.states.GameState;
import objs.Studio;
import objs.WorldTrend;
import objs.activities.impl.ProjectActivity;
import objs.workers.Level;
import objs.workers.Skill;
import objs.workers.Worker;
import processing.core.PApplet;
import ui.Tooltip;
import ui.WorkerBox;
import ui.buttons.Button;
import ui.buttons.impl.MenuButton;
import ui.locations.Location;
import ui.menus.Menu;

public class GameContext {
	 
	
	public GameTime gameTime;
	public Studio studio;
	public WorldTrend trends;


	public boolean gameOver, firstYear, reachedGoal;
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
	
	public void timeStep(GameState currentState) {
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
