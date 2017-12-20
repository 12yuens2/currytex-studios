package objs.activities.impl;

import objs.activities.Activity;
import objs.workers.Worker;
import ui.Tooltip;
import ui.locations.Location;
import ui.locations.impl.ProjectLocation;
import ui.menus.impl.ChooseNewProjectMenu;
import ui.menus.impl.RecruitMenu;

import java.util.Optional;

import game.states.GameState;
import game.states.impl.InMenuState;


public class GettingProjectActivity extends Activity {

	public ProjectLocation projectLocation;
	
	public GettingProjectActivity(Location location, ProjectLocation projectLocation) {
		super(location);
		
		this.projectLocation = projectLocation;
	}

	@Override
	public Activity start(Worker worker) {
		worker.setWorkTimer(GET_NEW_PROJECT_TIME);
		return this;
	}
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		super.finish(worker, currentState);
		
		projectLocation.occupied = false;
		 
		ChooseNewProjectMenu projectMenu = new ChooseNewProjectMenu(projectLocation, currentState);
		GameState menuState = new InMenuState(projectMenu, currentState);
		
		return Optional.of(menuState);
	}

	@Override
	public String name() {
		return "Getting new project...";
	}

}
