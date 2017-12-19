package ui.buttons.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.activities.impl.ProjectActivity;
import processing.core.PConstants;
import ui.buttons.Button;
import ui.locations.impl.ProjectLocation;

public class ChooseProjectButton extends Button {

	public ProjectActivity project;
	public ProjectLocation location;
	public GameState previousState;
	
	public ChooseProjectButton(float xPos, float yPos, ProjectActivity project, ProjectLocation location, GameState previousState) {
		super(xPos, yPos, 100, 30, DrawEngine.parent.color(0,250,0));
		this.project = project;
		this.location = location;
		this.previousState = previousState;
	}

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		location.project = project;
		project.location = location;
		
		context.activeProjects.add(project);
		
		return Optional.of(previousState);
	
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		return Optional.empty();
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(30, "Select", position.x, position.y, DrawEngine.BLACK);
	}

}
