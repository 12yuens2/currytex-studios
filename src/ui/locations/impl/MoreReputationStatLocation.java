package ui.locations.impl;

import game.GameTime;
import objs.activities.Activity;
import objs.activities.impl.MoreReputationStatActivity;
import ui.locations.TownLocation;

public class MoreReputationStatLocation extends TownLocation {

	public static final String IMAGE_PREFIX = "imgs/reputation";
	
	public MoreReputationStatLocation(GameTime time) {
		super(750, 460, IMAGE_PREFIX, time);
	}

	@Override
	public Activity getActivity() {
		return new MoreReputationStatActivity(this);
	}

}
