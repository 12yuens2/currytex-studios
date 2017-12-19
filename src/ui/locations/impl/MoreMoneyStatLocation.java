package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.MoreMoneyStatActivity;
import processing.core.PConstants;
import ui.locations.Location;

public class MoreMoneyStatLocation extends Location {

	public MoreMoneyStatLocation() {
		super(275, 450, TOWN_LOCATION_SIZE, DrawEngine.parent.color(150, 200, 50));
	}

	@Override
	public Activity getActivity() {
		return new MoreMoneyStatActivity(this);
	}

	
	@Override
	public void display(DrawEngine drawEngine) {
		if(image) {
			drawEngine.drawImage(PConstants.CENTER, drawEngine.moreMoneyLocation, position.x, position.y);
		}
		else {
			super.display(drawEngine);
		}		
	}

}
