package game.states.impl;

import java.util.ArrayList;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.GameUI;
import game.states.GameState;
import javafx.scene.shape.Arc;
import objs.activities.impl.ProjectActivity;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import ui.WorkerBox;
import ui.buttons.Button;
import ui.buttons.impl.MenuButton;
import ui.locations.Location;
import ui.menus.Menu;

public class PlayingState extends GameState {

	
	public PlayingState(GameContext context, GameUI ui) {
		super(context, ui);
	}
	
//	@Override
//	public void display(DrawEngine drawEngine) {
//		super.display(drawEngine);
//	}

	@Override
	public GameState update(float mouseX, float mouseY) {
		super.update(mouseX, mouseY);
		context.timeStep(this);
		
		if (!context.nextStates.isEmpty()){
			return context.nextStates.poll();
		}
	
		return this;
	}


	@Override
	public GameState handleMouseDrag(GameInput input) {
		ui.handleMouseDrag();
		return this;
	}

	@Override
	public GameState handleMousePress(GameInput input) {
		if (input.mouseButton == PConstants.RIGHT) {
			return handleRightClick();
		}
		else if (input.mouseButton == PConstants.LEFT) {
			return handleLeftClick();
		}
		return this;
	}

	@Override
	public GameState handleMouseRelease(GameInput input) {
		for (WorkerBox b : ui.boxes) {
			ArrayList<Location> locations = new ArrayList<>();
			locations.addAll(ui.locations);
			locations.addAll(ui.projectLocations);
			
			b.enterIfPossible(locations);
		}
		return this;
	}
	
	private GameState handleLeftClick() {
		GameState nextState = ui.handleLeftClick(this);
		
		return nextState;
	}
	
	private GameState handleRightClick() {
		return ui.handleRightClick(this);
	}
	

}
