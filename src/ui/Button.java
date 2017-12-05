package ui;

import game.DrawEngine;
import processing.core.PConstants;
import processing.core.PVector;

public class Button extends UIObject {
	
	
	public Button(float xPos, float yPos, int width, int height, int col) {
		super(xPos, yPos, width, height, col);
	}
	
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.RADIUS, col, position.x, position.y, width, height);
	}


	public void activate() {
		System.out.println(this + " was pressed.");
	}
}
