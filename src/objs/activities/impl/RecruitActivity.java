package objs.activities.impl;

import java.util.Optional;

import game.states.GameState;
import game.states.impl.InMenuState;
import objs.Worker;
import objs.activities.Activity;
import ui.locations.Location;
import ui.menus.impl.RecruitMenu;

public class RecruitActivity extends Activity {

	public RecruitActivity(Location location) {
		super(location);
	}

	@Override
	public Activity start(Worker worker) {
		worker.workTimer = 150;
		return this;
	}

	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		super.finish(worker, currentState);
		
		RecruitMenu recruitMenu = new RecruitMenu(currentState);
		return Optional.of(new InMenuState(recruitMenu, currentState));
	}

}
