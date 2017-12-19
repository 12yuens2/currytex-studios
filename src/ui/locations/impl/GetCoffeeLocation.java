package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.GetCoffeeActivity;
import processing.core.PConstants;
import ui.locations.Location;

public class GetCoffeeLocation extends Location {

	public GetCoffeeLocation() {
		super(1050, 275, TOWN_LOCATION_SIZE, DrawEngine.parent.color(50,150,200));
	}

	@Override
	public Activity getActivity() {
		return new GetCoffeeActivity(this);
	}

	@Override
	public void display(DrawEngine drawEngine) {
//		drawEngine.drawImage(PConstants.CENTER, drawEngine.cafeLocation, position.x, position.y);
		super.display(drawEngine);
		drawEngine.drawText(16, "Cafe", position.x, position.y, DrawEngine.BLACK);
	}

}
