package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.MoreMoneyStatActivity;
import ui.locations.Location;

public class MoreMoneyStatLocation extends Location {

	public MoreMoneyStatLocation() {
		super(400, 400, 75, DrawEngine.parent.color(150, 200, 50));
	}

	@Override
	public Activity getActivity() {
		return new MoreMoneyStatActivity(this);
	}

	@Override
	public boolean canAddWorker() {
		return true;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(16, "More Money Stat", position.x, position.y, DrawEngine.parent.color(0));
		
	}

}
