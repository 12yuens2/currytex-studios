package ui.locations.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.activities.Activity;
import objs.activities.impl.RecruitActivity;
import ui.locations.Location;

public class RecruitLocation extends Location {

	public RecruitLocation(float xPos, float yPos, int size, int col) {
		super(xPos, yPos, 75, DrawEngine.parent.color(100,200,200));
	}

	@Override
	public Activity getActivity() {
		return new RecruitActivity();
	}

	@Override
	public boolean canAddWorker() {
		return true;
//		return numWorkers == 0; TODO
	}

	@Override
	public void addWorker() {
		// numerWorkers++; //TODO
		
	}

	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(16, "Recruit location", position.x, position.y, DrawEngine.parent.color(0));
	}


}
