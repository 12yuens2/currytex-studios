package ui.locations.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.activities.Activity;
import objs.activities.impl.RestActivity;
import processing.core.PConstants;
import ui.locations.Location;

public class RestLocation extends Location {

	public RestLocation(float xPos, float yPos, int size, int col) {
		super(xPos, yPos, 75, DrawEngine.parent.color(200, 200, 100));
	}

	@Override
	public Activity getActivity() {
		return new RestActivity();
	}

	@Override
	public boolean canAddWorker() {
		return true; //TODO all locations global max workers
	}

	@Override
	public void addWorker() {
		/* Nothing to do */		
	}

	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawSquare(PConstants.RADIUS, col, position, width);
		drawEngine.drawText(16, "Rest location", position.x, position.y, DrawEngine.parent.color(0));		
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		return Optional.empty();
	}

}
