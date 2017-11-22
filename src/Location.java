

public class Location extends GameObject {

	public Location(float xPos, float yPos, int size) {
		super(xPos, yPos, size);
	}

	public void display(DrawEngine drawEngine) {
		drawEngine.drawRectangle(drawEngine.parent.color(255,0,0), position, size);
	}

}
