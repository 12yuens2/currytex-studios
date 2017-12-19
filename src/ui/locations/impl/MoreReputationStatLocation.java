package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.MoreReputationStatActivity;
import processing.core.PConstants;
import ui.locations.Location;

public class MoreReputationStatLocation extends Location {

	public MoreReputationStatLocation() {
		super(750, 460, TOWN_LOCATION_SIZE, DrawEngine.parent.color(150, 50, 150));
	}

	@Override
	public Activity getActivity() {
		return new MoreReputationStatActivity(this);
	}

	
	@Override
	public void display(DrawEngine drawEngine) {
		if (image) {
			super.display(drawEngine);
			drawEngine.drawImage(PConstants.CENTER, drawEngine.moreRepLocation, position.x, position.y);
		}
		else {
			super.display(drawEngine);
		}
		drawEngine.drawText(16, "More Reputation Stat", position.x, position.y, DrawEngine.BLACK);
		
	}

}
