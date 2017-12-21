package ui;

import game.states.GameState;

import java.util.Optional;

import game.DrawEngine;
import game.GameContext;
import processing.core.PVector;

/**
 * Represents all elements that are drawn on the screen.
 * This assumes all elements are rectangles.
 *
 */
public abstract class UIObject {

	public PVector position;
	
	public boolean mouseOver;
	public int width, height;
	
	
	public UIObject(float xPos, float yPos, int width, int height) {
		this.position = new PVector(xPos, yPos);
		this.mouseOver = false;
		this.width = width;
		this.height = height;
	}
	
	public UIObject(float xPos, float yPos, int size) {
		this(xPos, yPos, size, size);
	}
	
	
	/**
	 * Function to display the object.
	 */
	public abstract void display(DrawEngine drawEngine);
	
	
	
	/**
	 * Check if this object overlaps with another.
	 * @param other - Other object to check overlap.
	 * @return - If the two objects overlap.
	 */
	public boolean overlapsWith(UIObject other) {
		return (this.left() < other.right() && this.right() > other.left()) &&
			   (this.up() < other.down() && this.down() > other.up());
	}
	
	/**
	 * Check if this object is contained within the given x and y coordinates.
	 * @param x
	 * @param y
	 * @return - If x and y are in this object.
	 */
	public boolean contains(float x, float y) {
		return (x > position.x - width && x < position.x + width &&
				y > position.y - height && y < position.y + height);
	}
	
	
	/**
	 * Left click handler for this UI object.
	 * @param mouseX
	 * @param mouseY
	 * @param currentState 
	 * @param context 
	 * @return - Optional.empty() if the game state does change, Optional.of(GameState) if changes to new state.
	 */
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Optional.empty();
	}

	/**
	 * Right click handler for this UI object.
	 * @param mouseX
	 * @param mouseY
	 * @param context
	 * @param currentState
	 * @return - Optional.empty() if the game state does change, Optional.of(GameState) if changes to new state.
	 */
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Optional.empty();
	}
	
	
	public float left() {
		return position.x - width;
	}
	
	public float right() {
		return position.x + width;
	}
	
	public float up() {
		return position.y - height;
	}
	
	public float down() {
		return position.y + height;
	}


}
