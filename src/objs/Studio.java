package objs;

import game.DrawEngine;

public class Studio {

	public int currency;
	public int reputation;

	public void display(DrawEngine drawEngine) {
		drawEngine.drawText(16, "$: " + currency, 800, 50, DrawEngine.parent.color(0));
		drawEngine.drawText(16, "R: "+reputation, 1000, 50, DrawEngine.parent.color(0));
	}
}
