package game.states.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.GameUI;
import game.states.GameState;
import processing.core.PApplet;
import ui.buttons.Button;
import ui.buttons.impl.ExitButton;
import ui.menus.Menu;

public class InMenuState extends GameState {

	public Menu menu;
	
	public InMenuState(Menu menu, GameState previousState) {
		super(previousState.context, previousState.ui);
		this.menu = menu;
		this.menu.initExit(previousState);
//		this.previousState = previousState;
//		this.exitMenuButton = new ExitButton(menu.position.x + menu.width, menu.position.y - menu.height, 
//										 20, 20, DrawEngine.parent.color(250, 0, 0));
		
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		menu.display(drawEngine);
	}

	@Override
	public GameState update(float mouseX, float mouseY) {
		super.update(mouseX, mouseY);
		menu.update(mouseX, mouseY);
		
		return this;
	}

//	@Override
//	public GameState handleInput(GameInput input) {
//		switch (input.mouseAction) {
//			case NONE:
//				
//				break;
//				
//			case MOUSE_RELEASE:
//				if (exitMenuButton.contains(input.mouseX, input.mouseY)) {
//					return previousState;
//				}
//				menu.clicked(input);
//				break;			
//		}
//		return this;
//	}

	@Override
	public GameState handleMouseDrag(GameInput input) {
		return this;
	}

	@Override
	public GameState handleMousePress(GameInput input) {
		return this;
	}

	@Override
	public GameState handleMouseRelease(GameInput input) {
		Optional<GameState> result = menu.handleLeftClick(input.mouseX, input.mouseY, context, this);
		
		return result.isPresent() ? result.get() : this;
//		menu.clicked(input);
//		if (exitMenuButton.contains(input.mouseX, input.mouseY)) {
//			return previousState;
//		}
//		return this;
	}

}
