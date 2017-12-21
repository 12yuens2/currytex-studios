package ui.menus.impl;

import java.util.Optional;

import app.CurryTeXStudios;
import game.DrawEngine;
import game.GameModifiers;
import game.states.GameState;
import ui.WorkerInfo;
import ui.buttons.impl.UpgradeButton;
import ui.menus.Menu;


/**
 * Menu for buying upgrades. 
 *
 */
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
		
		buttons.add(new UpgradeButton(xPos + 200, yPos, 500, 5, 1.95f, "More power to manual clicks!",
				(GameState state) -> {
					GameModifiers.manualClickPower += 0.25f;
					return Optional.empty();
				}
		));
		
		/* More exp */
		buttons.add(new UpgradeButton(xPos + 600, yPos, 10, 1.51f,
				() -> "Workers earn " + (int) ((GameModifiers.expModifier - 1) * 100) + "% more exp!",
				(GameState state) -> {
					GameModifiers.expModifier += 0.2f;
					return Optional.empty();
				}
		));
		
		/* More revenue */
		buttons.add(new UpgradeButton(xPos + 200, yPos + 150, 10, 1.51f,
				() -> "Projects earn " + (int) ((GameModifiers.revenueModifier - 1) * 100) + "% more money!",
				(GameState state) -> {
					GameModifiers.revenueModifier += 0.1f;
					return Optional.empty();
				}				
		));
		
		
		/* More reputation */
		buttons.add(new UpgradeButton(xPos + 600, yPos + 150, 10, 1.51f,
				() -> "Projects earn " + (int) ((GameModifiers.reputationModifier - 1) * 100) + "% more reputation!",
				(GameState state) -> {
					GameModifiers.reputationModifier += 0.25f;
					return Optional.empty();
				}
		));
		
		
		/* More coffee */
		buttons.add(new UpgradeButton(xPos + 200, yPos + 300, 8, 1.6f,
				() -> "Cafe currently gives " + GameModifiers.coffeeAtCafe + " coffee!",
				(GameState state) -> {
					GameModifiers.coffeeAtCafe += 5;
					return Optional.empty();
				}
		));
		
		
		/* More worker slots */
		buttons.add(new UpgradeButton(xPos + 600, yPos + 300, 250, 6, 1.75f, "Unlock more worker slots!",
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
		buttons.add(new UpgradeButton(xPos + 200, yPos + 450, 300, 6, 1.75f,
				() -> GameModifiers.locationMaxWorkers + " workers are allowed in the same location!",
				(GameState state) -> {
					GameModifiers.locationMaxWorkers += 1;
					return Optional.empty();
				}
		));
	}

	
}
