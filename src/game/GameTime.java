package game;

public class GameTime {
	
	public static final double DELTA_TIME = 0.01;
	public static final double MINUTES_PER_TIMESTEP = 0.1;
	public static final int MINUTES_IN_HOUR = 60;
	public static final int HOURS_IN_DAY = 24;
	
	public double minutes;
	public int hours;
	public int days;
	
	public GameTime() {
		this.minutes = 0;
		this.hours = 0;
		this.days = 0;
	}
	
	public void incrementTimestep() {
		minutes += MINUTES_PER_TIMESTEP;
		
		if (minutes > MINUTES_IN_HOUR) {
			hours += Math.floor(minutes / MINUTES_IN_HOUR);
			minutes -= Math.floor(minutes / MINUTES_IN_HOUR) * MINUTES_IN_HOUR;
		}
		
		if (hours > HOURS_IN_DAY) {
			days += (hours / HOURS_IN_DAY);
			hours -= (hours / HOURS_IN_DAY) * HOURS_IN_DAY;
		}
	}
	
	public String toString() {
		return minutes + " minutes, " + hours + " hours, " + days + " days";
	}
}
