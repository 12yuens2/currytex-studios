package game;

import game.states.GameState;
import game.states.impl.BoxTestState;
import game.states.impl.StartState;
import processing.core.PApplet;;

public class GameController {

	public PApplet parent;
	public DrawEngine drawEngine;
	public GameState state;
	
	public GameController(PApplet parent) {
		this.parent = parent;
		this.drawEngine = new DrawEngine(parent);
//		this.state = new BoxTestState();
		this.state = new StartState();
	}
	
	public void step(float mouseX, float mouseY) {
		state.display(drawEngine);
		state = state.update(mouseX, mouseY);
	}
	
	public void handleInput(GameInput input) {
		state.handleInput(input);
	}
	
}
