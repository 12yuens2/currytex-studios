package objs;

import java.util.HashMap;

import game.GameTime;
import objs.activities.Activity;
import ui.menus.Menu;
import ui.menus.impl.WorkerMenu;

public class Worker {

	public String name;
	public HashMap<Skill, Level> skills;
	
	public int wage;
	
	public float workTimer;
	
	public Activity currentActivity;
	
	public Worker(String name) {
		this.name = name;
		this.wage = 0;
		this.workTimer = 0;
		this.skills = new HashMap<>();
	}
	
	public void startNewActivity(Activity activity) {
		currentActivity = activity;
		activity.start(this);
	}
	
	public void integrate() {
		if (currentActivity != null) {
			workTimer -= GameTime.MINUTES_PER_TIMESTEP;
			if (workTimer <= 0) {
				currentActivity.finish(this);
				currentActivity = null;
			}
		}
	}
	
	public void updateSkills(Skill skill, int expGain) {
		if (skills.containsKey(skill)) {
			skills.get(skill).gainExp(expGain);
		} 
		else {
			skills.put(skill, new Level());						
		}
	}

	public WorkerMenu getMenu() {
		return new WorkerMenu(this);
	}
}
