package game;

import java.util.ArrayList;
import java.util.Iterator;

import app.DevStudios;
import objs.Level;
import objs.Skill;
import objs.Studio;
import objs.Worker;
import objs.activities.impl.ProjectActivity;
import placeholder.WorkerBox;
import processing.core.PApplet;
import ui.buttons.Button;
import ui.buttons.impl.MenuButton;
import ui.locations.Location;
import ui.menus.Menu;

public class GameContext {
	
	public GameTime gameTime;
	public Studio studio;
	
	public ArrayList<Worker> workers;
	public ArrayList<ProjectActivity> activeProjects;
	
	public GameContext(PApplet parent) {
		this.gameTime = new GameTime();
		this.studio = new Studio();
		
		this.workers = new ArrayList<>();
		this.activeProjects = new ArrayList<>();
	}
	
	public void timeStep() {
		gameTime.incrementTimestep(this);
		
		for (Worker worker : workers) {

			worker.integrate();
		}
		
		
		
		Iterator<ProjectActivity> projectIt = activeProjects.iterator();
		while (projectIt.hasNext()) {
			ProjectActivity project = projectIt.next();
			if (project.finished) {
				project.finish(studio);
				projectIt.remove();
				//TODO reputation
			}
		}
		
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

}
