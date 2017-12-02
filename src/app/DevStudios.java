package app;
import java.util.ArrayList;

import game.DrawEngine;
import game.GameController;
import game.GameInput;
import game.GameInput.MouseAction;
import processing.core.PApplet;
import processing.core.PVector;

public class DevStudios extends PApplet {

	public static final int SCREEN_X = 1600;
	public static final int SCREEN_Y = 900;
	
	GameController controller;
	
	public void settings() {
		size(SCREEN_X, SCREEN_Y);
	}

	public void setup() {
		controller = new GameController(this);	
	}

	public void draw() { 
		background(0);
		controller.step(mouseX, mouseY);
	}
	
	public void mousePressed() {
		controller.handleInput(newMouseInput(MouseAction.MOUSE_PRESS));
	}
	
	public void mouseDragged() {
		controller.handleInput(newMouseInput(MouseAction.MOUSE_DRAG));
	}
	
	public void mouseReleased() {
		controller.handleInput(newMouseInput(MouseAction.MOUSE_RELEASE));
	}
	
	private GameInput newMouseInput(MouseAction mouseAction) {
		return new GameInput(mouseY, mouseY, mouseButton, -1, mouseAction);
	}
	
	
	public static void main(String[] args) {
		PApplet.main("app.DevStudios", args);
	}

}
