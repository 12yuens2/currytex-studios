package objs.factories;

import java.util.Random;

import objs.activities.impl.ProjectActivity;

public class ProjectFactory {

	private static Random random = new Random();
	
	public enum ProjectType {FREE, NORMAL, CORPORATE};
	public enum Difficulty {VERY_EASY, EASY, NORMAL, HARD, VERY_HARD};
	
	public static ProjectActivity getRandomProject() {
		ProjectType type = ProjectType.values()[random.nextInt(ProjectType.values().length)];
		Difficulty difficulty = Difficulty.values()[random.nextInt(Difficulty.values().length)];
		
		int workRequired = getWorkRequired(difficulty);
		int revenue = getRevenue(difficulty) * workRequired;
		int reputation = getReputation(difficulty);
		int timePerWork = getTimePerWork(difficulty);
		
		switch (type) {
			case CORPORATE:
				double ratio = 2 + random.nextDouble();
				revenue *= 1 + ratio;
				reputation -= reputation * ratio;
				break;
				
			case FREE:
				revenue = 0;
				reputation += reputation * random.nextDouble();
				break;
				
			case NORMAL:
				revenue = (int) (revenue * 0.7);
				reputation = (int) (reputation * 0.7);
				break;
		
		}
		
		return new ProjectActivity("TODO name", workRequired, revenue, reputation, timePerWork, type, difficulty);
	}
	
	
	private static int getWorkRequired(Difficulty difficulty) {
		int workRequired = random.nextInt(10) + 1; //TODO 10 as paramaters
		
		switch (difficulty) {
			case VERY_EASY:
				return 1 + (int) (workRequired * 0.7);
				
			case EASY:
				return 1 + (int) (workRequired * 0.85);
				
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
	
	private static int getRevenue(Difficulty difficulty) {
		int revenue = random.nextInt(100) + 1; //TODO scale with reputation
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
	
	private static int getReputation(Difficulty difficulty) {
		int reputation = 1 + random.nextInt(20);
		switch(difficulty) {
			case VERY_EASY:
				return (int) (reputation * 0.75);
				
			case EASY:
				return (int) (reputation * 0.9);
				
			case NORMAL:
				return reputation;
				
			case HARD:
				return (int) (reputation * 1.5);
				
			case VERY_HARD:
				return (int) (reputation * 2.5);
				
			default:
				return reputation;
		
		}
	}
	
	private static int getTimePerWork(Difficulty difficulty) {
		int minutesPerWork = ProjectActivity.MINUTES_PER_WORK;
		
		switch(difficulty) {
			case VERY_EASY:
				return (int) (minutesPerWork * 0.5);
				
			case EASY:
				return (int) (minutesPerWork * 0.85);
	
			case NORMAL:
				return minutesPerWork;
				
			case HARD:
				return (int) (minutesPerWork * 1.5);
				
			case VERY_HARD:
				return (int) (minutesPerWork * 2);
				
			default:
				return minutesPerWork;
		
		}
	}

}
