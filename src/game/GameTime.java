package game;

public class GameTime {
	
	public static final int MINUTES_PER_TIMESTEP = 5;
	public static final int MINUTES_IN_HOUR = 60;
	public static final int HOURS_IN_DAY = 24;
	
	public int minutes;
	public int hours;
	public int days;
	
	
	public void incrementTimestep() {
		minutes += 1;
		if (minutes > 0 && minutes % MINUTES_IN_HOUR == 0) {
			hours += (minutes / MINUTES_IN_HOUR);
			minutes -= (minutes / MINUTES_IN_HOUR) * MINUTES_IN_HOUR;
		}
		
		if (hours > 0 && hours % HOURS_IN_DAY == 0) {
			days += (hours / HOURS_IN_DAY);
			hours -= (hours / HOURS_IN_DAY) * HOURS_IN_DAY;
		}
	}
	
	public String toString() {
		return minutes + " minutes, " + hours + " hours, " + days + " days";
	}
}
