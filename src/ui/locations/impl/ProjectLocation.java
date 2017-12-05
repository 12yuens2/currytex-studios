package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.Project;
import processing.core.PConstants;
import ui.locations.Location;

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
	public void handleLeftClick(float mouseX, float mouseY) {
		//TODO create menu to allow player to choose project
		if (project == null) {
			project = Project.randomProject();
		}
		
	}

}
