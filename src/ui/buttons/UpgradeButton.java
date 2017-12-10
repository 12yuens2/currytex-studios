package ui.buttons;

import java.util.Optional;
import java.util.function.Function;

import game.DrawEngine;
import game.GameContext;
import game.states.GameState;
import objs.Studio;

public class UpgradeButton extends Button {

	public static final int INITIAL_COST = 100;
	public static final int COST_INCREASE = 100; //TODO balance formula
	
	public int cost;
	public Function<GameContext, Optional<GameState>> upgradeLambda;
	
	public UpgradeButton(float xPos, float yPos, int col, Function<GameContext, Optional<GameState>> upgradeLambda) {
		super(xPos, yPos, 75, 25, col);
		
		this.cost = INITIAL_COST;
		this.upgradeLambda = upgradeLambda;
	}
	
	protected boolean buyUpgrade(Studio studio) {
		if (studio.currency >= cost) {
			studio.currency -= cost;
			cost += COST_INCREASE;
			return true;
		}
		return false;
	}

	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawEngine.drawText(16, "upgrade: "+cost, position.x, position.y, DrawEngine.parent.color(0));
	}
	
	
	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		if (buyUpgrade(context.studio)) {
			return upgradeLambda.apply(context);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context, GameState currentState) {
		return Optional.empty();
	}
	
	

}
