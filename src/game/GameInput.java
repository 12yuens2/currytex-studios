package game;

public class GameInput {

	public enum MouseAction {NONE, MOUSE_PRESS, MOUSE_DRAG, MOUSE_RELEASE};
	
	public int mouseButton, keyCode;
	public float mouseX, mouseY;
	public MouseAction mouseAction;
	
	public GameInput(float mouseX, float mouseY, int mouseButton, int keyCode, MouseAction mouseAction) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseButton = mouseButton;
		this.keyCode = keyCode;
		this.mouseAction = mouseAction;
	}
}
