package game.states.impl;

import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.states.GameState;
import processing.core.PApplet;
import ui.Button;
import ui.Menu;

public class InMenuState extends GameState {

	public Menu menu;
	public Button exitMenuButton;
	public GameState previousState;
	
	public InMenuState(PApplet parent, GameContext context, Menu menu, GameState previousState) {
		super(parent, context);
		this.menu = menu;
		this.previousState = previousState;
		this.exitMenuButton = new Button(menu.position.x + menu.width, menu.position.y - menu.height, 
										 20, 20, parent.color(250, 0, 0));
		
		
		int col = parent.color(20, 200, 20);
		menu.buttons.add(new Button(400, 200, 50, 20, col));
		menu.buttons.add(new Button(400, 250, 50, 20, col));
		menu.buttons.add(new Button(400, 300, 50, 20, col));
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		menu.display(drawEngine);
		exitMenuButton.display(drawEngine);
	}

	@Override
	public GameState update(float mouseX, float mouseY) {
		super.update(mouseX, mouseY);
		menu.update(mouseX, mouseY);
		
		
		System.out.println(context.gameTime);
		return this;
	}

//	@Override
//	public GameState handleInput(GameInput input) {
//		switch (input.mouseAction) {
//			case NONE:
//				
//				break;
//				
//			case MOUSE_RELEASE:
//				if (exitMenuButton.contains(input.mouseX, input.mouseY)) {
//					return previousState;
//				}
//				menu.clicked(input);
//				break;			
//		}
//		return this;
//	}

	@Override
	public GameState handleMouseDrag(GameInput input) {
		return this;
	}

	@Override
	public GameState handleMousePress(GameInput input) {
		return this;
	}

	@Override
	public GameState handleMouseRelease(GameInput input) {
		menu.clicked(input);
		if (exitMenuButton.contains(input.mouseX, input.mouseY)) {
			return previousState;
		}
		return this;
	}

}
