package ui.menus.impl;

import java.util.Map.Entry;

import app.DevStudios;
import game.DrawEngine;
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
		drawEngine.drawText(12, "Worker name: " + worker.name, position.x, position.y, drawEngine.parent.color(0));
		
		float pos = position.y + 20;
		for (Entry<Skill, Level> entry : worker.skills.entrySet()) {
			drawEngine.drawText(12, entry.getKey().toString() + ": " + entry.getValue().level, position.x, pos, drawEngine.parent.color(0));
			pos += 20;
		}
	}

}
