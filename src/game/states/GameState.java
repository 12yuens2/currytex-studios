package game.states;

import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.GameUI;
import ui.locations.impl.GetCoffeeLocation;

/**
 * GameState which makes a simple state machine to represent the main game loop.
 *
 */
public abstract class GameState {

	public GameContext context;
	public GameUI ui;
	
	public GameState(GameContext context, GameUI ui) {
		this.context = context;
		this.ui = ui;
		ui.resetWorkerBoxes();
	}
	
	
	/**
	 * Update step for the game.
	 * @param mouseX
	 * @param mouseY
	 * @return Next game state after the update
	 */
	public GameState update(float mouseX, float mouseY) {
		ui.updateMouse(mouseX, mouseY);
		
		return this;
	}


	/**
	 * Draw function of the game state.
	 * @param drawEngine
	 */
	public void display(DrawEngine drawEngine) {
		ui.display(drawEngine);
	}
	
	
	/**
	 * Input handler of the game state.  
	 * @param input
	 * @return Next game state after handling the input
	 */
	public GameState handleInput(GameInput input) {
		switch (input.mouseAction) {
			case MOUSE_DRAG: 	return handleMouseDrag(input);
			case MOUSE_PRESS: 	return handleMousePress(input);
			case MOUSE_RELEASE:	return handleMouseRelease(input);
			default:			return this;
		}
	}
	
	/**
	 * Handler for MOUSE_DRAG action.
	 * @param input
	 * @return Next game state after handling the drag action
	 */
	public abstract GameState handleMouseDrag(GameInput input);
	
	
	/**
	 * Handler for MOUSE_PRESS action.
	 * @param input
	 * @return Next game state after handling the press action
	 */
	public abstract GameState handleMousePress(GameInput input);
	
	
	/**
	 * Handler for MOUSE_RELEASE action.
	 * @param input
	 * @return Next game state after handling the release action
	 */
	public abstract GameState handleMouseRelease(GameInput input);

	
	
/*
 * Tutorial reveals
 */
	public void coffeeReveal() {
		context.coffeeReveal = true;
		ui.locations.add(new GetCoffeeLocation(context.gameTime));
	}
	
	public void projectReveal() {
		context.projectReveal = true;
	}

	public void multipleReveal() {
		context.multipleReveal = true;		
	}

}
