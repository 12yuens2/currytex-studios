package game;

public class GameTime {
	
	public static final double DELTA_TIME = 0.02;
	public static final double HOURS_PER_TIMESTEP = 1.1;
	public static final int HOURS_IN_DA = 24;
	public static final int DAYS_IN_MONTH = 30;
	
	public double hours;
	public int days;
	public int months;
	
	public GameTime() {
		this.hours = 0;
		this.days = 0;
		this.months = 0;
	}
	
	public void incrementTimestep(GameContext context) {
		hours += HOURS_PER_TIMESTEP;
		
		/* New hour */
		if (hours > HOURS_IN_DA) {
			context.newHour();
			days += Math.floor(hours / HOURS_IN_DA);
			hours -= Math.floor(hours / HOURS_IN_DA) * HOURS_IN_DA;
		}
		
		if (days > DAYS_IN_MONTH - 1) {
			context.newDay();
			months += (days / DAYS_IN_MONTH);
			days -= (days / DAYS_IN_MONTH) * DAYS_IN_MONTH;
		}
	}
	
	public String toString() {
		return /*minutes + " minutes, " + */days + " days, " + months + " months";
	}

	public void display(DrawEngine drawEngine) {
		drawEngine.drawText(16, toString(), 100, 25, DrawEngine.BLACK);
	}
}
