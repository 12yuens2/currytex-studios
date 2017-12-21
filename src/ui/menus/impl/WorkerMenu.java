package ui.menus.impl;


import app.CurryTeXStudios;
import game.DrawEngine;
import objs.workers.Worker;
import ui.menus.Menu;


/**
 * Menu to show worker details.
 *
 */
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
