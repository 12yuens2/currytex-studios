package game.states.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameInput;
import game.states.GameState;
import ui.menus.Menu;

/**
 * GameState where a menu is displayed and time does not pass.
 *
 */
public class InMenuState extends GameState {

	public Menu menu;
	
	/**
	 * @param menu - Menu to be displayed
	 * @param previousState - Previous GameState to return to when the menu is closed
	 */
	public InMenuState(Menu menu, GameState previousState) {
		super(previousState.context, previousState.ui);
		this.menu = menu;
		this.menu.initExit(previousState);		
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
	}

}
