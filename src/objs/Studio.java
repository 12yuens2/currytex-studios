package objs;

import java.util.HashMap;

import game.DrawEngine;
import objs.workers.Skill;
import processing.core.PConstants;

public class Studio {

	public int currency, coffee, totalReputation, reputationGoal;
	public HashMap<Skill, Integer> reputation;

	public Studio() {
		this.currency = 100;
		this.coffee = 20;
		this.totalReputation = 0;
		this.reputationGoal = 1000;
		this.reputation = new HashMap<>();
	}
	
	public void addReputation(Skill skill, int reputation) {
		if (this.reputation.containsKey(skill)) {
			this.reputation.put(skill, this.reputation.get(skill) + reputation);
		} else {
			this.reputation.put(skill, reputation);
		}
	}
	
	public void display(DrawEngine drawEngine) {
		drawEngine.drawImage(PConstants.CENTER, drawEngine.moneyIcon, 600, 25);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ": "+currency, 625, 25, DrawEngine.BLACK);
		
		drawEngine.drawImage(PConstants.CENTER, drawEngine.reputationIcon, 800, 25);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ": " + totalReputation + " / " + reputationGoal, 
				825, 25, DrawEngine.BLACK);
		
		drawEngine.drawImage(PConstants.CENTER, drawEngine.caffeineIcon, 1100, 25);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ": " + coffee, 1125, 25, DrawEngine.BLACK);
	}
}
