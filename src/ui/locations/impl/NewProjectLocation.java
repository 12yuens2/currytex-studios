package ui.locations.impl;

import java.util.ArrayList;

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
			if (projectLocation.project == null) {
				return new GettingProjectActivity(this, projectLocation);
			}
		}
		return null;
	}

	@Override
	public boolean canAddWorker() {
		for (ProjectLocation projectLocation : projectLocations) {
			if (projectLocation.project == null) {
				return true;
			}
		}
		return false;
	}

}
