package ui.buttons.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import ui.buttons.Button;

public class ExitButton extends Button {

	public GameState previousState;
	
	public ExitButton(float xPos, float yPos, int width, int height, GameState previousState) {
		super(xPos, yPos, width, height, DrawEngine.parent.color(255, 0, 0));
		this.previousState = previousState;
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Optional.of(previousState);
	}

}
