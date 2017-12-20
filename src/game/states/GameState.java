package game.states;

import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.GameUI;
import processing.core.PApplet;

public abstract class GameState {

	public GameContext context;
	public GameUI ui;
	
	public GameState(GameContext context, GameUI ui) {
		this.context = context;
		this.ui = ui;
		ui.resetWorkerBoxes();
	}
	
	public void display(DrawEngine drawEngine) {
		ui.display(drawEngine);
	}
	
	public GameState update(float mouseX, float mouseY) {
		ui.updateMouse(mouseX, mouseY);
		
		return this;
	}

	
	public GameState handleInput(GameInput input) {
		GameState nextGameState = this;
		switch (input.mouseAction) {
			case NONE:
				//TODO
				break;
			case MOUSE_DRAG:
				nextGameState = handleMouseDrag(input);
				break;
			case MOUSE_PRESS:
				nextGameState = handleMousePress(input);
				break;
			case MOUSE_RELEASE:
				nextGameState = handleMouseRelease(input);
				break;
		
		}
		
		return nextGameState;
		
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public abstract GameState handleMouseDrag(GameInput input);
	
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public abstract GameState handleMousePress(GameInput input);
	
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public abstract GameState handleMouseRelease(GameInput input);

}
