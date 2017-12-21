package ui.buttons.impl;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import game.states.impl.InMenuState;
import objs.activities.impl.ProjectActivity;
import ui.Tooltip;
import ui.buttons.Button;
import ui.locations.impl.ProjectLocation;

public class ChooseProjectButton extends Button {

	public ProjectActivity project;
	public ProjectLocation location;
	public GameState previousState;
	
	public ChooseProjectButton(float xPos, float yPos, ProjectActivity project, ProjectLocation location, GameState previousState) {
		super(xPos, yPos, 100, 30);
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
		
		/* Tutorial tooltip */
		if (!context.projectReveal) {
			previousState.projectReveal();
			return Optional.of(new InMenuState(new Tooltip(
					"To work on a project, drag the worker to the corresponding project box. "
					+ "Each time a worker works on a project, they complete 1 feature. ", 
					200, 200), 
					
					new InMenuState(new Tooltip(
							"Workers with matching skills work faster on a project. "
							+ "Workers gain experience in that skill as they work on projects, "
							+ "but only their 2 best skills are active.",
							200, 200), previousState)));
		}
		else {
			return Optional.of(previousState);
		}
	
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(30, "Select", position.x, position.y, DrawEngine.BLACK);
	}

}
