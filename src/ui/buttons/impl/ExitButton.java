package ui.buttons.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import processing.core.PConstants;
import ui.buttons.Button;

public class ExitButton extends Button {

	public GameState previousState;
	
	public ExitButton(float xPos, float yPos, GameState previousState) {
		super(xPos, yPos, 20, 20, DrawEngine.parent.color(255, 0, 0));
		this.previousState = previousState;
	}

	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawImage(PConstants.CENTER, drawEngine.exitButton, position.x, position.y);
	}
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Optional.of(previousState);
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Optional.empty();
	}

}
