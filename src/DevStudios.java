import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class DevStudios extends PApplet {

	public static final int SCREEN_X = 1280;
	public static final int SCREEN_Y = 720;
	
	
	int value = 0;
	
	public void settings() {
		size(SCREEN_X, SCREEN_Y);
	}
	
//	int boxSize = 75;
//	boolean overBox = false;
//	boolean locked = false;
//	float xOffset = 0f; 
//	float yOffset = 0f; 
	
	ArrayList<Box> boxes;
	Box draggingBox;

	public void setup() {
		boxes = new ArrayList<Box>();
		boxes.add(new Box(100, 100, 50));
		boxes.add(new Box(100, 300, 50));
		boxes.add(new Box(100, 500, 50));
		rectMode(RADIUS);  
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
		
	//	  if (mouseX > bx-boxSize && mouseX < bx+boxSize && 
	//	      mouseY > by-boxSize && mouseY < by+boxSize) {
	//	    overBox = true;  
	////	    if(!locked) { 
	////	      stroke(255); 
	////	      fill(153);
	////	    } 
	//	  } else {
	////	    stroke(153);
	////	    fill(153);
//	    overBox = false;
//	  }
	  
		stroke(153);
		fill(153);
	  
	  // Draw the box
	    for (Box b : boxes) {
	    	rect(b.position.x, b.position.y, b.size, b.size);
	    }
	}

	public void mousePressed() {
		for (Box b : boxes) {
			if (b.mouseOver) b.mouseLocked = true;
			else b.mouseLocked = false;
			
			b.mouseXOffset = mouseX - b.position.x;
			b.mouseYOffset = mouseY - b.position.y;
		}
		
//		if(overBox) { 
//	    locked = true; 
//	    fill(255, 255, 255);
//	  } else {
//	    locked = false;
//	  }
//		xOffset = mouseX-bx; 
//		yOffset = mouseY-by; 

	}

	public void mouseDragged() {
		for (Box b : boxes) {
			if (b.mouseLocked) {
				b.position = new PVector(mouseX - b.mouseXOffset, mouseY - b.mouseYOffset);
			}
		}
//	  if(locked) {
//	    bx = mouseX-xOffset; 
//	    by = mouseY-yOffset; 
//	  }
	}

	public void mouseReleased() {
		for (Box b : boxes) {
			if (b.mouseLocked) {
				b.mouseLocked = false;
				b.position = b.absolutePosition.copy();
			}
		}
//	  locked = false;
//	  bx = boxX;
//	  by = boxY;
	}
	
	
	public static void main(String[] args) {
		PApplet.main("DevStudios", args);
	}

}
