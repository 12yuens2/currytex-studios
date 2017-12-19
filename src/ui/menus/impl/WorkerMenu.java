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
		worker.menuDisplay(drawEngine, position.copy().add(-width + 25, -height + 25));
//		int xPos = (int) (position.x - width + 150);
//		int yPos = (int) (position.y - height + 50);
//			
//		for (String property : worker.getProperties()) {
//			drawEngine.drawText(16, property, xPos, yPos, DrawEngine.BLACK);
//			yPos += 30;
//		}
//		xPos += 250;
		
	}
	
//
//	@Override
//	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
//		
//		return Optional.empty();
//	}

}
