package ui.menus.impl;

import java.util.ArrayList;

import app.DevStudios;
import game.DrawEngine;
import game.states.GameState;
import objs.activities.impl.ProjectActivity;
import objs.factories.ProjectFactory;
import ui.buttons.impl.ChooseProjectButton;
import ui.locations.impl.ProjectLocation;
import ui.menus.Menu;

public class ChooseNewProjectMenu extends Menu {
	
	public ArrayList<ProjectActivity> newProjects;
	
	public ChooseNewProjectMenu(ProjectLocation location, GameState previousState) {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 700, 400);

		newProjects = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			ProjectActivity newProject = ProjectFactory.getRandomProject(previousState.context.studio.reputation);
			newProjects.add(newProject);
			
			float xPos = (position.x - width + 150) * (i + 1);
			buttons.add(new ChooseProjectButton(xPos, position.y, newProject, location, previousState));
		}
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		int xPos = (int) (position.x - width + 150);
		for (ProjectActivity project : newProjects) {
			int yPos = (int) (position.y - height + 50);
			
			for (String property : project.getProperties()) {
				drawEngine.drawText(16, property, xPos, yPos, DrawEngine.parent.color(0));
				yPos += 30;
			}
			xPos += 250;
		}
	}

}
