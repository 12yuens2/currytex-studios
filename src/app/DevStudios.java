package app;

import game.GameController;
import game.GameInput;
import game.GameTime;
import game.GameInput.MouseAction;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class DevStudios extends PApplet {

	public static final int SCREEN_X = 1600;
	public static final int SCREEN_Y = 900;
	
	GameController controller;
	PImage bg;
	
	public void settings() {
		size(SCREEN_X, SCREEN_Y);
	}

	public void setup() {
		frameRate(60);
		controller = new GameController(this);	
		bg = loadImage("imgs/background.png");
	}

	public void draw() { 
		background(bg);
//		for (int x = 0; x < SCREEN_X; x += 50) {
//			for (int y = 0; y < SCREEN_Y; y += 50) {
//				line(x, 0, x, SCREEN_Y);
//				line(0, y, SCREEN_X, y);
//			}
//		}
		
		
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
		return new GameInput(mouseX, mouseY, mouseButton, -1, mouseAction);
	}
	
	
	public static void main(String[] args) {
		PApplet.main("app.DevStudios", args);
	}

}
