package game;

import java.util.ArrayList;

import app.DevStudios;
import objs.Studio;
import placeholder.Box;
import placeholder.Location;
import processing.core.PApplet;
import ui.Button;
import ui.Menu;
import ui.MenuButton;

public class GameContext {
	
	public ArrayList<MenuButton> openMenuButtons;

	public ArrayList<Location> locations; /* Represents projects */
	public ArrayList<Box> boxes; /* Represents workers */
	

	public GameTime gameTime;
	public Studio studio;
	
	public GameContext(PApplet parent) {
		this.gameTime = new GameTime();
		this.studio = new Studio();
		
		this.locations = new ArrayList<>();
		this.boxes = new ArrayList<>();
		
		this.openMenuButtons = new ArrayList<>();
		
		testInit(parent);
	}
	
	private void testInit(PApplet parent) {
		openMenuButtons.add(new MenuButton(200, 700, 100, 30, parent.color(20, 20, 200), 
				   new Menu(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 300, parent)));

		openMenuButtons.add(new MenuButton(500, 700, 100, 30, parent.color(20, 200, 20),
				   new Menu(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 300, 300, parent)));
		
		boxes.add(new Box(100, 100, 50));
		boxes.add(new Box(100, 300, 50));
		boxes.add(new Box(100, 500, 50));
		
		locations.add(new Location(1000, 150, 100));
		locations.add(new Location(1000, 540, 100));
	}

	
	public void updateGameTime() {
		gameTime.incrementTimestep();
	}
	
	public void display(DrawEngine drawEngine) {
		for (MenuButton b : openMenuButtons) {
			b.display(drawEngine);
		}
		
		for (Location l : locations) {
			l.display(drawEngine);
		}
		
		for (Box b : boxes) {
			b.display(drawEngine);
		}
 		
	}

	public void updateBoxes(float mouseX, float mouseY) {
		for (Box b : boxes) {
			if (mouseX > b.position.x - b.size && mouseX < b.position.x + b.size &&
				mouseY > b.position.y - b.size && mouseY < b.position.y + b.size) {
				
				b.mouseOver = true;
			} 
			else {
				b.mouseOver = false;
			}
			
			b.integrate();
		}
		
	}
}
