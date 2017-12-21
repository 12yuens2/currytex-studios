package ui.menus.impl;

import java.util.ArrayList;

import app.CurryTeXStudios;
import game.DrawEngine;
import game.states.GameState;
import objs.activities.impl.ProjectActivity;
import objs.factories.WorkerFactory;
import objs.workers.Worker;
import processing.core.PConstants;
import processing.core.PVector;
import ui.WorkerBox;
import ui.buttons.impl.ChooseWorkerButton;
import ui.menus.Menu;

public class RecruitMenu extends Menu {

	public ArrayList<Worker> newRecruits;
	
	public RecruitMenu(GameState previousState) {
		super(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, 500, 450);
		
		this.newRecruits = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Worker newWorker = WorkerFactory.getRandomWorker(previousState.context.studio, previousState.context.gameTime);
			newRecruits.add(newWorker);
			
			float yPos = (position.y - height + 200) + (250 * (i));
			buttons.add(new ChooseWorkerButton((position.x - width + 750), yPos, newWorker, previousState));
		}
	}
	
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(30, "Recruitment", position.x, position.y - height + 50, DrawEngine.BLACK);
		
		PVector pos = new PVector(position.x - width + 50, position.y - height + 125);
		for (Worker worker : newRecruits) {
			worker.menuDisplay(drawEngine, pos.copy());
			pos.add(new PVector(0, 255));
		}
	}

}
