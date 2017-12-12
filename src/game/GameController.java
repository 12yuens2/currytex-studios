package game;

import app.DevStudios;
import game.states.GameState;
import game.states.impl.InMenuState;
import game.states.impl.StartState;
import processing.core.PApplet;
import ui.buttons.impl.MenuButton;
import ui.menus.Menu;;

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

		GameContext context = new GameContext(parent);
		
		this.state = new StartState(context, new GameUI(context));
//		this.state = new InMenuState(new Menu(600, 400, 700, 300, parent), parent);
		
		this.timeAccumulator = 0;
		this.currentTime = System.currentTimeMillis();
		this.deltaTime = GameTime.DELTA_TIME;
	}
	
	public void step(float mouseX, float mouseY) {
		timeAccumulator += getFrameTime();
		
		while (timeAccumulator >= deltaTime) {
			state = state.update(mouseX, mouseY);
			timeAccumulator -= deltaTime;
		}
		
		state.display(drawEngine);
	}
	
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
