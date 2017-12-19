package ui.locations.impl;

import game.DrawEngine;
import game.GameTime;
import objs.activities.Activity;
import objs.activities.impl.MoreMoneyStatActivity;
import processing.core.PConstants;
import ui.locations.Location;
import ui.locations.TownLocation;

public class MoreMoneyStatLocation extends TownLocation {

	public static final String IMAGE_PREFIX = "imgs/money";
	
	public MoreMoneyStatLocation(GameTime time) {
		super(275, 450, IMAGE_PREFIX, time);
	}

	@Override
	public Activity getActivity() {
		return new MoreMoneyStatActivity(this);
	}

}
