package objs.activities;

import objs.Worker;

public abstract class Activity {

	public int timeNeeded;
	
	
	/**
	 * Start this activity and update the worker on time needed, likely based on worker's stats.
	 * For example a skilled worker starting a Project activity would take less time.
	 * @param worker - The worker starting this activity.
	 */
	public abstract void start(Worker worker);
	
	/**
	 * Finish this activity and update worker with appropriate stats.
	 * @param worker - The worker who finished this activity.
	 */
	public abstract void finish(Worker worker);
	
}
