package objs.activities;

import java.util.ArrayList;
import java.util.Optional;

import ui.locations.Location;
import game.states.GameState;
import objs.Worker;

public abstract class Activity {

	
	public static final int GET_NEW_PROJECT_TIME = 40;
	public static final int GET_COFFEE_TIME = 30;
	public static final int MORE_MONEY_STAT_TIME = 72;
	public static final int MORE_REP_STAT_TIME = 72;
	public static final int RECRUIT_TIME = 84;
	public static final int REST_TIME = 50;
	
	public int timeNeeded;
	
	public Location location;
	
	public Activity(Location location) {
		this.location = location;
	}
	
	/**
	 * Start this for activity for the worker and update the worker on time needed, likely based on worker's stats.
	 * For example a skilled worker starting a Project activity would take less time.
	 * @param worker - The worker starting this activity.
	 */
	public abstract Activity start(Worker worker);
	
	/**
	 * Finish this activity and update worker with appropriate stats.
	 * @param worker - The worker who finished this activity.
	 * @param currentState - The current state of the game.
	 */
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		worker.addStress(5);
		
		location.workers.remove(worker);
		return Optional.empty();
	}
	
	
	/**
	 * Check if the worker can start this activity
	 * @param worker
	 * @return
	 */
	public boolean canStart(Worker worker) {
		return worker.stressPercent < 100;
	}

	
	
}
