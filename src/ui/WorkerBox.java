package ui;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import game.DrawEngine;
import game.GameContext;
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
//			int col = worker.currentActivity == null ? DrawEngine.parent.color(0, 255, 0) : DrawEngine.parent.color(0, 105, 0);
//			
//			drawEngine.drawSquare(PConstants.RADIUS, col, position, width);
//			PImage box = drawEngine.box.copy();
//			box.resize(width*2, height*2);
			
			
			/* Worker name */
			drawEngine.drawText(16, worker.name, position.x, position.y + width/2, DrawEngine.BLACK);
			
			/* Best worker skills */
			drawWorkerSkills(drawEngine);
		}
		
		/* Vacant worker spot */
//		else {
//			int col = DrawEngine.parent.color(200, 150, 0);
//			drawEngine.drawSquare(PConstants.RADIUS, col, position, width);
//		}
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
			if (mouseOver && !isDisabled()) {
				mouseLocked = true;
			}
			else {
				mouseLocked =  false;
			}
			
			mouseXOffset = mouseX - position.x;
			mouseYOffset = mouseY - position.y;
		}

		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		return Optional.empty();
	}
	
	
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
	
	public boolean isDisabled() {
		return (!hasWorker() || 
				(worker != null && worker.currentActivity != null));
	}
	
	public boolean hasWorker() {
		return worker != null;
	}
	
	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public void reset() {
		mouseLocked = false;
		mouseOver = false;
		position = originalPosition.copy();
		
	}

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
