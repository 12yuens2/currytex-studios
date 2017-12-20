package objs.workers;

public class StatLevel extends Level {

	public static final int PROGRESS_REQUIRED = 1;
	
	public int progress;
	
	public StatLevel() {
		super();
		progress = 0;
	}
	
	public void addProgress() {
		progress++;
		
		if (progress == PROGRESS_REQUIRED) {
			level++;
			progress = 0;
		}
	}
}
