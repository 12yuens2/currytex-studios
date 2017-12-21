package ui.menus.impl;

import java.util.Optional;
import java.util.function.Function;

import app.CurryTeXStudios;
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
		super(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, 450, 400);
		
		initUpgradeButtons();
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(30, "Upgrades", position.x, position.y - height + 60, DrawEngine.BLACK);
	}

	private void initUpgradeButtons() {
		int xPos = (int) (position.x - width + 50);
		int yPos = (int) (position.y - height + 175);
		
		/* Manual click upgrade button */
		
		buttons.add(new UpgradeButton(xPos + 200, yPos, 500, "More power to manual clicks!",
				(GameState state) -> {
					GameModifiers.manualClickPower += 0.25f;
					return Optional.empty();
				}
		));
		
		/* More exp */
		buttons.add(new UpgradeButton(xPos + 600, yPos, 
				() -> "Workers earn " + (int) ((GameModifiers.expModifier - 1 + 0.2f) * 100) + "% more exp!",
				(GameState state) -> {
					GameModifiers.expModifier += 0.2f;
					return Optional.empty();
				}
		));
		
		/* More revenue */
		buttons.add(new UpgradeButton(xPos + 200, yPos + 150, 
				() -> "Projects earn " + (int) ((GameModifiers.revenueModifier - 1 + 0.1f) * 100) + "% more money!",
				(GameState state) -> {
					GameModifiers.revenueModifier += 0.1f;
					return Optional.empty();
				}				
		));
		
		
		/* More reputation */
		buttons.add(new UpgradeButton(xPos + 600, yPos + 150, 
				() -> "Projects earn " + (int) ((GameModifiers.reputationModifier - 1 + 0.25f) * 100) + "% more reputation!",
				(GameState state) -> {
					GameModifiers.reputationModifier += 0.25f;
					return Optional.empty();
				}
		));
		
		
		/* More coffee */
		buttons.add(new UpgradeButton(xPos + 200, yPos + 300,
				() -> "Cafe gives " + GameModifiers.coffeeAtCafe + " coffee!",
				(GameState state) -> {
					GameModifiers.coffeeAtCafe += 5;
					return Optional.empty();
				}
		));
		
		
		/* More worker slots */
		buttons.add(new UpgradeButton(xPos + 600, yPos + 300, 250, "Unlock more worker slots!",
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
		buttons.add(new UpgradeButton(xPos + 200, yPos + 450, 300,
				() -> "Allow " + (GameModifiers.locationMaxWorkers + 1) + " workers in the same location!",
				(GameState state) -> {
					GameModifiers.locationMaxWorkers += 1;
					return Optional.empty();
				}
		));
	}

	
}
