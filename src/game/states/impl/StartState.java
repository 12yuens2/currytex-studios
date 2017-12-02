package game.states.impl;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.states.GameState;
import processing.core.PApplet;
import ui.Button;
import ui.Menu;
import ui.MenuButton;

public class StartState extends GameState {

	
	public StartState(PApplet parent, GameContext context) {
		super(parent, context);

	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
	}

	@Override
	public GameState update(float mouseX, float mouseY) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public GameState handleInput(GameInput input) {
		switch (input.mouseAction) {			
			case MOUSE_RELEASE:
				for (MenuButton b : context.openMenuButtons) {
					if (b.contains(input.mouseX, input.mouseY)) {
						return new InMenuState(parent, context, b.menu, this);
					}
				}

				break;			
		}
		return this;
	}

}
