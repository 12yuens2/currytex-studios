package ui.buttons.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Worker;
import ui.buttons.Button;

public class PaySalaryButton extends Button {

	public Worker worker;
	
	public PaySalaryButton(float xPos, float yPos, Worker worker) {
		super(xPos, yPos, 80, 20, DrawEngine.parent.color(0,250,50));
		this.worker = worker;
	}

	
	@Override
	public void display(DrawEngine drawEngine) {
		if (worker.salary > 0) {
			super.display(drawEngine);
			drawEngine.drawText(16, "Pay", position.x, position.y, DrawEngine.BLACK);
		}
	}
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		if (context.studio.currency >= worker.salary && worker.salary > 0) {
			context.studio.currency -= worker.salary;
			worker.salary = 0;
		}
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		return Optional.empty();
	}

}
