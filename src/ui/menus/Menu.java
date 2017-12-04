package ui.menus;

import java.util.ArrayList;

import game.DrawEngine;
import game.GameInput;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import ui.Button;

public class Menu {

	public PVector position;
	public float mouseX, mouseY;
	public int width, height;
	
	public ArrayList<Button> buttons;
	
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

	public void update(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	public void clicked(GameInput input) {
		for (Button b : buttons) {
			if (b.contains(input.mouseX, input.mouseY)) {
				b.activate();
			}
		}
		
	}
}
