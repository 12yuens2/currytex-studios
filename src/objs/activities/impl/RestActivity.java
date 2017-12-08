package objs.activities.impl;

import objs.Worker;
import objs.activities.Activity;

public class RestActivity extends Activity {

	public static int TIME_NEEDED = 150;

	@Override
	public Activity start(Worker worker) {
		worker.workTimer = TIME_NEEDED;
		return this;		
	}	
	
	@Override
	public void finish(Worker worker) {
		
		worker.stressPercent = Math.max(0, worker.stressPercent - 40);
		//worker.destress();
		
	}


}
