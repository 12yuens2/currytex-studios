package game;

public class GameTime {
	
	public static final double DELTA_TIME = 0.02;
	public static final double HOURS_PER_TIMESTEP = 1.1;
	public static final int HOURS_IN_DA = 24;
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
		
		if (months > MONTHS_IN_YEAR - 1) {
			context.newYear();
			years += (months / MONTHS_IN_YEAR);
			months -= (months / MONTHS_IN_YEAR) * MONTHS_IN_YEAR;
		}
	}

	
	public int totalTime() {
		return days + (months * 30) + (years * 360);
	}
	
	public String toString() {
		return days + " days, " + months + " months, " + years + " years";
	}

	public void display(DrawEngine drawEngine) {
		drawEngine.drawText(16, toString(), 125, 25, DrawEngine.BLACK);
	}
}
