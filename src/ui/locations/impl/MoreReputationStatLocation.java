package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.MoreReputationStatActivity;
import ui.locations.Location;

public class MoreReputationStatLocation extends Location {

	public MoreReputationStatLocation() {
		super(700, 400, 75, DrawEngine.parent.color(150, 50, 150));
	}

	@Override
	public Activity getActivity() {
		return new MoreReputationStatActivity(this);
	}

	@Override
	public boolean canAddWorker() {
		return true;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(16, "More Reputation Stat", position.x, position.y, DrawEngine.parent.color(0));
		
	}

}
