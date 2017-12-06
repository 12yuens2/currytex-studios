package ui.menus.impl;

import app.DevStudios;
import game.DrawEngine;
import objs.activities.impl.Project;
import ui.menus.Menu;

public class ProjectMenu extends Menu {

	public Project project;
	
	public ProjectMenu(Project project) {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 300);
		this.project = project;
	}
	
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		int textCol = DrawEngine.parent.color(0);
		
		int yPos = (int) position.y - height + 50;
		for (String property : project.getProperties()) {
			drawEngine.drawText(16, property, position.x - width + 150, yPos, textCol);
			yPos += 25;
		}
	}

}
