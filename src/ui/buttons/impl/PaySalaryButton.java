package ui.buttons.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.workers.Worker;
import ui.buttons.Button;

public class PaySalaryButton extends Button {

	public List<Worker> workers;
	
	public PaySalaryButton(float xPos, float yPos, List<Worker> workers) {
		super(xPos, yPos, 80, 20);
		this.workers = workers;
	}
	
	public PaySalaryButton(float xPos, float yPos, Worker worker) {
		this(xPos, yPos, Arrays.asList(worker));
	}

	
	@Override
	public void display(DrawEngine drawEngine) {
		int sumToPay = sumToPay();
		if (sumToPay > 0) {
			super.display(drawEngine);
			drawEngine.drawText(16, "Pay", position.x, position.y, DrawEngine.BLACK);
		}
	}
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		int sumToPay = sumToPay();
		if (context.studio.currency >= sumToPay && sumToPay > 0) {
			context.studio.currency -= sumToPay;
			
			for (Worker worker : workers) {
				worker.salary = 0;
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		return Optional.empty();
	}

	
	private int sumToPay() {
		int sum = 0;
		for (Worker worker : workers) {
			sum += worker.salary;
		}
		
		return sum;
	}
}
