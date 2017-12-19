package ui;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Worker;
import processing.core.PConstants;

public class WorkerInfo extends UIObject {

	public static final int WIDTH = 140;
	public static final int HEIGHT = 50;
	
	public WorkerBox workerBox;
	public Worker worker;
	
	public boolean locked;
	
	public WorkerInfo(WorkerBox box) {
		super(box.position.x + 80, box.position.y, WIDTH, HEIGHT, DrawEngine.BLACK);
		this.workerBox = box;
		this.worker = box.worker;
		
		this.locked = this.worker == null;
	}

	@Override
	public void display(DrawEngine drawEngine) {
		
		/* Display worker */
		if (worker != null && !locked) {
			drawEngine.drawImage(PConstants.CENTER, drawEngine.workerInfo, position.x, position.y);
			
			/* Progress bar */
			drawProgressBar(drawEngine);
			
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Stress: " + worker.stressPercent + "%", 
					workerBox.originalPosition.x + workerBox.width + 10, workerBox.originalPosition.y - 30, 
					DrawEngine.BLACK);
			
			workerBox.display(drawEngine);
		}
		
		/* Display locked */
		else if (locked) {
			drawEngine.drawImage(PConstants.CENTER, drawEngine.workerInfoLocked, position.x, position.y);
		}
		
		/* Display vacant */
		else {
			drawEngine.drawImage(PConstants.CENTER, drawEngine.workerInfoUnlocked, position.x, position.y);
			workerBox.display(drawEngine);
		}
		

		
		/* Display hover */
//		if (mouseOver) {
//			drawEngine.drawRectangle(PConstants.CENTER, col, position.x - 2*width, position.y, width, height);
//		}
		
	}
	
	private void drawProgressBar(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.CORNER, DrawEngine.parent.color(255), 
				workerBox.originalPosition.x + workerBox.width + 10, workerBox.originalPosition.y + 10, 150, 30);
		
		
		/* Timer progress */
		float timer = 0f;
		if (worker != null) {
			timer  = (worker.workTimer / worker.workTimerStart) * 150;
		}
		drawEngine.drawRectangle(PConstants.CORNER, DrawEngine.parent.color(50,50,250), 
				workerBox.originalPosition.x + workerBox.width + 10, workerBox.originalPosition.y + 10, timer, 30, 100);
		
		/* Activity name */
		if (worker != null && worker.currentActivity != null) {
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 12, worker.currentActivity.name(), 
					workerBox.originalPosition.x + workerBox.width + 15, workerBox.originalPosition.y + 25, DrawEngine.BLACK);
		}
	}
	
	
	public void displayHover(DrawEngine drawEngine) {
	}
	

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		return Optional.empty();
	}

	public boolean hasWorker() {
		return worker != null;
	}
	
	public void setWorker(Worker worker) {
		this.worker = worker;
		this.workerBox.worker = worker;
	}
	
}
