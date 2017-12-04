package placeholder;

import java.util.ArrayList;

import game.DrawEngine;
import game.GameTime;
import objs.Level;
import objs.Skill;
import objs.Worker;
import objs.activities.impl.Project;
import objs.activities.impl.Project.Difficulty;
import processing.core.PConstants;
import processing.core.PVector;
import ui.menus.Menu;
import ui.menus.impl.WorkerMenu;

public class Box extends GameObject {

	public final PVector originalPosition;
	public float mouseXOffset, mouseYOffset;
	public boolean disabled, mouseOver, mouseLocked;
	
	public float timer;
	
	/* Developer associated with this box */
	public Worker worker;
	
	public Location location;
	
	
	public Box(float xPos, float yPos, int size, Worker worker){
		super(xPos, yPos, size);
		this.originalPosition = position.copy();
		
		this.worker = worker;
		
		this.timer = 0;
		
		this.mouseXOffset = 0f;
		this.mouseYOffset = 0f;
		this.mouseOver = false;
		this.mouseLocked = false;
	}
	
	/**
	 * Create Box with no worker.
	 * @param xPos
	 * @param yPos
	 * @param size
	 */
	public Box(float xPos, float yPos, int size) {
		this(xPos, yPos, size, null);
	}
	
	public void integrate() {
		if (timer > 0) {
			timer-= GameTime.MINUTES_PER_TIMESTEP;
			if (timer <= 0) {
				location.completeWorkload();
				
				for (Skill s : location.project.skillsRequired) {
					if (worker.skills.containsKey(s)) {
						worker.skills.get(s).exp += location.project.expGain;
					} 
					else {
						worker.skills.put(s, new Level());						
					}
				}
				
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
		
		Project locationProject = location.project;
		
		/* Decrease project time based on worker skill */
		double timeNeeded = location.project.timePerWork;
		for (Skill s : worker.skills.keySet()) {
			if (locationProject.skillsRequired.contains(s)) {
				timeNeeded *= (1.0 / worker.skills.get(s).level);
			}
		}
		
		timer = 1 + (int) timeNeeded;
		
		location.addWorker();
	}
	
	public void display(DrawEngine drawEngine) {
		if (worker != null) {
			int col = disabled ? drawEngine.parent.color(0, 155, 0) : drawEngine.parent.color(0, 255, 0);
			drawEngine.drawSquare(PConstants.RADIUS, col, position, size);
			drawEngine.drawRectangle(PConstants.CORNER, drawEngine.parent.color(0,0,180), position.x, position.y + 30, timer, 20);
			drawEngine.drawText(16, worker.name, position.x, position.y + size/2, drawEngine.parent.color(0));
		}
		
		/* Vacant worker spot */
		else {
			int col = drawEngine.parent.color(200, 150, 0);
			drawEngine.drawSquare(PConstants.RADIUS, col, position, size);
		}
	}


	public Menu workerDetailsMenu() {
		return new WorkerMenu(worker);
	}
}
