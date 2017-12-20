package objs.activities.impl;

import java.util.Optional;

import game.states.GameState;
import objs.activities.Activity;
import objs.workers.Worker;
import ui.locations.Location;

public class RestActivity extends Activity {

	public RestActivity(Location location) {
		super(location);
	}

	@Override
	public boolean canStart(Worker worker) {
		return worker.stressPercent > 0;
	}
	
	@Override
	public Activity start(Worker worker) {
		worker.setWorkTimer(REST_TIME);
		return this;		
	}	
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		worker.loseStress(40);
		
		location.workers.remove(worker);
		return Optional.empty();
	}

	@Override
	public String name() {
		return "Resting...";
	}
	
	


}
