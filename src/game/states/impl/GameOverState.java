package game.states.impl;

import java.util.Optional;

import app.CurryTeXStudios;
import game.DrawEngine;
import game.GameContext;
import game.GameInput;
import game.GameUI;
import game.states.GameState;
import ui.buttons.Button;

public class GameOverState extends GameState {

	public Button restart;
	
	public GameOverState(GameContext context, GameUI ui) {
		super(context, ui);

		restart = new Button(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2 + 100, 100, 30) {

			@Override
			public void display(DrawEngine drawEngine) {
				super.display(drawEngine);
				drawEngine.drawText(30, "Restart", position.x, position.y, DrawEngine.BLACK);
			}
			
			@Override
			public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
					GameState currentState) {
				
				return Optional.empty();
			}

			@Override
			public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
					GameState currentState) {
				
				return Optional.empty();
			}
			
		};
		
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		DrawEngine.parent.background(0);
		
		drawEngine.drawText(16, "Game Over!", CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, DrawEngine.parent.color(255));
		restart.display(drawEngine);
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
		if (restart.contains(input.mouseX, input.mouseY)) {
			GameContext newContext = new GameContext();
			return new StartState(newContext, new GameUI(newContext));
		}
		return this;
	}

}
