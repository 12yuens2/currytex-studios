package ui.locations.impl;

import java.util.ArrayList;

import game.GameTime;
import objs.activities.Activity;
import objs.activities.impl.RecruitActivity;
import ui.WorkerInfo;
import ui.locations.TownLocation;

public class RecruitLocation extends TownLocation {
	
	public static final String IMAGE_PREFIX = "imgs/recruit";
	
	public ArrayList<WorkerInfo> workerInfos;
	
	public RecruitLocation(GameTime time, ArrayList<WorkerInfo> workerInfos) {
		super(570, 200, IMAGE_PREFIX, time);
		
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

}
