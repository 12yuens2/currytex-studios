package ui.menus;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.states.GameState;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import ui.UIObject;
import ui.buttons.Button;
import ui.buttons.impl.ExitButton;

public class Menu extends UIObject {

	public float mouseX, mouseY;
	
	public ArrayList<Button> buttons;
	public ExitButton exitButton;
	
	public Menu(float xPos, float yPos, int width, int height) {
		super(xPos, yPos, width, height, DrawEngine.parent.color(150));
		
		this.buttons = new ArrayList<>();
		
	}
	public Menu(float xPos, float yPos, int size) {
		this(xPos, yPos, size, size);
	}
	
	public void initExit(GameState previousState) {
		this.exitButton = new ExitButton(position.x + width - 20, position.y - height + 25, previousState);
	}

	
	/**
	 * Draw menu box and buttons.
	 */
	@Override
	public void display(DrawEngine drawEngine) {
		drawEngine.drawImage(PConstants.CENTER, drawEngine.resizedBox(width*2, height*2), position.x, position.y);
		
		for (Button b : buttons) {
			b.display(drawEngine);
		}
		
		exitButton.display(drawEngine);
	}

	public void update(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Stream.concat(buttons.stream(), Stream.of(exitButton))
				.filter(button -> button.contains(mouseX, mouseY))
				.map(button -> button.handleLeftClick(mouseX, mouseY, context, currentState))
				.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
				.findFirst();
	}
	
	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {

		return Optional.empty();
	}

}
