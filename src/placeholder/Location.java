package placeholder;
import game.DrawEngine;
import processing.core.PConstants;

public class Location extends GameObject {

	public int timeNeeded;
	
	public Location(float xPos, float yPos, int size, int timeNeeded) {
		super(xPos, yPos, size);
		this.timeNeeded = timeNeeded;
		
	}

	public void display(DrawEngine drawEngine) {
		drawEngine.drawSquare(PConstants.RADIUS, drawEngine.parent.color(255,0,0), position, size);
	}

}
