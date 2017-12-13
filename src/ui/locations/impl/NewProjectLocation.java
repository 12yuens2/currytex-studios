package ui.locations.impl;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Occurs;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.GettingProjectActivity;
import ui.locations.Location;

public class NewProjectLocation extends Location {

	ArrayList<ProjectLocation> projectLocations;
	
	public NewProjectLocation(ArrayList<ProjectLocation> projectLocations) {
		super(700, 200, 75, DrawEngine.parent.color(200, 100, 150));
		this.projectLocations = projectLocations;
	}
	
	public NewProjectLocation(float xPos, float yPos, int size, int col) {
		super(xPos, yPos, size, col);
	}

	@Override
	public Activity getActivity() {
		for (ProjectLocation projectLocation : projectLocations) {
			if (!projectLocation.occupied) {
				projectLocation.occupied = true;
				return new GettingProjectActivity(this, projectLocation);
			}
		}
		return null;
	}

	@Override
	public boolean canAddWorker() {
		for (ProjectLocation projectLocation : projectLocations) {
			if (!projectLocation.occupied) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(16, "Get new project", position.x, position.y, DrawEngine.parent.color(0));
	}

}
