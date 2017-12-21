package ui.locations.impl;

import game.GameTime;
import objs.activities.Activity;
import objs.activities.impl.GetCoffeeActivity;
import ui.locations.TownLocation;

public class GetCoffeeLocation extends TownLocation {

	public static final String IMAGE_PREFIX = "imgs/cafe";
	
	public GetCoffeeLocation(GameTime time) {
		super(1050, 275, IMAGE_PREFIX, time);
	}

	@Override
	public Activity getActivity() {
		return new GetCoffeeActivity(this);
	}
	
}
