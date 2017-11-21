import processing.core.PApplet;

public class DevStudios extends PApplet {

	public static final int SCREEN_X = 1280;
	public static final int SCREEN_Y = 720;
	
	
	int value = 0;
	
	public void settings() {
		size(SCREEN_X, SCREEN_Y);
	}
	
	float bx;
	float by;
	int boxSize = 75;
	boolean overBox = false;
	boolean locked = false;
	float xOffset = 0f; 
	float yOffset = 0f; 

	public void setup() {
	  bx = (float) (SCREEN_X/2.0);
	  by = (float) (SCREEN_Y/2.0);
	  rectMode(RADIUS);  
	}

	public void draw() { 
	  background(0);
	  
	  // Test if the cursor is over the box 
	  if (mouseX > bx-boxSize && mouseX < bx+boxSize && 
	      mouseY > by-boxSize && mouseY < by+boxSize) {
	    overBox = true;  
	    if(!locked) { 
	      stroke(255); 
	      fill(153);
	    } 
	  } else {
	    stroke(153);
	    fill(153);
	    overBox = false;
	  }
	  
	  // Draw the box
	  rect(bx, by, boxSize, boxSize);
	}

	public void mousePressed() {
	  if(overBox) { 
	    locked = true; 
	    fill(255, 255, 255);
	  } else {
	    locked = false;
	  }
	  xOffset = mouseX-bx; 
	  yOffset = mouseY-by; 

	}

	public void mouseDragged() {
	  if(locked) {
	    bx = mouseX-xOffset; 
	    by = mouseY-yOffset; 
	  }
	}

	public void mouseReleased() {
	  locked = false;
	}
	
	
	public static void main(String[] args) {
		PApplet.main("DevStudios", args);
	}

}
