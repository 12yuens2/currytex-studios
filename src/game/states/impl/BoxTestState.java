package game.states.impl;

import java.util.ArrayList;

import game.DrawEngine;
import game.GameInput;
import game.states.GameState;
import placeholder.Box;
import placeholder.Location;
import processing.core.PVector;

public class BoxTestState extends GameState {

	public ArrayList<Box> boxes;
	public ArrayList<Location> locations;
	
	float mouseX, mouseY;
	
	public BoxTestState() {
		boxes = new ArrayList<Box>();
		locations = new ArrayList<Location>();
		
		boxes.add(new Box(100, 100, 50));
		boxes.add(new Box(100, 300, 50));
		boxes.add(new Box(100, 500, 50));
		
		locations.add(new Location(700, 150, 100, 200));
		locations.add(new Location(700, 450, 100, 300));
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		for (Location l : locations) l.display(drawEngine);
		for (Box b : boxes) b.display(drawEngine);
		
	}

	@Override
	public GameState update(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		for (Box b : boxes) {
			if (mouseX > b.position.x - b.size && mouseY < b.position.x + b.size &&
				mouseY > b.position.y - b.size && mouseY < b.position.y + b.size) {
				
				b.mouseOver = true;
			} 
			else {
				b.mouseOver = false;
			}
			
			b.integrate();
		}

		return this;
	}

	@Override
	public GameState handleInput(GameInput input) {
		switch (input.mouseAction) {
			case NONE:
				
				break;
			case MOUSE_PRESS:
				handleMousePress();
				break;
				
			case MOUSE_DRAG:
				handleMouseDrag();
				break;
				
			case MOUSE_RELEASE:
				handleMouseRelease();
				break;			
		}
		return this;
	}

	
	private void handleMousePress() {
		for (Box b : boxes) {
			if (b.mouseOver && !b.disabled) {
				b.mouseLocked = true;
			}
			else {
				b.mouseLocked =  false;
			}
			
			b.mouseXOffset = mouseX - b.position.x;
			b.mouseYOffset = mouseY - b.position.y;
		}
	}
	
	private void handleMouseDrag() {
		for (Box b : boxes) {
			if (b.mouseLocked) {
				b.position = new PVector(mouseX - b.mouseXOffset, mouseY - b.mouseYOffset);
			}
		}
	}
	
	private void handleMouseRelease() {
		for (Box b : boxes) {
			if (b.mouseLocked) {
				b.mouseLocked = false;
				for (Location l : locations) {
					if (b.overlapsWith(l)) {
						b.enterLocation(l.timeNeeded);
					}
				}
				b.position = b.originalPosition.copy();
			}
		}
	}
}
