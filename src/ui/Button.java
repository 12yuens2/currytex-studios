package ui;

import game.DrawEngine;
import processing.core.PConstants;
import processing.core.PVector;

public class Button {

	public PVector position;
	
	public int width, height;
	
	public Button(float xPos, float yPos, int width, int height) {
		this.position = new PVector(xPos, yPos);
		this.width = width;
		this.height = height;
	}
	
	public void display(DrawEngine drawEngine) {
		int col = drawEngine.parent.color(200, 0, 0);
		drawEngine.drawRectangle(PConstants.RADIUS, col, position.x, position.y, width, height);
	}
}
