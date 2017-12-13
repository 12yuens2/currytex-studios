package objs.activities.impl;

import java.util.Optional;

import game.states.GameState;
import objs.Worker;
import objs.activities.Activity;
import ui.locations.Location;

public class MoreReputationStatActivity extends Activity {

	public MoreReputationStatActivity(Location location) {
		super(location);
	}

	@Override
	public Activity start(Worker worker) {
		worker.workTimer += 150;
		
		return this;
	}
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		worker.addMoreReputationLevel();;
		
		return super.finish(worker, currentState);
	}

}
