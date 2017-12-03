package objs;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Project {
	
	public enum Difficulty {VERY_EASY, EASY, NORMAL, HARD, VERY_HARD};
	
	
	/* 
	 * Default time needed per work load.
	 * Increases with difficulty and as game progresses.
	 */
	public static int MINUTES_PER_WORK = 120;
	
	public static Random random = new Random();
	
	public String name;
	
	public int workRequired;
	public int revenue;
	public int timePerWork;
	
	public final Difficulty difficulty;
	
	public Project(String name, int workRequired, int revenue, Difficulty difficulty) {
		this.name = name;
		this.difficulty = difficulty;
		
		this.workRequired = getModifiedWorkRequired(workRequired);
		this.revenue = getModifiedRevenue(revenue);
		this.timePerWork = getModifiedTime();
	}
	
	private int getModifiedWorkRequired(int workRequired) {
		switch (difficulty) {
			case VERY_EASY:
				return (int) (workRequired * 0.7);
				
			case EASY:
				return (int) (workRequired * 0.85);
				
			case NORMAL:
				return workRequired;
				
			case HARD:
				return (int) (workRequired * 1.5);
				
			case VERY_HARD:
				return (int) (workRequired * 2.5);
				
			default:
				return workRequired;
		}
	}
	
	private int getModifiedRevenue(int revenue) {
		switch(difficulty) {
			case VERY_EASY:
				return (int) (revenue * 0.8);
				
			case EASY:
				return (int) (revenue * 0.9);
				
			case NORMAL:
				return revenue;
				
			case HARD:
				return (int) (revenue * 2);
				
			case VERY_HARD:
				return (int) (revenue * 3);
				
			default:
				return revenue;
			
		}
	}

	private int getModifiedTime() {
		switch(difficulty) {
			case VERY_EASY:
				return (int) (MINUTES_PER_WORK * 0.5);
				
			case EASY:
				return (int) (MINUTES_PER_WORK * 0.85);

			case NORMAL:
				return MINUTES_PER_WORK;
				
			case HARD:
				return (int) (MINUTES_PER_WORK * 1.5);
				
			case VERY_HARD:
				return (int) (MINUTES_PER_WORK * 2);
				
			default:
				return MINUTES_PER_WORK;
		
		}
	}
	
	

	public static Project randomProject() {
		Random random = new Random();
		Difficulty difficulty = Arrays.asList(Difficulty.values()).get(random.nextInt(Difficulty.values().length));
		
		return new Project("Java 1", random.nextInt(10) + 1, random.nextInt(100) + 15, difficulty);
	}
}
