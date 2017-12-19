package ui.buttons.impl;

import java.util.Optional;
import java.util.function.Function;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Studio;
import ui.buttons.Button;

public class UpgradeButton extends Button {

	public static final int INITIAL_COST = 100;
//	public static final int COST_INCREASE = 100; //TODO balance formula
	
	public int cost;
	public String upgrade;
	public Function<GameState, Optional<GameState>> upgradeLambda;

	
	public UpgradeButton(String upgrade, float xPos, float yPos, int initialCost, Function<GameState, Optional<GameState>> upgradeLambda) {
		super(xPos, yPos, 75, 25);
		
		this.cost = initialCost;
		this.upgrade = upgrade;
		this.upgradeLambda = upgradeLambda;
	}
	
	public UpgradeButton(String upgrade, float xPos, float yPos, Function<GameState, Optional<GameState>> upgradeLambda) {
		this(upgrade, xPos, yPos, INITIAL_COST, upgradeLambda);
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
		drawEngine.drawText(16, upgrade + ": " + cost, position.x, position.y, DrawEngine.BLACK);
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
