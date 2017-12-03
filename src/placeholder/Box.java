package placeholder;

import java.util.ArrayList;

import game.DrawEngine;
import game.GameTime;
import objs.Project;
import objs.Project.Difficulty;
import processing.core.PConstants;
import processing.core.PVector;

public class Box extends GameObject {

	public final PVector originalPosition;
	public float mouseXOffset, mouseYOffset;
	public boolean disabled, mouseOver, mouseLocked;
	
	public float timer;
	
	public Location location;
	
	
	public Box(float xPos, float yPos, int size){
		super(xPos, yPos, size);
		this.originalPosition = position.copy();
		
		this.timer = 0;
		
		this.mouseXOffset = 0f;
		this.mouseYOffset = 0f;
		this.mouseOver = false;
		this.mouseLocked = false;
		
	}
	
	public void integrate() {
		if (timer > 0) {
			timer-= GameTime.MINUTES_PER_TIMESTEP;
			if (timer <= 0) {
				location.completeWorkload();
				location = null;
				disabled = false;
			}
		}
		
		
	}
	
	public void enterIfPossible(ArrayList<Location> locations) {
		if (mouseLocked) {
			mouseLocked = false;
			for (Location l : locations) {
				if (overlapsWith(l) && l.project.workRequired > 0) {
					enterLocation(l);
				}
			}
			position = originalPosition.copy();
		}
	}
	
	private void enterLocation(Location location) {
		this.location = location;
		disabled = true;
		timer = location.project.timePerWork;
		

		location.addWorker();
	}
	
	public void display(DrawEngine drawEngine) {
		int col = disabled ? drawEngine.parent.color(0, 155, 0) : drawEngine.parent.color(0, 255, 0);
		drawEngine.drawSquare(PConstants.RADIUS, col, position, size);
		drawEngine.drawRectangle(PConstants.CORNER, drawEngine.parent.color(0,0,180), position.x, position.y + 30, timer, 20);
	}
}
