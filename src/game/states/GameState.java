package game.states;

import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import processing.core.PApplet;

public abstract class GameState {

	public PApplet parent;
	public GameContext context;
	
	public float mouseX, mouseY;
	
	public GameState(PApplet parent, GameContext context) {
		this.parent = parent;
		this.context = context;
	}
	
	public void display(DrawEngine drawEngine) {
		context.display(drawEngine);
	}
	
	public GameState update(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		System.out.println(context.gameTime);
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
	
	public abstract GameState handleMouseDrag(GameInput input);
	
	public abstract GameState handleMousePress(GameInput input);
	
	public abstract GameState handleMouseRelease(GameInput input);

}
