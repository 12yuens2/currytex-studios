package game.states.impl;

import game.DrawEngine;
import game.GameInput;
import game.states.GameState;
import ui.Menu;

public class StartState extends GameState {

	Menu menu;
	
	public StartState() {
		this.menu = new Menu(600, 300, 500, 200);
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		menu.display(drawEngine);
		
	}

	@Override
	public GameState update(float mouseX, float mouseY) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public GameState handleInput(GameInput input) {
		// TODO Auto-generated method stub
		return this;
	}

}
