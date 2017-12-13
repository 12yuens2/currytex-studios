package ui.buttons.impl;

import java.util.ArrayList;
import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Worker;
import ui.WorkerBox;
import ui.buttons.Button;

public class ChooseWorkerButton extends Button {

	public Worker worker;
	public GameState previousState;
	
	public ChooseWorkerButton(float xPos, float yPos, Worker worker, GameState previousState) {
		super(xPos, yPos, 100, 30, DrawEngine.parent.color(0,250,0));
		
		this.worker = worker;
		this.previousState = previousState;
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		/* Assign worker from this button to the box location */
		ArrayList<WorkerBox> workerBoxes = currentState.ui.boxes;
		for (WorkerBox box : workerBoxes) {
			if (!box.hasWorker()) {
				box.worker = worker;
				
				context.workers.add(worker);
				return Optional.of(previousState);
			}
		}
		

		return Optional.of(previousState);
		

	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		return Optional.empty();
	}

}