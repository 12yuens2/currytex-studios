package ui.locations.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.activities.Activity;
import objs.activities.impl.RestActivity;
import processing.core.PConstants;
import ui.locations.Location;

public class RestLocation extends Location {

	
	public RestLocation(float xPos, float yPos, int size, int col) {
		super(xPos, yPos, 75, DrawEngine.parent.color(200, 200, 100));
	}

	@Override
	public Activity getActivity() {
		return new RestActivity(this);
	}

	@Override
	public boolean canAddWorker() {
		return true; //TODO all locations global max workers
	}

	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(16, "Rest location", position.x, position.y, DrawEngine.parent.color(0));		
	}


}
