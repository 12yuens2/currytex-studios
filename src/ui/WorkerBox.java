package ui;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import game.DrawEngine;
import game.GameContext;
import game.GameModifiers;
import game.GameTime;
import game.states.GameState;
import game.states.impl.InMenuState;
import game.states.impl.PlayingState;
import objs.activities.impl.ProjectActivity;
import objs.workers.Level;
import objs.workers.Skill;
import objs.workers.Worker;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import ui.locations.Location;
import ui.menus.Menu;
import ui.menus.impl.WorkerMenu;

public class WorkerBox extends UIObject {

	public final PVector originalPosition;
	public float mouseXOffset, mouseYOffset;
	
	/* mouseOver for hover, mouseLocked for dragging */
	public boolean mouseOver, mouseLocked;
	
	/* Developer associated with this box */
	public Worker worker;	
	
	public WorkerBox(float xPos, float yPos, int size, Worker worker){
		super(xPos, yPos, size);
		this.originalPosition = position.copy();
		
		this.worker = worker;
		
		this.mouseXOffset = 0f;
		this.mouseYOffset = 0f;
		this.mouseOver = false;
		this.mouseLocked = false;
	}
	
	/**
	 * Create Box with no worker.
	 * @param xPos
	 * @param yPos
	 * @param size
	 */
	public WorkerBox(float xPos, float yPos, int size) {
		this(xPos, yPos, size, null);
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawImage(PConstants.CENTER, drawEngine.resizedBox(width*2, height*2), position.x, position.y);
		
		if (worker != null) {
			
			/* Worker name */
			drawEngine.drawText(16, worker.name, position.x, position.y + width/2, DrawEngine.BLACK);
			
			/* Best worker skills */
			drawWorkerSkills(drawEngine);
		}
	}
	
	private void drawWorkerSkills(DrawEngine drawEngine) {
		int xPos = (int) (position.x - 20);
		int yPos = (int) (position.y - height + 20);
		for (Skill s : worker.getSkills().keySet()) {
			drawEngine.drawImage(PConstants.CENTER, s.icon(drawEngine), xPos, yPos);
			xPos += 40;
		}
	}
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		if (worker != null) {
			
			/* Allow drag if not disabled */
			if (mouseOver && !isDisabled()) {
				mouseLocked = true;
			}
			else {
				mouseLocked =  false;
			}
			
			/* Allow manual clicking on worker to decrease timer */
			if (worker.workTimer > 0) {
				worker.work(GameModifiers.manualClickPower);
			}
			
			mouseXOffset = mouseX - position.x;
			mouseYOffset = mouseY - position.y;
		}

		return Optional.empty();
	}
	
	/**
	 * Move the worker in this box to a location if possible. 
	 * Checking this resets the worker box and enters the worker into any location possible. 
	 * @param locations to try and enter. 
	 */
	public void enterIfPossible(ArrayList<? extends Location> locations) {
		if (mouseLocked) {
			mouseLocked = false;
			for (Location location : locations) {
				location.workerCollision = false;
				
				if (overlapsWith(location) && location.canAddWorker()) {
					worker.startNewActivity(location);
				}
			}
			position = originalPosition.copy();
		}
	}
	
	/**
	 * A worker box is disabled if it does not contain a worker,
	 * or the worker is currently working on an activity.
	 * @return
	 */
	public boolean isDisabled() {
		return (!hasWorker() || 
				(worker != null && worker.currentActivity != null));
	}
	
	/**
	 * Check if this worker box contains a worker
	 * @return
	 */
	public boolean hasWorker() {
		return worker != null;
	}
	
	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	/**
	 * Reset the worker box back to its original position.
	 */
	public void reset() {
		mouseLocked = false;
		mouseOver = false;
		position = originalPosition.copy();
		
	}

	/**
	 * Check overlap of this worker box with given locations. 
	 * @param locations to check overlap with.
	 */
	public void checkCollision(ArrayList<Location> locations) {
		for (Location location : locations) {
			if (location.overlapsWith(this)) {
				location.workerCollision = true;
			} 
			else {
				location.workerCollision = false;
			}
		}
		
	}

}
