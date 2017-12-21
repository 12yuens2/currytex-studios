package ui.locations.impl;

import game.GameTime;
import objs.activities.Activity;
import objs.activities.impl.RestActivity;
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
