package game;

public class GameTime {
	
	public static final double DELTA_TIME = 0.01;
	public static final double MINUTES_PER_TIMESTEP = 0.2;
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
	
	public void incrementTimestep(GameContext context) {
		minutes += MINUTES_PER_TIMESTEP;
		
		/* New hour */
		if (minutes > MINUTES_IN_HOUR) {
			context.newHour();
			hours += Math.floor(minutes / MINUTES_IN_HOUR);
			minutes -= Math.floor(minutes / MINUTES_IN_HOUR) * MINUTES_IN_HOUR;
		}
		
		if (hours > HOURS_IN_DAY - 1) {
			context.newDay();
			days += (hours / HOURS_IN_DAY);
			hours -= (hours / HOURS_IN_DAY) * HOURS_IN_DAY;
		}
	}
	
	public String toString() {
		return /*minutes + " minutes, " + */hours + " hours, " + days + " days";
	}

	public void display(DrawEngine drawEngine) {
		drawEngine.drawText(16, toString(), 100, 25, DrawEngine.parent.color(0));
	}
}
