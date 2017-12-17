package ui.menus.impl;

import java.util.ArrayList;

import app.DevStudios;
import game.DrawEngine;
import game.states.GameState;
import objs.Worker;
import objs.activities.impl.ProjectActivity;
import objs.factories.WorkerFactory;
import ui.WorkerBox;
import ui.buttons.impl.ChooseWorkerButton;
import ui.menus.Menu;

public class RecruitMenu extends Menu {

	public ArrayList<Worker> newRecruits;
	
	public RecruitMenu(GameState previousState) {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 700, 400);
		
		this.newRecruits = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Worker newWorker = WorkerFactory.getRandomWorker();
			newRecruits.add(newWorker);
			
			float xPos = (position.x - width + 150) * (i + 1);
			buttons.add(new ChooseWorkerButton(xPos, position.y, newWorker, previousState));
		}
	}
	
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		int xPos = (int) (position.x - width + 150);
		for (Worker newWorker : newRecruits) {
			int yPos = (int) (position.y - height + 50);
			
			for (String property : newWorker.getProperties()) {
				drawEngine.drawText(16, property, xPos, yPos, DrawEngine.BLACK);
				yPos += 30;
			}
			xPos += 250;
		}
	}

}
