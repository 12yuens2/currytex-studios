import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class DevStudios extends PApplet {

	public static final int SCREEN_X = 1280;
	public static final int SCREEN_Y = 720;
	
	DrawEngine drawEngine;
	
	public void settings() {
		size(SCREEN_X, SCREEN_Y);
	}

	ArrayList<Box> boxes;
	ArrayList<Location> locations;
	Box draggingBox;

	public void setup() {
		drawEngine = new DrawEngine(this);
		
		boxes = new ArrayList<Box>();
		locations = new ArrayList<Location>();
		
		boxes.add(new Box(100, 100, 50));
		boxes.add(new Box(100, 300, 50));
		boxes.add(new Box(100, 500, 50));
		
		locations.add(new Location(700, 150, 100, 200));
		locations.add(new Location(700, 450, 100, 300));
		
	}

	public void draw() { 
		background(0);
	  
		// Test if the cursor is over the box
		for (Box b : boxes) {
			if (mouseX > b.position.x - b.size && mouseX < b.position.x + b.size &&
				mouseY > b.position.y - b.size && mouseY < b.position.y + b.size) {
				
				b.mouseOver = true;
			} else {
				b.mouseOver = false;
			}
		}
	  
		stroke(153);
		fill(153);
	  
	  // Draw the box	    
	    for (Location l : locations) {
	    	l.display(drawEngine);
	    }
	    
	    for (Box b : boxes) {
	    	b.integrate();
	    	b.display(drawEngine);
	    }
	}

	public void mousePressed() {
		for (Box b : boxes) {
			if (b.mouseOver && !b.disabled) b.mouseLocked = true;
			else b.mouseLocked = false;
			
			b.mouseXOffset = mouseX - b.position.x;
			b.mouseYOffset = mouseY - b.position.y;
		}

	}

	public void mouseDragged() {
		for (Box b : boxes) {
			if (b.mouseLocked) {
				b.position = new PVector(mouseX - b.mouseXOffset, mouseY - b.mouseYOffset);
			}
		}
	}

	public void mouseReleased() {
		for (Box b : boxes) {
			if (b.mouseLocked) {
				b.mouseLocked = false;
				for (Location l : locations) {
					if (b.overlapsWith(l)) {
						b.enterLocation(l.timeNeeded);
					}
				}
				b.position = b.originalPosition.copy();
			}
		}
	}
	
	
	public static void main(String[] args) {
		PApplet.main("DevStudios", args);
	}

}
