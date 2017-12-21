package ui.menus.impl;

import java.util.ArrayList;

import app.CurryTeXStudios;
import game.DrawEngine;
import game.states.GameState;
import objs.activities.impl.ProjectActivity;
import objs.factories.ProjectFactory;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import ui.buttons.impl.ChooseProjectButton;
import ui.locations.impl.ProjectLocation;
import ui.menus.Menu;

public class ChooseNewProjectMenu extends Menu {
	
	public ArrayList<ProjectActivity> newProjects;
	
	public ChooseNewProjectMenu(ProjectLocation location, GameState previousState) {
		super((CurryTeXStudios.SCREEN_X/2) - 100, CurryTeXStudios.SCREEN_Y/2 - 25, 500, 375);

		newProjects = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			ProjectActivity newProject = ProjectFactory.getRandomProject(previousState.context.studio.reputation, previousState.context.trends);
			newProjects.add(newProject);
			
			float yPos = (position.y - height - 50) + (225 * (i + 1));
			buttons.add(new ChooseProjectButton(position.x + width - 200, yPos, newProject, location, previousState));
		}
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(30, "New projects", position.x, position.y - height + 50, DrawEngine.BLACK);
		
		PVector pos = new PVector(position.x - width + 50, position.y - height + 100);
		for (ProjectActivity project : newProjects) {
			project.menuDisplay(drawEngine, pos.copy());
			pos.add(new PVector(0, 215));
		}
	}

}
