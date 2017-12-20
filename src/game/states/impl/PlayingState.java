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
import ui.Tooltip;
import ui.WorkerBox;
import ui.buttons.Button;
import ui.buttons.impl.MenuButton;
import ui.locations.Location;
import ui.locations.impl.GetCoffeeLocation;
import ui.locations.impl.MoreMoneyStatLocation;
import ui.locations.impl.MoreReputationStatLocation;
import ui.locations.impl.RecruitLocation;
import ui.locations.impl.RestLocation;
import ui.menus.Menu;

public class PlayingState extends GameState {
	
	public boolean start, clickReveal, recruitReveal, restReveal, salaryReveal;
	
	public PlayingState(GameContext context, GameUI ui) {
		super(context, ui);
		
		this.start = false;
		this.clickReveal = false;
		this.restReveal = false;
		this.recruitReveal = false;
		this.salaryReveal = false;
	}


	@Override
	public GameState update(float mouseX, float mouseY) {
		super.update(mouseX, mouseY);
		context.timeStep(this);
		
		if (!start) {
			return startReveal();
		}
		
		if (context.gameTime.totalTime() > 0 && !clickReveal) {
			return clickReveal();
		}
		
		if (context.gameTime.totalTime() > 3 && !restReveal) {
			return restReveal();
		}
		
		if (context.gameTime.totalTime() > 5 && !recruitReveal) {
			return recruitReveal();
		}
		
		if (context.gameTime.totalTime() > 10 && !salaryReveal) {
			return salaryReveal();
		}
		
		if (context.gameTime.totalTime() > 15 && !context.moreMoneyReveal) {
			return moreMoneyReveal();
		}
		
		if (context.gameTime.totalTime() > 16 && !context.moreRepReveal) {
			return moreRepReveal();
		}
		
		if (!context.nextStates.isEmpty()){
			return context.nextStates.poll();
		}
	
		return this;
	}


	private GameState startReveal() {
		start = true;
		
		return new InMenuState(new Tooltip(
				"To get a new project, drag your worker to the [Get new project] location. "
				+ "Projects require different skills and give different amounts of money and reputation. "
				+ "Harder projects take longer, but are more rewarding. ",
				200, 200), this);
	}
	
	private GameState clickReveal() {
		clickReveal = true;
		
		return new InMenuState(new Tooltip(
				"You can click on any location in the game to make all the workers there work faster.",
				200, 200), this);
	}
	
	private GameState restReveal() {
		restReveal = true;
		ui.locations.add(new RestLocation(context.gameTime));
		
		return new InMenuState(new Tooltip(
				"Workers will get stressed when they complete any activity. "
				+ "To let workers recover, drag them to the [Restation]. ",
				200, 200), this);
	}
	
	private GameState recruitReveal() {
		recruitReveal = true;
		ui.locations.add(new RecruitLocation(context.gameTime, ui.workerInfos));
		
		return new InMenuState(new Tooltip(
				"You can recruit new workers by dragging a worker to the [Recruitment] location. "
				+ "Note you must have an unlocked worker slot to recruit new workers. "
				+ "Get more worker slots from the [Upgrades] menu. ",
				200, 200), this);
	}
	
	private GameState salaryReveal() {
		salaryReveal = true;
		
		return new InMenuState(new Tooltip(
				"At the end of every month, the salaries of all your workers have to be paid. "
				+ "Each worker can be paid what they have earned pre-emptively from the [Salaries] menu. ",
				200, 200), this);
		
	}

	
	private GameState moreMoneyReveal() {
		context.moreMoneyReveal = true;
		ui.locations.add(new MoreMoneyStatLocation(context.gameTime));
		
		return new InMenuState(new Tooltip(
				"Workers have an entrepreneur stat. "
				+ "This stat gives the worker a chance to increase the money earned when working on a project. "
				+ "To increase this stat for a particular worker, "
				+ "drag their box to the [Entrepreneur 101] location.", 
				200, 200), this);		
	}

	private GameState moreRepReveal() {
		context.moreRepReveal = true;
		ui.locations.add(new MoreReputationStatLocation(context.gameTime));
		
		return new InMenuState(new Tooltip(
				"In addition to the entrepreneur stat, workers have a fame stat. "
				+ "Similar to entrepreneur level, this stat has a chace to increase the reputation when working on a project. "
				+ "To increase this stat, drag the worker to the [Open Source] location", 
				200, 200), this);
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
	

	@Override
	public GameState handleMouseDrag(GameInput input) {
		ui.handleMouseDrag();
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
