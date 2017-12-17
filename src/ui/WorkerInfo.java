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
	
	public WorkerInfo(WorkerBox box) {
		super(box.position.x + 80, box.position.y, 140, 50, DrawEngine.BLACK);
		this.workerBox = box;
		this.worker = box.worker;
	}

	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.RADIUS, col, position.x, position.y, width, height);
		
		/* Progress bar box */
		if (worker != null) {
			drawProgressBar(drawEngine);
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Stress: " + worker.stressPercent + "%", 
					workerBox.originalPosition.x + workerBox.width + 10, workerBox.originalPosition.y + 5, 
					DrawEngine.parent.color(255));
		}
		

		workerBox.display(drawEngine);
		
		/* Display hover */
		if (mouseOver) {
			drawEngine.drawRectangle(PConstants.CENTER, col, position.x - 2*width, position.y, width, height);
		}
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasWorker() {
		return worker != null;
	}
	
	public void setWorker(Worker worker) {
		this.worker = worker;
		this.workerBox.worker = worker;
	}
	
}
