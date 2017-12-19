package ui;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Worker;
import processing.core.PConstants;

public class WorkerInfo extends UIObject {

	public WorkerBox workerBox;
	public Worker worker;
	
	public boolean locked;
	
	public WorkerInfo(WorkerBox box) {
		super(box.position.x + 80, box.position.y, 140, 50, DrawEngine.BLACK);
		this.workerBox = box;
		this.worker = box.worker;
		
		this.locked = this.worker == null;
	}

	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.RADIUS, col, position.x, position.y, width, height);
		
		/* Display worker */
		if (worker != null && !locked) {
			drawProgressBar(drawEngine);
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Stress: " + worker.stressPercent + "%", 
					workerBox.originalPosition.x + workerBox.width + 10, workerBox.originalPosition.y - 30, 
					DrawEngine.parent.color(255));
			
			workerBox.display(drawEngine);
		}
		
		/* Display locked */
		else if (locked) {
			drawEngine.drawText(16, "LOCKED!", position.x, position.y, DrawEngine.parent.color(255));
		}
		
		/* Display vacant */
		else {
			drawEngine.drawText(16, "VACANT!", position.x, position.y, DrawEngine.parent.color(255));
		}
		

		
		/* Display hover */
//		if (mouseOver) {
//			drawEngine.drawRectangle(PConstants.CENTER, col, position.x - 2*width, position.y, width, height);
//		}
		
	}
	
	private void drawProgressBar(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.CORNER, DrawEngine.parent.color(255), 
				workerBox.originalPosition.x + workerBox.width + 10, workerBox.originalPosition.y + 15, 150, 30);
		
		
		float timer = 0f;
		if (worker != null) {
			timer  = (worker.workTimer / worker.workTimerStart) * 150;
		}
		drawEngine.drawRectangle(PConstants.CORNER, DrawEngine.parent.color(50,50,250), 
				workerBox.originalPosition.x + workerBox.width + 10, workerBox.originalPosition.y + 15, timer, 30);
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
