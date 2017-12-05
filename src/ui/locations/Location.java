package ui.locations;
import java.util.ArrayList;

import game.DrawEngine;
import objs.Skill;
import objs.activities.Activity;
import objs.activities.impl.Project;
import objs.activities.impl.Project.Difficulty;
import processing.core.PConstants;
import ui.UIObject;

public abstract class Location extends UIObject {

	public int numWorkers;
	
	//TODO maximum number of workers
	
//	public Project project;
	
	public Location(float xPos, float yPos, int size, int col) {
		super(xPos, yPos, size, col);
		
		this.numWorkers = 0;
//		this.project = Project.randomProject();
	}
	
	/**
	 * Create Activity associated with this location.
	 * @return
	 */
	public abstract Activity getActivity();
	
	
	/**
	 * Add a worker to this location if possible.
	 * @return - If the worker can be added to this location.
	 */
	public abstract boolean addWorker();

	
	
	
	public abstract void handleLeftClick(float mouseX, float mouseY);
	
	
	

//	public void display(DrawEngine drawEngine) {
//		drawEngine.drawSquare(PConstants.RADIUS, drawEngine.parent.color(255,0,0), position, size);
//		
//		if (project != null) {
//			drawEngine.drawText(12, ""+project.workRequired, position.x, position.y, drawEngine.parent.color(0));
//		} else {
//			drawEngine.drawText(12, "?", position.x, position.y, drawEngine.parent.color(0));
//		}
//	}

//	public void completeWorkload() {
//		if (project != null) {
//			numWorkers--;
//			if (project.workRequired <= 0 && numWorkers <= 0) {
//				project = null;
//			}
//		}
//		
//	}

//	public void addWorker() {
//		project.workRequired--;
//		numWorkers++;		
//	}

}
