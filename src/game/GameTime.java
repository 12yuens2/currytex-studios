package game;

/**
 * Class that represents in-game time. 
 * The game time is updated according to real time and not frame rate.
 *
 */
public class GameTime {
	
	public static final float DELTA_TIME = 0.03f;
	public static final float HOURS_PER_TIMESTEP = 0.07f;
	public static final int HOURS_IN_DAY = 24;
	public static final int DAYS_IN_MONTH = 30;
	public static final int MONTHS_IN_YEAR = 12;
	
	public double hours;
	public int days;
	public int months;
	public int years;
	
	public GameTime() {
		this.hours = 0;
		this.days = 0;
		this.months = 0;
	}
	
	/**
	 * Increment this time by one time step.
	 * Update the days/months/years accordingly.
	 * @param context
	 */
	public void incrementTimestep(GameContext context) {
		hours += HOURS_PER_TIMESTEP;
		
		if (hours > HOURS_IN_DAY) {
			context.newDay();
			days += Math.floor(hours / HOURS_IN_DAY);
			hours -= Math.floor(hours / HOURS_IN_DAY) * HOURS_IN_DAY;
		}
		
		if (days > DAYS_IN_MONTH - 1) {
			context.newMonth();
			months += (days / DAYS_IN_MONTH);
			days -= (days / DAYS_IN_MONTH) * DAYS_IN_MONTH;
		}
		
		if (months > MONTHS_IN_YEAR - 1) {
			context.newYear();
			years += (months / MONTHS_IN_YEAR);
			months -= (months / MONTHS_IN_YEAR) * MONTHS_IN_YEAR;
		}
	}

	/**
	 * @return Total time in terms of days.
	 */
	public int totalTime() {
		return days + (months * 30) + (years * 360);
	}
	
	@Override
	public String toString() {
		return days + " days, " + months + " months, " + years + " years";
	}

	public void display(DrawEngine drawEngine) {
		drawEngine.drawText(16, toString(), 125, 25, DrawEngine.BLACK);
	}
}
