package game;

import java.util.ArrayList;

import ui.Button;
import ui.Menu;
import ui.MenuButton;

public class GameContext {
	
	public GameTime gameTime;
	public ArrayList<MenuButton> openMenuButtons;
	
	public GameContext() {
		this.gameTime = new GameTime();
		this.openMenuButtons = new ArrayList<>();
	}

	
	public void updateGameTime() {
		gameTime.incrementTimestep();
	}
	
	public void display(DrawEngine drawEngine) {
		for (MenuButton b : openMenuButtons) {
			b.display(drawEngine);
		}
		
	}
}
