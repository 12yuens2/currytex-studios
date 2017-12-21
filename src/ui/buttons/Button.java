package ui.buttons;

import java.util.Optional;

import game.DrawEngine;
import game.states.GameState;
import processing.core.PConstants;
import processing.core.PVector;
import ui.UIObject;

public abstract class Button extends UIObject {
		
	public Button(float xPos, float yPos, int width, int height) {
		super(xPos, yPos, width, height);
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawImage(PConstants.CENTER, drawEngine.resizedBox(width*2, height*2), position.x, position.y);
	}

}
