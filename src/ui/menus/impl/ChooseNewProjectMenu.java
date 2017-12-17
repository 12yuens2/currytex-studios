package ui.menus.impl;

import java.util.ArrayList;

import app.DevStudios;
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
		super((DevStudios.SCREEN_X/2) - 100, DevStudios.SCREEN_Y/2 - 25, 500, 325);

		newProjects = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			ProjectActivity newProject = ProjectFactory.getRandomProject(previousState.context.studio.reputation);
			newProjects.add(newProject);
			
			float yPos = (position.y - height - 110) + (225 * (i + 1));
			buttons.add(new ChooseProjectButton(position.x + width - 200, yPos, newProject, location, previousState));
		}
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		PVector pos = new PVector(position.x - width + 50, position.y - height + 30);
		//int xPos = (int) (position.x - width + 50);
		for (ProjectActivity project : newProjects) {
			project.menuDisplay(drawEngine, pos.copy());
			pos.add(new PVector(0, 225));
		}
//			int yPos = (int) (position.y - height + 50);
//			
//			for (String property : project.getProperties()) {
//				drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, property, xPos, yPos, DrawEngine.BLACK);
//				yPos += 30;
//			}
//			xPos += 250;
//		}
//		
//		PImage icon = DrawEngine.parent.loadImage("imgs/placeholder.png");
//		icon.resize(40, 40);
//		
//		DrawEngine.parent.image(icon, position.x, position.y - height + 200);
	}

}
