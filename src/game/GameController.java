package game;

import game.states.GameState;
import game.states.impl.StartState;
import processing.core.PApplet;

/**
 * Controller that updates the game state according to real time that has passed.
 *
 */
public class GameController {

	public PApplet parent;
	public DrawEngine drawEngine;
	public GameState state;
	
	private double timeAccumulator;
	private double currentTime;
	private double deltaTime;
	
	public GameController(PApplet parent) {
		this.parent = parent;
		this.drawEngine = new DrawEngine(parent);

		GameContext context = new GameContext();
		this.state = new StartState(context, new GameUI(context));
		
		this.timeAccumulator = 0;
		this.currentTime = System.currentTimeMillis();
		this.deltaTime = GameTime.DELTA_TIME;
	}
	
	/**
	 * Game update step called on every frame.
	 * @param mouseX
	 * @param mouseY
	 */
	public void step(float mouseX, float mouseY) {
		timeAccumulator += getFrameTime();
		
		/* Update the game based on real time elapsed */
		while (timeAccumulator >= deltaTime) {
			state = state.update(mouseX, mouseY);
			timeAccumulator -= deltaTime;
		}
		
		state.display(drawEngine);
	}
	
	/**
	 * Handle input from Processing and pass to the game state.
	 * @param input
	 */
	public void handleInput(GameInput input) {
		state = state.handleInput(input);
	}
	
	
	private double getFrameTime() {
		double newTime = System.currentTimeMillis();
		double frameTime = (newTime - currentTime) / 1000;
		currentTime = newTime;
		
		return frameTime;
	}
	
}
