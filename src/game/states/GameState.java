package game.states;

import game.DrawEngine;
import game.GameInput;

public abstract class GameState {

	public GameState() {
		
	}
	
	public abstract void display(DrawEngine drawEngine);
	
	public abstract GameState update(float mouseX, float mouseY);

	public abstract GameState handleInput(GameInput input); 
}
