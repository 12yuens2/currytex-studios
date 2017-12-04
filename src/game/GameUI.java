package game;

import java.util.ArrayList;

import app.DevStudios;
import objs.Level;
import objs.Skill;
import objs.Worker;
import placeholder.Box;
import placeholder.Location;
import processing.core.PApplet;
import ui.MenuButton;
import ui.menus.Menu;

public class GameUI {

	public GameContext context;
	
	public ArrayList<MenuButton> openMenuButtons;
	public ArrayList<Location> locations; /* Represents projects */
	public ArrayList<Box> boxes; /* Represents workers */
	
	public GameUI(GameContext context, PApplet parent) {
		this.context = context;
		
		this.openMenuButtons = new ArrayList<>();
		this.locations = new ArrayList<>();
		this.boxes = new ArrayList<>();
		
		testInit(parent);
	}

	private void testInit(PApplet parent) {
		openMenuButtons.add(new MenuButton(200, 700, 100, 30, parent.color(20, 20, 200), 
				   new Menu(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 300)));

		openMenuButtons.add(new MenuButton(500, 700, 100, 30, parent.color(20, 200, 20),
				   new Menu(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 300, 300)));
		
		//TODO debug
		Worker a = new Worker("a");
		Level java = new Level();
		java.level = 2;
		a.skills.put(Skill.JAVA, java);
		
		boxes.add(new Box(100, 100, 50, a));
		boxes.add(new Box(100, 300, 50));
		boxes.add(new Box(100, 500, 50));
		
		locations.add(new Location(1000, 150, 100));
		locations.add(new Location(1000, 540, 100));
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
			
//			b.integrate();
		}
		
	}
	
}
