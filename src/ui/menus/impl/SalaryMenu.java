package ui.menus.impl;

import java.util.ArrayList;

import app.DevStudios;
import game.DrawEngine;
import objs.Worker;
import processing.core.PConstants;
import ui.WorkerInfo;
import ui.buttons.impl.PaySalaryButton;
import ui.menus.Menu;

public class SalaryMenu extends Menu {

	public ArrayList<WorkerInfo> workerInfos;
	
	public SalaryMenu(ArrayList<WorkerInfo> workerInfos) {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 400, 400);
		
		this.workerInfos = workerInfos;
		
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Worker", 
				position.x - width + 50, position.y - height + 50, DrawEngine.BLACK);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Unpaid Salary", 
				position.x - width + 250, position.y - height + 50, DrawEngine.BLACK);
		
		drawWorkerSalaries(drawEngine);
		
	}
	
	private void drawWorkerSalaries(DrawEngine drawEngine) {
		buttons = new ArrayList<>();
		ArrayList<Worker> workers = new ArrayList<>();
		for (WorkerInfo workerInfo : workerInfos) {
			if (workerInfo.worker != null) workers.add(workerInfo.worker);
		}
		

		int xPos = (int) (position.x - width + 50);
		int yPos = (int) (position.y - height + 150);
		
		for (Worker worker : workers) {
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, worker.name, xPos, yPos, DrawEngine.BLACK);
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ""+worker.salary, xPos + 200, yPos, DrawEngine.BLACK);
			
			buttons.add(new PaySalaryButton(xPos + 450, yPos, worker));
			
			yPos += 50;
		}
	}

}
