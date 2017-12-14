package objs;

import java.util.HashMap;

import game.DrawEngine;

public class Studio {

	public int currency, coffee;
	public HashMap<Skill, Integer> reputation;

	public Studio() {
		this.currency = 10000;
		this.coffee = 5;
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
		drawEngine.drawText(16, "$: " + currency, 800, 25, DrawEngine.parent.color(0));
		drawEngine.drawText(16, "R: " + reputation, 1000, 25, DrawEngine.parent.color(0));
		drawEngine.drawText(16, "C: " + coffee, 1200, 25, DrawEngine.parent.color(0));
	}
}
