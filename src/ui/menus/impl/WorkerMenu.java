package ui.menus.impl;

import java.util.Map.Entry;
import java.util.Optional;

import app.CurryTeXStudios;
import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.workers.Level;
import objs.workers.Skill;
import objs.workers.Worker;
import processing.core.PApplet;
import ui.menus.Menu;

public class WorkerMenu extends Menu {

	public Worker worker;
	
	public WorkerMenu(Worker worker) {
		super(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, 300, 300);
		this.worker = worker;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(30, "Worker " + worker.name, position.x, position.y - height + 50, DrawEngine.BLACK);
		
		worker.menuDisplay(drawEngine, position.copy().add(-width + 55, -height + 125));

		
	}
	
// TODO fire
//	@Override
//	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
//		
//		return Optional.empty();
//	}

}
