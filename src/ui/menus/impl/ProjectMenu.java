package ui.menus.impl;

import app.DevStudios;
import game.DrawEngine;
import objs.activities.impl.ProjectActivity;
import processing.core.PConstants;
import ui.menus.Menu;

public class ProjectMenu extends Menu {

	public ProjectActivity project;
	
	public ProjectMenu(ProjectActivity project) {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 300, 200);
		this.project = project;
	}
	
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);

		drawEngine.drawText(30, "Project details", position.x, position.y - height + 60, DrawEngine.BLACK);
		
		project.menuDisplay(drawEngine, position.copy().add(-width + 50, -height + 125));
	}

}
