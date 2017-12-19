package ui.locations.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.GameTime;
import game.states.GameState;
import objs.activities.Activity;
import objs.activities.impl.RestActivity;
import processing.core.PConstants;
import ui.locations.Location;
import ui.locations.TownLocation;

public class RestLocation extends TownLocation {

	public static final String IMAGE_PREFIX = "imgs/rest";
	
	public RestLocation(GameTime time) {
		super(150, 175, IMAGE_PREFIX, time);
	}

	@Override
	public Activity getActivity() {
		return new RestActivity(this);
	}

}
