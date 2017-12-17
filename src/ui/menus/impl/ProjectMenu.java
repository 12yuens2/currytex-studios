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
//		int textCol = DrawEngine.BLACK;
//		
//		int yPos = (int) position.y - height + 50;
//		for (String property : project.getProperties()) {
//			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, property, position.x - width + 50, yPos, textCol);
//			yPos += 25;
//		}
		project.menuDisplay(drawEngine, position.copy().add(-width + 50, -height + 50));
	}

}
