package ui.buttons.impl;

import java.util.Optional;

import game.GameContext;
import game.states.GameState;
import game.states.impl.InMenuState;
import ui.buttons.Button;
import ui.menus.Menu;

public class MenuButton extends Button{

	public Menu menu;
	
	public MenuButton(float xPos, float yPos, int width, int height, int col, Menu menu) {
		super(xPos, yPos, width, height, col);
		this.menu = menu;
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState state) {
		if (menu != null) {
			return Optional.of(new InMenuState(menu, state));
		}
		
		return Optional.empty();
	}

}
