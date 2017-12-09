package ui.locations;
import java.util.ArrayList;
import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Skill;
import objs.activities.Activity;
import objs.activities.impl.ProjectActivity;
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
	 * Check if it is possible to add a new worker to this location.
	 * @return - If the worker can be added to this location.
	 */
	public abstract boolean canAddWorker();
	
	/**
	 * Add a worker to this location.
	 */
	public abstract void addWorker();

	
	//TODO make all locations have default handle left click to decrease worker timer
	

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
