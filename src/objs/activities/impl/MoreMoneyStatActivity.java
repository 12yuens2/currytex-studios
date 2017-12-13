package objs.activities.impl;

import objs.Worker;
import objs.activities.Activity;
import ui.locations.Location;

import java.util.Optional;

import game.states.GameState;

public class MoreMoneyStatActivity extends Activity {

	public MoreMoneyStatActivity(Location location) {
		super(location);
	}

	@Override
	public Activity start(Worker worker) {
		worker.workTimer += 150;
		
		return this;
	}
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		worker.addMoreMoneyLevel();
		
		return super.finish(worker, currentState);
	}

}
