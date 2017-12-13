package objs.activities.impl;

import objs.Worker;
import objs.activities.Activity;
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
		worker.workTimer += 100;
		return this;
	}
	
	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		super.finish(worker, currentState);
		
		projectLocation.occupied = false;
		
		ChooseNewProjectMenu projectMenu = new ChooseNewProjectMenu(projectLocation, currentState);
		return Optional.of(new InMenuState(projectMenu, currentState));
	}

}
