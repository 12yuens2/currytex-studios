package ui;

import java.util.ArrayList;
import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.GameTime;
import game.states.GameState;
import game.states.impl.InMenuState;
import game.states.impl.StartState;
import objs.Level;
import objs.Skill;
import objs.Worker;
import objs.activities.impl.ProjectActivity;
import processing.core.PConstants;
import processing.core.PVector;
import ui.locations.Location;
import ui.menus.Menu;
import ui.menus.impl.WorkerMenu;

public class WorkerBox extends UIObject {

	public final PVector originalPosition;
	public float mouseXOffset, mouseYOffset;
	public boolean mouseOver, mouseLocked;
	
	/* Developer associated with this box */
	public Worker worker;	
	
	public WorkerBox(float xPos, float yPos, int size, Worker worker){
		super(xPos, yPos, size, 0);
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
		if (worker != null) {
			int col = worker.currentActivity == null ? DrawEngine.parent.color(0, 255, 0) : DrawEngine.parent.color(0, 105, 0);
			
			drawEngine.drawSquare(PConstants.RADIUS, col, position, width);
			drawEngine.drawRectangle(PConstants.CORNER, DrawEngine.parent.color(0,0,180), position.x, position.y + 30, worker.workTimer, 20);
			drawEngine.drawText(16, worker.name, position.x, position.y + width/2, DrawEngine.parent.color(0));
		}
		
		/* Vacant worker spot */
		else {
			int col = DrawEngine.parent.color(200, 150, 0);
			drawEngine.drawSquare(PConstants.RADIUS, col, position, width);
		}
	}
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		if (worker != null) {
			//TODO game state for dragging
			if (mouseOver && !isDisabled()) {
				mouseLocked = true;
			}
			else {
				mouseLocked =  false;
			}
			
			mouseXOffset = mouseX - position.x;
			mouseYOffset = mouseY - position.y;
		}
//		else {
//			return Optional.of(new TestState(null));
//		}
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		if (worker != null) {
			return Optional.of(new InMenuState(worker.getMenu(), currentState));
		}
		return Optional.empty();
	}
	
	
	public void enterIfPossible(ArrayList<? extends Location> locations) {
		if (mouseLocked) {
			mouseLocked = false;
			for (Location location : locations) {
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

}
