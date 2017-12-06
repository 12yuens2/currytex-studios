package game;

import java.util.ArrayList;
import java.util.Iterator;

import app.DevStudios;
import objs.Level;
import objs.Skill;
import objs.Studio;
import objs.Worker;
import objs.activities.impl.Project;
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
	public ArrayList<Project> activeProjects;
	
	public GameContext(PApplet parent) {
		this.gameTime = new GameTime();
		this.studio = new Studio();
		
		this.workers = new ArrayList<>();
		this.activeProjects = new ArrayList<>();
	}
	
	public void timeStep() {
		gameTime.incrementTimestep();
		
		for (Worker worker : workers) {
			worker.integrate();
		}
		
		
		
		Iterator<Project> projectIt = activeProjects.iterator();
		while (projectIt.hasNext()) {
			Project project = projectIt.next();
			if (project.finished) {
				studio.currency += project.revenue;
				projectIt.remove();
				//TODO reputation
			}
		}
//		for (Project project : activeProjects) {
//			if (project.finished) {
//				studio.currency += project.revenue;
//				//TODO reputation;
//			}
//		}
		
	}

}
