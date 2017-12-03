package game;

import app.DevStudios;
import game.states.GameState;
import game.states.impl.BoxTestState;
import game.states.impl.InMenuState;
import game.states.impl.StartState;
import processing.core.PApplet;
import ui.Menu;
import ui.MenuButton;;

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
		context.openMenuButtons.add(new MenuButton(200, 700, 100, 30, parent.color(20, 20, 200), 
				 								   new Menu(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 300, parent)));

		context.openMenuButtons.add(new MenuButton(500, 700, 100, 30, parent.color(20, 200, 20),
				 								   new Menu(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 300, 300, parent)));
		
		this.state = new StartState(parent, context);
//		this.state = new InMenuState(new Menu(600, 400, 700, 300, parent), parent);
		
		this.timeAccumulator = 0;
		this.currentTime = System.currentTimeMillis();
		this.deltaTime = 0.2;
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
