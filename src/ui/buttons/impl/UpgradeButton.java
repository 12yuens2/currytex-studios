package ui.buttons.impl;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Studio;
import ui.buttons.Button;

public class UpgradeButton extends Button {

	public static final int DEFAULT_COST = 100;
	
	public int cost;
	public Supplier<String> descriptionLambda;
	public Function<GameState, Optional<GameState>> upgradeLambda;

	
	public UpgradeButton(float xPos, float yPos, int initialCost, 
			Supplier<String> descriptionLamda, Function<GameState, Optional<GameState>> upgradeLambda) {
		super(xPos, yPos, 75, 25);
		
		this.cost = initialCost;
		this.upgradeLambda = upgradeLambda;
		this.descriptionLambda = descriptionLamda;
	}
	
	public UpgradeButton(float xPos, float yPos, 
			Supplier<String> descriptionLambda, Function<GameState, Optional<GameState>> upgradeLambda) {
		
		this(xPos, yPos, DEFAULT_COST, descriptionLambda, upgradeLambda);
	}
	
	public UpgradeButton(float xPos, float yPos, int initialCost, 
			String description, Function<GameState, Optional<GameState>> upgradeLamda) {
		this(xPos, yPos, initialCost, () -> description, upgradeLamda);
	}
	
	public UpgradeButton(float xPos, float yPos, String description, Function<GameState, Optional<GameState>> upgradeLambda) {
		this(xPos, yPos, DEFAULT_COST, description, upgradeLambda);
	}

	
	protected boolean buyUpgrade(Studio studio) {
		if (studio.currency >= cost) {
			studio.currency -= cost;
			cost += cost; //TODO balance formula
			return true;
		}
		return false;
	}

	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(16, "Buy: " + cost, position.x, position.y, DrawEngine.BLACK);
		drawEngine.drawText(14, descriptionLambda.get(), position.x, position.y - height - 15, DrawEngine.BLACK);
	}
	
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		if (buyUpgrade(context.studio)) {
			return upgradeLambda.apply(currentState);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Optional.empty();
	}
	
	

}
