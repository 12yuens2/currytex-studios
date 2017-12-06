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
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 300);
		this.worker = worker;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(12, "Worker name: " + worker.name, position.x, position.y, DrawEngine.parent.color(0));
		
		float pos = position.y + 20;
		for (Entry<Skill, Level> entry : worker.skills.entrySet()) {
			Level level = entry.getValue();
			String skillDisplay = entry.getKey().toString() + ": " + level.level + ", " + level.exp + "/" + level.expToLevel;
			
			drawEngine.drawText(12, skillDisplay, position.x, pos, DrawEngine.parent.color(0));
			pos += 20;
		}
	}
	
//
//	@Override
//	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
//		
//		return Optional.empty();
//	}

}
