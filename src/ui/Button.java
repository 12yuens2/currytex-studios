package ui;

import game.DrawEngine;
import processing.core.PConstants;
import processing.core.PVector;

public class Button {

	public PVector position;
	
	public int width, height, col;
	
	public Button(float xPos, float yPos, int width, int height, int col) {
		this.position = new PVector(xPos, yPos);
		this.col = col;
		this.width = width;
		this.height = height;
	}
	
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.RADIUS, col, position.x, position.y, width, height);
	}
	
	public boolean contains(float x, float y) {
		return (x > position.x - width && x < position.x + width &&
				y > position.y - height && y < position.y + height);
	}

	public void activate() {
		System.out.println(this + " was pressed.");
	}
}
