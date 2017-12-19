package ui.menus.impl;

import java.util.Map.Entry;
import java.util.Optional;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Level;
import objs.Skill;
import objs.Worker;
import processing.core.PApplet;
import ui.menus.Menu;

public class WorkerMenu extends Menu {

	public Worker worker;
	
	public WorkerMenu(Worker worker) {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 300, 300);
		this.worker = worker;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		worker.menuDisplay(drawEngine, position.copy().add(-width + 55, -height + 55));

		
	}
	
// TODO fire
//	@Override
//	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
//		
//		return Optional.empty();
//	}

}
