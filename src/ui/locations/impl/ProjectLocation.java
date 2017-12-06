package ui.locations.impl;

import java.util.Optional;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import game.states.impl.InMenuState;
import objs.activities.Activity;
import objs.activities.impl.Project;
import processing.core.PConstants;
import ui.locations.Location;
import ui.menus.impl.ChooseNewProjectMenu;
import ui.menus.impl.ProjectMenu;

public class ProjectLocation extends Location {
	
	public Project project;
	
	public ProjectLocation(float xPos, float yPos, int size) {
		super(xPos, yPos, size, 0);

	}

	@Override
	public Activity getActivity() {
		return project;		
	}

	@Override
	public boolean addWorker() {
		if (project != null && project.workRequired > 0) {
			project.workRequired--;
			numWorkers++;
			return true;
		}

		return false;
	}

	@Override
	public void display(DrawEngine drawEngine) {
		int col = project != null ? DrawEngine.parent.color(255, 0, 0) : DrawEngine.parent.color(150, 0, 50);
		drawEngine.drawSquare(PConstants.RADIUS, col, position, width);
		
		if (project != null) {
			drawEngine.drawText(12, ""+project.workRequired, position.x, position.y, DrawEngine.parent.color(0));
		} else {
			drawEngine.drawText(12, "?", position.x, position.y, DrawEngine.parent.color(0));
		}
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		//TODO create menu to allow player to choose project
		if (project == null) {
			return Optional.of(new InMenuState(new ChooseNewProjectMenu(this, currentState), currentState));
//			project = Project.randomProject();
//			context.activeProjects.add(project);
		}
		else {
			project.manualDecrement(1);
			
		}
		
		return Optional.empty();
		
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		if (project != null) {
			return Optional.of(new InMenuState(project.getMenu(), currentState));
		}
		return Optional.empty();
	}

}
