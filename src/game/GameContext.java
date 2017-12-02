package game;

import java.util.ArrayList;

import ui.Button;
import ui.Menu;
import ui.MenuButton;

public class GameContext {
	
	public ArrayList<MenuButton> openMenuButtons;
	
	public GameContext() {
		this.openMenuButtons = new ArrayList<>();
	}

	public void display(DrawEngine drawEngine) {
		for (MenuButton b : openMenuButtons) {
			b.display(drawEngine);
		}
		
	}
}
