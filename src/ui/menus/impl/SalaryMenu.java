package ui.menus.impl;

import java.util.ArrayList;

import app.CurryTeXStudios;
import game.DrawEngine;
import objs.workers.Worker;
import processing.core.PConstants;
import ui.WorkerInfo;
import ui.buttons.impl.PaySalaryButton;
import ui.menus.Menu;

public class SalaryMenu extends Menu {

	public ArrayList<WorkerInfo> workerInfos;
	
	public SalaryMenu(ArrayList<WorkerInfo> workerInfos) {
		super(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, 400, 375);
		
		this.workerInfos = workerInfos;
		
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(30, "Salary", position.x, position.y - height + 75, DrawEngine.BLACK);
		
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Worker", 
				position.x - width + 50, position.y - height + 125, DrawEngine.BLACK);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Unpaid Salary", 
				position.x - width + 250, position.y - height + 125, DrawEngine.BLACK);
		
		drawWorkerSalaries(drawEngine);
		
	}
	
	private void drawWorkerSalaries(DrawEngine drawEngine) {
		buttons = new ArrayList<>();
		ArrayList<Worker> workers = new ArrayList<>();
		for (WorkerInfo workerInfo : workerInfos) {
			if (workerInfo.worker != null) workers.add(workerInfo.worker);
		}
		

		int xPos = (int) (position.x - width + 50);
		int yPos = (int) (position.y - height + 175);
		
		int salarySum = 0;
		for (Worker worker : workers) {
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, worker.name, xPos, yPos, DrawEngine.BLACK);
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ""+worker.salary, xPos + 200, yPos, DrawEngine.BLACK);
			
			buttons.add(new PaySalaryButton(xPos + 450, yPos, worker));
			
			salarySum += worker.salary;
			yPos += 50;
		}
		
		/* Draw total */
		yPos += 25;
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, "Total salary: ", xPos, yPos, DrawEngine.BLACK);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ""+salarySum, xPos + 200, yPos, DrawEngine.BLACK);
		buttons.add(new PaySalaryButton(xPos + 450, yPos, workers));
		
	}

}
