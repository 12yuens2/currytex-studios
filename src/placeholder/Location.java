package placeholder;
import game.DrawEngine;
import objs.Project;
import objs.Project.Difficulty;
import processing.core.PConstants;

public class Location extends GameObject {

	public int numWorkers;
	public Project project;
	
	public Location(float xPos, float yPos, int size) {
		super(xPos, yPos, size);
		
		this.numWorkers = 0;
		this.project = Project.randomProject();
		
	}

	public void display(DrawEngine drawEngine) {
		drawEngine.drawSquare(PConstants.RADIUS, drawEngine.parent.color(255,0,0), position, size);
		
		if (project != null) {
			drawEngine.drawText(12, ""+project.workRequired, position.x, position.y, drawEngine.parent.color(0));
		} else {
			drawEngine.drawText(12, "?", position.x, position.y, drawEngine.parent.color(0));
		}
	}
	
	public void finishProject() {
		
	}

	public void completeWorkload() {
		if (project != null) {
			numWorkers--;
			if (project.workRequired <= 0 && numWorkers <= 0) {
				project = null;
			}
		}
		
	}

	public void addWorker() {
		project.workRequired--;
		numWorkers++;		
	}

}
