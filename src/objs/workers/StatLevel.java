package objs.workers;


/**
 * StatLevel is also a Level, but uses a static progress rather than experience to level up.
 *
 */
public class StatLevel extends Level {

	public static final int PROGRESS_REQUIRED = 2;
	
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
