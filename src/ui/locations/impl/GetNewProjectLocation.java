package ui.locations.impl;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Occurs;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.GettingProjectActivity;
import ui.locations.Location;

public class GetNewProjectLocation extends Location {

	ArrayList<ProjectLocation> projectLocations;
	
	public GetNewProjectLocation(ArrayList<ProjectLocation> projectLocations) {
		super(1175, 705, LOCATION_SIZE, "imgs/box.png");
		this.projectLocations = projectLocations;
	}

	@Override
	public Activity getActivity() {
		for (ProjectLocation projectLocation : projectLocations) {
			if (!projectLocation.occupied && projectLocation.project == null) {
				projectLocation.occupied = true;
				return new GettingProjectActivity(this, projectLocation);
			}
		}
		return null;
	}

	@Override
	public boolean canAddWorker() {
		for (ProjectLocation projectLocation : projectLocations) {
			if (!projectLocation.occupied && projectLocation.project == null) {
				return super.canAddWorker();
			}
		}
		return false;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(16, "Get new project", position.x, position.y, DrawEngine.BLACK);
	}

}
