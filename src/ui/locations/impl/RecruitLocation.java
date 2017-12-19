package ui.locations.impl;

import java.util.ArrayList;

import game.DrawEngine;
import objs.activities.Activity;
import objs.activities.impl.RecruitActivity;
import processing.core.PConstants;
import ui.WorkerInfo;
import ui.locations.Location;

public class RecruitLocation extends Location {

	public ArrayList<WorkerInfo> workerInfos;
	
	public RecruitLocation(ArrayList<WorkerInfo> workerInfos) {
		super(570, 200, TOWN_LOCATION_SIZE, DrawEngine.parent.color(100,200,200));
		
		this.workerInfos = workerInfos;
	}

	@Override
	public Activity getActivity() {
		return new RecruitActivity(this);
	}

	@Override
	public boolean canAddWorker() {
		for (WorkerInfo info : workerInfos) {
			if (super.canAddWorker() && !info.locked && !info.hasWorker()) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void display(DrawEngine drawEngine) {		
//		drawEngine.drawImage(PConstants.CENTER, drawEngine.recruitLocation, position.x, position.y);
		super.display(drawEngine);
		drawEngine.drawText(16, "Recruit location", position.x, position.y, DrawEngine.BLACK);
	}


}
