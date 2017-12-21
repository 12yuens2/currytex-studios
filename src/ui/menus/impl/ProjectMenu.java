package ui.menus.impl;

import app.CurryTeXStudios;
import game.DrawEngine;
import objs.activities.impl.ProjectActivity;
import ui.menus.Menu;

/**
 * Menu with project details.
 *
 */
public class ProjectMenu extends Menu {

	public ProjectActivity project;
	
	public ProjectMenu(ProjectActivity project) {
		super(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, 300, 200);
		this.project = project;
	}
	
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);

		drawEngine.drawText(30, "Project details", position.x, position.y - height + 60, DrawEngine.BLACK);
		
		project.menuDisplay(drawEngine, position.copy().add(-width + 50, -height + 125));
	}

}
