package game.states.impl;

import java.util.ArrayList;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.states.GameState;
import javafx.scene.shape.Arc;
import objs.Project;
import objs.Project.Difficulty;
import placeholder.Box;
import placeholder.Location;
import processing.core.PApplet;
import processing.core.PVector;
import ui.Button;
import ui.Menu;
import ui.MenuButton;

public class StartState extends GameState {

	
	public StartState(PApplet parent, GameContext context) {
		super(parent, context);

	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
	}

	@Override
	public GameState update(float mouseX, float mouseY) {
		super.update(mouseX, mouseY);
		context.updateGameTime();
		context.updateBoxes(mouseX, mouseY);
		
		
//		System.out.println(context.gameTime);
		return this;
	}

//	@Override
//	public GameState handleInput(GameInput input) {
//		switch (input.mouseAction) {			
//			case MOUSE_RELEASE:
//				for (MenuButton b : context.openMenuButtons) {
//					if (b.contains(input.mouseX, input.mouseY)) {
//						return new InMenuState(parent, context, b.menu, this);
//					}
//				}
//
//				break;			
//		}
//		return this;
//	}

	@Override
	public GameState handleMouseDrag(GameInput input) {
		for (Box b : context.boxes) {
			if (b.mouseLocked) {
				b.position = new PVector(mouseX - b.mouseXOffset, mouseY - b.mouseYOffset);
			}
		}
		return this;
	}

	@Override
	public GameState handleMousePress(GameInput input) {
		for (Box b : context.boxes) {
			if (b.mouseOver && !b.disabled) {
				b.mouseLocked = true;
			}
			else {
				b.mouseLocked =  false;
			}
			
			b.mouseXOffset = mouseX - b.position.x;
			b.mouseYOffset = mouseY - b.position.y;
		}
		
		for (Location l : context.locations) {
			if (l.project == null) {
				l.project = Project.randomProject();
			}
		}
		return this;
	}

	@Override
	public GameState handleMouseRelease(GameInput input) {
		for (MenuButton b : context.openMenuButtons) {
			if (b.contains(input.mouseX, input.mouseY)) {
				return new InMenuState(parent, context, b.menu, this);
			}
		}
		
		for (Box b : context.boxes) {
			b.enterIfPossible(context.locations);
		}
		return this;
	}
	
	

}
