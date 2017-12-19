package ui.locations;
import java.util.ArrayList;
import java.util.Optional;

import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;

import game.DrawEngine;
import game.GameContext;
import game.GameModifiers;
import game.states.GameState;
import objs.Skill;
import objs.Worker;
import objs.activities.Activity;
import objs.activities.impl.ProjectActivity;
import processing.core.PConstants;
import processing.core.PImage;
import ui.UIObject;

public abstract class Location extends UIObject {

	public static final int TOWN_LOCATION_SIZE = 85;
	
	public boolean image = true;
	public boolean workerCollision = false;
	
	public ArrayList<Worker> workers;
	//TODO maximum number of workers
	
//	public Project project;
	
	public Location(float xPos, float yPos, int size, int col) {
		super(xPos, yPos, size, col);
		
		this.workers = new ArrayList<>();
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
	public boolean canAddWorker() {
		return workers.size() < GameModifiers.locationMaxWorkers;
	}
	
	/**
	 * Add a worker to this location.
	 */
	public void addWorker(Worker worker) {
//		if (canAddWorker()) {
		workers.add(worker);
//		}
	
	}
	
	
	@Override
	public void display(DrawEngine drawEngine) {
//		drawEngine.drawSquare(PConstants.RADIUS, col, position, width);
		drawEngine.drawImage(PConstants.CENTER, drawEngine.resizedBox(TOWN_LOCATION_SIZE*2, TOWN_LOCATION_SIZE*2), position.x, position.y);
	}
	

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		manualDecrement(GameModifiers.manualClickPower);
		
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		return Optional.empty();
	}

	
	protected void manualDecrement(float amount) {
		for (Worker worker : workers) {
			worker.workTimer = Math.max(0, worker.workTimer - amount);
		}
	}

//	public void display(DrawEngine drawEngine) {
//		drawEngine.drawSquare(PConstants.RADIUS, drawEngine.parent.color(255,0,0), position, size);
//		
//		if (project != null) {
//			drawEngine.drawText(12, ""+project.workRequired, position.x, position.y, DrawEngine.BLACK);
//		} else {
//			drawEngine.drawText(12, "?", position.x, position.y, DrawEngine.BLACK);
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
