package objs;

import java.util.HashMap;

import game.DrawEngine;

public class Studio {

	public int currency;
	public HashMap<Skill, Integer> reputation;

	public Studio() {
		reputation = new HashMap<>();
	}
	
	public void addReputation(Skill skill, int reputation) {
		if (this.reputation.containsKey(skill)) {
			this.reputation.put(skill, this.reputation.get(skill) + reputation);
		} else {
			this.reputation.put(skill, reputation);
		}
	}
	
	public void display(DrawEngine drawEngine) {
		drawEngine.drawText(16, "$: " + currency, 800, 50, DrawEngine.parent.color(0));
		drawEngine.drawText(16, "R: " + reputation, 1000, 50, DrawEngine.parent.color(0));
	}
}
