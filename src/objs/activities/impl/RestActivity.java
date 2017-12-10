package objs.activities.impl;

import java.util.Optional;

import game.states.GameState;
import objs.Worker;
import objs.activities.Activity;

public class RestActivity extends Activity {

	public static int TIME_NEEDED = 150;

	@Override
	public boolean canStart(Worker worker) {
		return worker.stressPercent > 0;
	}
	
	@Override
	public Activity start(Worker worker) {
		worker.workTimer = TIME_NEEDED;
		return this;		
	}	
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		worker.stressPercent = Math.max(0, worker.stressPercent - 40);
		return Optional.empty();
	}


}
