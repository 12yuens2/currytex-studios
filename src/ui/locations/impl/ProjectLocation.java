package ui.locations.impl;

import java.util.Optional;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import game.states.impl.InMenuState;
import objs.Worker;
import objs.activities.Activity;
import objs.activities.impl.ProjectActivity;
import processing.core.PConstants;
import ui.locations.Location;
import ui.menus.impl.ChooseNewProjectMenu;
import ui.menus.impl.ProjectMenu;

public class ProjectLocation extends Location {
	
	public ProjectActivity project;
	
	public ProjectLocation(float xPos, float yPos, int size) {
		super(xPos, yPos, size, 0);

	}

	@Override
	public Activity getActivity() {
		return project;		
	}
	
	@Override
	public boolean canAddWorker() {
		return project != null && project.workRequired > 0;
	}

	@Override
	public void addWorker(Worker worker) {
		super.addWorker(worker);
		project.workRequired--;
	}

	@Override
	public void display(DrawEngine drawEngine) {
		if (project != null && project.finished) {
			project = null;
		}
		
		col = project != null ? DrawEngine.parent.color(255, 0, 0) : DrawEngine.parent.color(150, 0, 50);
		super.display(drawEngine);
		
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
			return super.handleLeftClick(mouseX, mouseY, context, currentState);
//			project.manualDecrement(context.constants.manualClickPower);
			
		}
		
//		return Optional.empty();
		
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
