package ui.menus.impl;

import java.util.Optional;
import java.util.function.Function;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import ui.buttons.UpgradeButton;
import ui.menus.Menu;

public class UpgradesMenu extends Menu {

	public UpgradesMenu() {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 400);
		
		initUpgradeButtons();
	}

	private void initUpgradeButtons() {
		/* Manual click upgrade button */
		buttons.add(new UpgradeButton(position.x, position.y, DrawEngine.parent.color(200, 50, 50), 
				new Function<GameContext, Optional<GameState>>() {

					@Override
					public Optional<GameState> apply(GameContext context) {
						context.constants.manualClickPower += 1;
						return Optional.empty();
					}
		}));
		
	}

	
}
