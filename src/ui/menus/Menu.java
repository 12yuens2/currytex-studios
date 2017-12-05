package ui.menus;

import java.util.ArrayList;

import game.DrawEngine;
import game.GameInput;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import ui.Button;
import ui.UIObject;

public class Menu extends UIObject {

	public float mouseX, mouseY;
	
	public ArrayList<Button> buttons;
	
	public Menu(float xPos, float yPos, int width, int height) {
		super(xPos, yPos, width, height, DrawEngine.parent.color(150));
		this.buttons = new ArrayList<>();
		
	}
	public Menu(float xPos, float yPos, int size) {
		this(xPos, yPos, size, size);
	}

	
	/**
	 * Draw menu box and buttons.
	 */
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.RADIUS, col, position.x, position.y, width, height);
		
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
