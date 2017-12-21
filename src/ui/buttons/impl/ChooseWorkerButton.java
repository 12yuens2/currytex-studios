package ui.buttons.impl;

import java.util.ArrayList;
import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.GameModifiers;
import game.states.GameState;
import game.states.impl.InMenuState;
import objs.workers.Addiction;
import objs.workers.Worker;
import ui.Tooltip;
import ui.WorkerInfo;
import ui.buttons.Button;

public class ChooseWorkerButton extends Button {

	public Worker worker;
	public GameState previousState;
	
	public ChooseWorkerButton(float xPos, float yPos, Worker worker, GameState previousState) {
		super(xPos, yPos, 100, 30);
		
		this.worker = worker;
		this.previousState = previousState;
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		/* Assign worker from this button to the box location */
		ArrayList<WorkerInfo> workerBoxes = currentState.ui.workerInfos;
		for (WorkerInfo box : workerBoxes) {
			if (!box.hasWorker() && !box.locked) {
				box.setWorker(worker);
				
				context.workers.add(worker);
				
				/* Coffee reveal */
				if (worker.addictionLevel != Addiction.NONE && !context.coffeeReveal) {
					previousState.coffeeReveal();
					return Optional.of(new InMenuState(new Tooltip(
							"Some workers may have levels of coffee addition. "
							+ "These workers need to drink coffee when they work on projects. "
							+ "If you have no coffee available in the company, "
							+ "they become more stressed and have a chance to stop working altogether. "
							+ "To get more coffee for the company, send any worker to the [Cafe]. "
							+ "This gets coffee for anyone who wants to drink it while working on a project.",
							250, 200), previousState));
				}
				
				if (!context.multipleReveal) {
					previousState.multipleReveal();
					return Optional.of(new InMenuState(new Tooltip(
							"Multiple workers can be dragged to the same location. "
							+ "The maximum number of workers per location is " + GameModifiers.locationMaxWorkers + " "
							+ "This number can be increased from the [Upgrades] menu. ",
							200, 200), previousState));
				}
				return Optional.of(previousState);
			}
		}
		
		return Optional.of(previousState);
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(30, "Select", position.x, position.y, DrawEngine.BLACK);
	}
}
