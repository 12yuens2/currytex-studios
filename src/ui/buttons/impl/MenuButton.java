package ui.buttons.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import game.states.impl.InMenuState;
import ui.buttons.Button;
import ui.menus.Menu;

public class MenuButton extends Button{

	public String name;
	public Menu menu;
	
	public MenuButton(String name, float xPos, float yPos, int width, int height, Menu menu) {
		super(xPos, yPos, width, height);
		this.name = name;
		this.menu = menu;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(18, name, position.x, position.y, DrawEngine.BLACK);
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState state) {
		if (menu != null) {
			return Optional.of(new InMenuState(menu, state));
		}
		
		return Optional.empty();
	}
	
}
