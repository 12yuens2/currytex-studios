package ui;

import java.util.ArrayList;

import game.DrawEngine;
import processing.core.PConstants;
import processing.core.PVector;

public class Menu {

	public PVector position;
	public int width, height;
	
	ArrayList<Button> buttons;
	
	public Menu(float xPos, float yPos, int size) {
		this.position = new PVector(xPos, yPos);
		this.width = size;
		this.height = size;
		
		this.buttons = new ArrayList<>();
	}
	
	public Menu(float xPos, float yPos, int width, int height) {
		this(xPos, yPos, width);
		this.width = width;
		this.height = height;
	}
	
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.RADIUS, drawEngine.parent.color(150), position.x, position.y, width, height);
		
		for (Button b : buttons) {
			b.display(drawEngine);
		}
	}
}
