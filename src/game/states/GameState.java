package game.states;

import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import processing.core.PApplet;

public abstract class GameState {

	public PApplet parent;
	public GameContext context;
	
	public GameState(PApplet parent, GameContext context) {
		this.parent = parent;
		this.context = context;
	}
	
	public void display(DrawEngine drawEngine) {
		context.display(drawEngine);
	}
	
	public abstract GameState update(float mouseX, float mouseY);

	
	//TODO delegate switch statements to this function and only implement the individual handlers
	public abstract GameState handleInput(GameInput input); 
}
