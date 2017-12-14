package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.GetCoffeeActivity;
import ui.locations.Location;

public class GetCoffeeLocation extends Location {

	public GetCoffeeLocation() {
		super(1000, 400, 75, DrawEngine.parent.color(50,150,200));
	}

	@Override
	public Activity getActivity() {
		return new GetCoffeeActivity(this);
	}

	@Override
	public boolean canAddWorker() {
		return true; //TODO max workers in one location
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(16, "Cafe", position.x, position.y, DrawEngine.parent.color(0));
	}

}
