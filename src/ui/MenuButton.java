package ui;

import ui.menus.Menu;

public class MenuButton extends Button{

	public Menu menu;
	
	public MenuButton(float xPos, float yPos, int width, int height, int col, Menu menu) {
		super(xPos, yPos, width, height, col);
		this.menu = menu;
	}

}
