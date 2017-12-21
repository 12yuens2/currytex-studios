package ui.buttons.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import processing.core.PConstants;
import ui.buttons.Button;


/**
 * Exit button for menus.
 * Returns to the previous state before the menu was opened. 
 *
 */
public class ExitButton extends Button {

	public GameState previousState;
	
	public ExitButton(float xPos, float yPos, GameState previousState) {
		super(xPos, yPos, 20, 20);
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
}
