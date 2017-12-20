package objs.activities.impl;

import objs.activities.Activity;
import objs.workers.Worker;
import ui.locations.Location;

import java.util.Optional;

import game.states.GameState;

public class MoreMoneyStatActivity extends Activity {

	public MoreMoneyStatActivity(Location location) {
		super(location);
	}

	@Override
	public Activity start(Worker worker) {
		worker.setWorkTimer(MORE_MONEY_STAT_TIME);
		
		return this;
	}
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		worker.addMoreMoneyLevel();
		
		return super.finish(worker, currentState);
	}

	@Override
	public String name() {
		return "Entrepreneuring...";
	}

}
