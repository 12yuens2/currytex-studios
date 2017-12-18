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

	
	public RestLocation() {
		super(150, 175, TOWN_LOCATION_SIZE, DrawEngine.parent.color(200, 200, 100));
	}

	@Override
	public Activity getActivity() {
		return new RestActivity(this);
	}

	@Override
	public void display(DrawEngine drawEngine) {
//		drawEngine.drawImage(PConstants.CENTER, drawEngine.restLocation, position.x, position.y);
		super.display(drawEngine);
		drawEngine.drawText(16, "Rest location", position.x, position.y, DrawEngine.BLACK);		
	}


}
