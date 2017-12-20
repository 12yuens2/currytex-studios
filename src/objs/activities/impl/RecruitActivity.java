package objs.activities.impl;

import java.util.Optional;

import game.states.GameState;
import game.states.impl.InMenuState;
import objs.activities.Activity;
import objs.workers.Worker;
import ui.locations.Location;
import ui.menus.impl.RecruitMenu;

public class RecruitActivity extends Activity {

	public RecruitActivity(Location location) {
		super(location);
	}

	@Override
	public Activity start(Worker worker) {
		worker.setWorkTimer(RECRUIT_TIME);
		return this;
	}

	@Override
	public Optional<GameState> finish(Worker worker, GameState currentState) {
		super.finish(worker, currentState);
		
		RecruitMenu recruitMenu = new RecruitMenu(currentState);
		return Optional.of(new InMenuState(recruitMenu, currentState));
	}

	@Override
	public String name() {
		return "Recruiting...";
	}

}
