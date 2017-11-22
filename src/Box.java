import processing.core.PVector;

public class Box {

	public final PVector absolutePosition;
	
	public PVector position;
	public int size;
	public float mouseXOffset, mouseYOffset;
	public boolean mouseOver, mouseLocked;
	
	public Box(float xPos, float yPos, int size){
		this.absolutePosition = new PVector(xPos, yPos);
		this.position = absolutePosition.copy();
		this.size = size;
		this.mouseXOffset = 0f;
		this.mouseYOffset = 0f;
		this.mouseOver = false;
		this.mouseLocked = false;
	}
}
