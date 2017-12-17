package ui.buttons;

import java.util.Optional;

import game.DrawEngine;
import game.states.GameState;
import processing.core.PConstants;
import processing.core.PVector;
import ui.UIObject;

public abstract class Button extends UIObject {
		
	public Button(float xPos, float yPos, int width, int height, int col) {
		super(xPos, yPos, width, height, col);
	}
	
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(PConstants.RADIUS, col, position.x, position.y, width, height);		
	}

}
