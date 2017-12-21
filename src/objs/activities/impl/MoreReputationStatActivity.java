package objs.activities.impl;

import java.util.Optional;

import game.states.GameState;
import objs.activities.Activity;
import objs.workers.Worker;
import ui.locations.Location;

public class MoreReputationStatActivity extends Activity {

	public MoreReputationStatActivity(Location location) {
		super(location);
	}

	@Override
	public Activity start(Worker worker) {
		worker.setWorkTimer(MORE_REP_STAT_TIME);
		return this;
	}
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		worker.addMoreReputationLevel();;
		
		return super.finish(worker, currentState);
	}

	@Override
	public String getName() {
		return "Reputationing...";
	}

}
