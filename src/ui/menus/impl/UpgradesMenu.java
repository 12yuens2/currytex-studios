package ui.menus.impl;

import java.util.Optional;
import java.util.function.Function;

import app.DevStudios;
import game.DrawEngine;
import game.GameContext;
import game.GameModifiers;
import game.states.GameState;
import ui.WorkerInfo;
import ui.buttons.impl.UpgradeButton;
import ui.menus.Menu;

public class UpgradesMenu extends Menu {

	public static final float MODIFIER_INCREASE = 0.15f;
	
	public UpgradesMenu() {
		super(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 400);
		
		initUpgradeButtons();
	}

	private void initUpgradeButtons() {
		int xPos = (int) (position.x - width + 50);
		int yPos = (int) (position.y - height + 50);
		
		/* Manual click upgrade button */
		buttons.add(new UpgradeButton("Manual Click", xPos + 200, yPos, 500,
				(GameState state) -> {
					GameModifiers.manualClickPower += 0.5f;
					return Optional.empty();
				}
		));
		
		/* More exp */
		buttons.add(new UpgradeButton("More exp", xPos + 600, yPos, 
				(GameState state) -> {
					GameModifiers.expModifier += MODIFIER_INCREASE;
					return Optional.empty();
				}
		));
		
		/* More revenue */
		buttons.add(new UpgradeButton("More money", xPos + 200, yPos + 100, 
				(GameState state) -> {
					GameModifiers.revenueModifier += MODIFIER_INCREASE;
					return Optional.empty();
				}				
		));
		
		
		/* More reputation */
		buttons.add(new UpgradeButton("More reputation", xPos + 600, yPos + 100, 
				(GameState state) -> {
					GameModifiers.reputationModifier += MODIFIER_INCREASE;
					return Optional.empty();
				}
		));
		
		
		/* More coffee */
		buttons.add(new UpgradeButton("More coffee", xPos + 200, yPos + 200,
				(GameState state) -> {
					GameModifiers.coffeeAtCafe += 5;
					return Optional.empty();
				}
		));
		
		
		/* More worker slots */
		buttons.add(new UpgradeButton("More workers", xPos + 600, yPos + 200, 200,
				(GameState state) -> {
					for (WorkerInfo info : state.ui.workerInfos) {
						if (info.locked) {
							info.locked = false;
							return Optional.empty();
						}
					}
					
					return Optional.empty();
				}
		));
		
		
		
		/* More workers per building */
		buttons.add(new UpgradeButton("More in building", xPos + 200, yPos + 300, 250, 
				(GameState state) -> {
					GameModifiers.locationMaxWorkers += 1;
					return Optional.empty();
				}
		));
	}

	
}
