package objs.activities.impl;

import java.util.Optional;

import game.GameModifiers;
import game.states.GameState;
import objs.activities.Activity;
import objs.workers.Worker;
import ui.locations.Location;

public class GetCoffeeActivity extends Activity {

	public GetCoffeeActivity(Location location) {
		super(location);
	}

	@Override
	public Activity start(Worker worker) {
		worker.setWorkTimer(GET_COFFEE_TIME);
		return this;
	}
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		currentState.context.studio.coffee += GameModifiers.coffeeAtCafe;
		
		return super.finish(worker, currentState);
	}

	@Override
	public String getName() {
		return "Getting coffee...";
	}

}
