package game.states.impl;

import java.util.Optional;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.GameUI;
import game.states.GameState;
import ui.buttons.Button;

public class StartState extends GameState {

	public Button startButton;
	
	public StartState(GameContext context, GameUI ui) {
		super(context, ui);
		startButton = new Button(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 100, 30, DrawEngine.parent.color(255)) {
			
			@Override
			public void display(DrawEngine drawEngine) {
				super.display(drawEngine);
				drawEngine.drawText(16, "Start Game", position.x, position.y, DrawEngine.BLACK);
			}
			
			@Override
			public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
					GameState currentState) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
					GameState currentState) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		DrawEngine.parent.background(DrawEngine.BLACK);
		startButton.display(drawEngine);
	}

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
		if (startButton.contains(input.mouseX, input.mouseY)) {
			return new PlayingState(context, ui);
		}
		return this;
	}

}
