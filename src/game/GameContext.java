package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import app.DevStudios;
import game.states.GameState;
import objs.Level;
import objs.Skill;
import objs.Studio;
import objs.Worker;
import objs.WorldTrend;
import objs.activities.impl.ProjectActivity;
import processing.core.PApplet;
import ui.WorkerBox;
import ui.buttons.Button;
import ui.buttons.impl.MenuButton;
import ui.locations.Location;
import ui.menus.Menu;

public class GameContext {
	
	public GameTime gameTime;
	public Studio studio;
	public GameModifiers constants;
	public WorldTrend trends;
	
	public LinkedList<GameState> nextStates;
	
	public ArrayList<Worker> workers;
	public ArrayList<ProjectActivity> activeProjects;
	
	public GameContext(PApplet parent) {
		this.gameTime = new GameTime();
		this.studio = new Studio();
		this.constants = new GameModifiers();
		this.trends = new WorldTrend(parent);
		
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

	public void newHour() {
		for (Worker worker : workers) {
			worker.updateSalary();
		}
		
	}
	
	public void newDay() {
		for (Worker worker : workers) {
			worker.paySalary(studio);
		}
		
		if (studio.currency < 0) {
			//TODO game over? or debt
		}
	}
	
	public void newYear() {
		//TODO check yearly reputation goal.
	}

}
