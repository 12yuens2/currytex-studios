import processing.core.PVector;

public class Box extends GameObject {

	public final PVector originalPosition;
	public float mouseXOffset, mouseYOffset;
	public boolean disabled, mouseOver, mouseLocked;
	
	public int timer;
	
	public Box(float xPos, float yPos, int size){
		super(xPos, yPos, size);
		this.originalPosition = position.copy();
		
		this.timer = 0;
		
		this.mouseXOffset = 0f;
		this.mouseYOffset = 0f;
		this.mouseOver = false;
		this.mouseLocked = false;
	}
	
	public void integrate() {
		if (timer > 0) {
			timer--;
			if (timer <= 0) disabled = false;
		}
		
		
	}
	
	public void enterLocation(int timeNeeded) {
		disabled = true;
		timer = timeNeeded;
	}
	
	public void display(DrawEngine drawEngine) {
		int col = disabled ? drawEngine.parent.color(0, 155, 0) : drawEngine.parent.color(0, 255, 0);
		drawEngine.drawRectangle(col, position, size);
	}
}
