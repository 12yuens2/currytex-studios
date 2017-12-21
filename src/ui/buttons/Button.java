package ui.buttons;

import game.DrawEngine;
import processing.core.PConstants;
import ui.UIObject;


/**
 * Class that represents a button on the UI.
 *
 */
public abstract class Button extends UIObject {
		
	public Button(float xPos, float yPos, int width, int height) {
		super(xPos, yPos, width, height);
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawImage(PConstants.CENTER, drawEngine.resizedBox(width*2, height*2), position.x, position.y);
	}

}
