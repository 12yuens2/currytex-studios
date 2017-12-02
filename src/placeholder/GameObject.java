package placeholder;
import game.DrawEngine;
import processing.core.PVector;

/**
 * Assume all gameobjects are rectangles
 * @author sy35
 *
 */
public abstract class GameObject {

	public PVector position;
	
	public int size;
	
	public GameObject(float xPos, float yPos, int size) {
		this.position = new PVector(xPos, yPos);
		this.size = size;
	}
	
	public abstract void display(DrawEngine drawEngine);
	
	
	
	/**
	 * Check if this object overlaps with another.
	 * @param other - Other object to check overlap.
	 * @return - If the two objects overlap.
	 */
	public boolean overlapsWith(GameObject other) {
		return (this.left() < other.right() && this.right() > other.left()) &&
			   (this.up() < other.down() && this.down() > other.up());
	}
	
	public float left() {
		return position.x - size;
	}
	
	public float right() {
		return position.x + size;
	}
	
	public float up() {
		return position.y - size;
	}
	
	public float down() {
		return position.y + size;
	}
}
