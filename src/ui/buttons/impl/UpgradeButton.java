package ui.buttons.impl;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Studio;
import ui.buttons.Button;

/**
 * 
 *
 */
public class UpgradeButton extends Button {

	public static final int DEFAULT_COST = 200;
	
	public int cost, upgrades;
	public final int maxUpgrades, baseCost;
	public float costMultipler;
	
	/* Description of the upgrade as a lambda */
	public Supplier<String> descriptionLambda;
	
	/* Affect of buying the upgrade */
	public Function<GameState, Optional<GameState>> upgradeLambda;

	
	public UpgradeButton(float xPos, float yPos, int initialCost, int maxUpgrades, float costMultiplier,
			Supplier<String> descriptionLamda, Function<GameState, Optional<GameState>> upgradeLambda) {
		super(xPos, yPos, 75, 25);
		
		this.cost = initialCost;
		this.baseCost = initialCost;
		this.costMultipler = costMultiplier;
		this.upgrades = 0;
		this.maxUpgrades = maxUpgrades;
		this.upgradeLambda = upgradeLambda;
		this.descriptionLambda = descriptionLamda;
	}
	
	public UpgradeButton(float xPos, float yPos, int maxUpgrades, float costMultiplier,
			Supplier<String> descriptionLambda, Function<GameState, Optional<GameState>> upgradeLambda) {
		
		this(xPos, yPos, DEFAULT_COST, maxUpgrades, costMultiplier, descriptionLambda, upgradeLambda);
	}
	
	public UpgradeButton(float xPos, float yPos, int initialCost, int maxUpgrades, float costMultiplier,
			String description, Function<GameState, Optional<GameState>> upgradeLamda) {
		this(xPos, yPos, initialCost, maxUpgrades, costMultiplier, () -> description, upgradeLamda);
	}
	
	public UpgradeButton(float xPos, float yPos, int maxUpgrades,  float costMultiplier, 
			String description, Function<GameState, Optional<GameState>> upgradeLambda) {
		this(xPos, yPos, DEFAULT_COST, maxUpgrades, costMultiplier, description, upgradeLambda);
	}

	
	protected boolean buyUpgrade(Studio studio) {
		if (studio.currency >= cost && !isMaxed()) {
			upgrades++;
			studio.currency -= cost;
			cost = (int) (baseCost * Math.pow(costMultipler, upgrades)); 
			return true;
		}
		return false;
	}

	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		/* Draw text in button */
		if (isMaxed()) {
			drawEngine.drawText(16, "MAXED", position.x, position.y, DrawEngine.BLACK);
		}
		else {
			drawEngine.drawText(16, "Buy: " + cost, position.x, position.y, DrawEngine.BLACK);
		}
		
		/* Draw description */
		drawEngine.drawText(14, descriptionLambda.get(), position.x, position.y - height - 15, DrawEngine.BLACK);
	}
	
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		if (buyUpgrade(context.studio)) {
			return upgradeLambda.apply(currentState);
		}
		
		return Optional.empty();
	}
	
	private boolean isMaxed() {
		return upgrades >= maxUpgrades;
	}
	

}
