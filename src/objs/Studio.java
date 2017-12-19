package objs;

import java.util.HashMap;

import game.DrawEngine;
import processing.core.PConstants;

public class Studio {

	public int currency, coffee, totalReputation;
	public HashMap<Skill, Integer> reputation;

	public Studio() {
		this.currency = 100;
		this.coffee = 20;
		this.totalReputation = 0;
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
		drawEngine.drawImage(PConstants.CENTER, drawEngine.moneyIcon, 700, 25);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ": "+currency, 725, 25, DrawEngine.BLACK);
		
		drawEngine.drawImage(PConstants.CENTER, drawEngine.reputationIcon, 900, 25);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ": " + totalReputation, 925, 25, DrawEngine.BLACK);
		
		drawEngine.drawImage(PConstants.CENTER, drawEngine.caffineIcon, 1100, 25);
		drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, ": " + coffee, 1125, 25, DrawEngine.BLACK);
	}
}
