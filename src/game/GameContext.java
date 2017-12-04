package game;

import java.util.ArrayList;

import app.DevStudios;
import objs.Level;
import objs.Skill;
import objs.Studio;
import objs.Worker;
import objs.activities.impl.Project;
import placeholder.Box;
import placeholder.Location;
import processing.core.PApplet;
import ui.Button;
import ui.MenuButton;
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


	
	public void updateGameTime() {
		gameTime.incrementTimestep();
	}

}
