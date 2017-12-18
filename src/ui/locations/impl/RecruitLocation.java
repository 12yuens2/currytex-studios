package ui.locations.impl;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.RecruitActivity;
import processing.core.PConstants;
import ui.locations.Location;

public class RecruitLocation extends Location {

	public RecruitLocation() {
		super(570, 200, TOWN_LOCATION_SIZE, DrawEngine.parent.color(100,200,200));
	}

	@Override
	public Activity getActivity() {
		return new RecruitActivity(this);
	}

	@Override
	public boolean canAddWorker() {
		//TODO cannot get new worked until unlockeds
		//TODO cannot add worker if no avaiable slots
		return true;
//		return numWorkers == 0; TODO
	}


	@Override
	public void display(DrawEngine drawEngine) {		
//		drawEngine.drawImage(PConstants.CENTER, drawEngine.recruitLocation, position.x, position.y);
		super.display(drawEngine);
		drawEngine.drawText(16, "Recruit location", position.x, position.y, DrawEngine.BLACK);
	}


}
