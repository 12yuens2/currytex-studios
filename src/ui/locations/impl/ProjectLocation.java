package ui.locations.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import game.states.impl.InMenuState;
import objs.Worker;
import objs.activities.Activity;
import objs.activities.impl.ProjectActivity;
import processing.core.PConstants;
import ui.locations.Location;

public class ProjectLocation extends Location {
	
	public ProjectActivity project;
	public boolean occupied;
	
	public ProjectLocation(float xPos, float yPos, int size) {
		super(xPos, yPos, 75, 0);
		
		this.occupied = false;
	}

	@Override
	public Activity getActivity() {
		return project;		
	}
	
	@Override
	public boolean canAddWorker() {
		return super.canAddWorker() && project != null && project.workRequired > 0;
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
			occupied = false;
		}
		
		col = project != null ? DrawEngine.parent.color(255, 0, 0) : DrawEngine.parent.color(150, 0, 50);
		super.display(drawEngine);
		
		if (project != null) {
			drawProjectInfo(drawEngine);
		} else {
			drawEngine.drawText(12, "?", position.x, position.y, DrawEngine.BLACK);
		}
	}

	private void drawProjectInfo(DrawEngine drawEngine) {
		/* Project name */
		drawEngine.drawText(PConstants.LEFT, PConstants.TOP, 12, project.name, 
				position.x - width + 5, position.y - height + 5, DrawEngine.BLACK);
		
		/* Work required */
		drawEngine.drawText(PConstants.RIGHT, PConstants.TOP, 24, ""+project.workRequired,
				position.x + width - 5, position.y - height + 5, DrawEngine.BLACK);
		
		/* Difficulty */
		drawEngine.drawText(PConstants.LEFT, PConstants.TOP, 12, ""+project.difficulty,
				position.x - width + 5, position.y - height + 20, DrawEngine.BLACK);
		
		/* Money earned */
		drawEngine.drawImage(PConstants.CENTER, drawEngine.moneyIcon, position.x - width/2, position.y + height - 50);
		drawEngine.drawText(16, ""+project.revenue, 
				position.x - width/2, position.y + height - 20, DrawEngine.BLACK);
		
		/* Reputation earned */
		drawEngine.drawImage(PConstants.CENTER, drawEngine.reputationIcon, position.x + width/2, position.y + height - 50);
		drawEngine.drawText(16, ""+project.reputation, 
				position.x + width/2, position.y + height - 20, DrawEngine.BLACK);
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
