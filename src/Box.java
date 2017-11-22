import processing.core.PVector;

public class Box extends GameObject {

	public final PVector originalPosition;
	public float mouseXOffset, mouseYOffset;
	public boolean mouseOver, mouseLocked;
	
	public Box(float xPos, float yPos, int size){
		super(xPos, yPos, size);
		this.originalPosition = position.copy();
		
		this.mouseXOffset = 0f;
		this.mouseYOffset = 0f;
		this.mouseOver = false;
		this.mouseLocked = false;
	}
	
	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(drawEngine.parent.color(0,255,0), position, size);
	}
}
