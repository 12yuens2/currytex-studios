package game.states.impl;

import app.CurryTeXStudios;
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
		startButton = new Button(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, 100, 30) {
			
			@Override
			public void display(DrawEngine drawEngine) {
				super.display(drawEngine);
				drawEngine.drawText(16, "Start Game", position.x, position.y, DrawEngine.BLACK);
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
			GameContext context = new GameContext();
			return new PlayingState(context, new GameUI(context));
		}
		return this;
	}

}
