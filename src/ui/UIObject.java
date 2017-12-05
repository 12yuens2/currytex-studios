package ui;
import game.DrawEngine;
import processing.core.PVector;

/**
 * Assume all gameobjects are rectangles
 * @author sy35
 *
 */
public abstract class UIObject {

	public PVector position;
	
	public int width, height;
	public int col;
	
	
	public UIObject(float xPos, float yPos, int width, int height, int col) {
		this.position = new PVector(xPos, yPos);
		this.width = width;
		this.height = height;
		this.col = col;
	}
	
	public UIObject(float xPos, float yPos, int size, int col) {
		this(xPos, yPos, size, size, col);
	}
	
	
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
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(float x, float y) {
		return (x > position.x - width && x < position.x + width &&
				y > position.y - height && y < position.y + height);
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
