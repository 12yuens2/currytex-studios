package objs.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import game.GameModifiers;
import game.GameTime;
import objs.WorldTrend;
import objs.activities.impl.ProjectActivity;
import objs.workers.Skill;

public class ProjectFactory {

	private static Random random = new Random();
	
	public enum ProjectType {FREE, NORMAL, CORPORATE};

	public enum Difficulty {
		VERY_EASY, EASY, NORMAL, HARD, VERY_HARD;
		
		@Override
		public String toString() {
			switch(this) {
				case VERY_EASY: return "Very easy";
				case EASY: 		return "Easy";
				case NORMAL: 	return "Normal";
				case HARD: 		return "Hard";
				case VERY_HARD: return "Very hard";
				default: 		throw new IllegalArgumentException();
			}
		}

		public int minExp() {
			switch(this) {
				case VERY_EASY: return 10;
				case EASY: 		return 12;
				case NORMAL:	return 15;
				case HARD:		return 17;
				case VERY_HARD:	return 19;
				default:		throw new IllegalArgumentException();
			}
		}
	};
	
	/**
	 * 
	 * @param studioReputation
	 * @param trend
	 * @return
	 */
	public static ProjectActivity getRandomProject(HashMap<Skill, Integer> studioReputation, WorldTrend trend) {
		ProjectType type = ProjectType.values()[random.nextInt(ProjectType.values().length)];
		ProjectCategory category = ProjectCategory.values()[random.nextInt(ProjectCategory.values().length)];
		Difficulty difficulty = Difficulty.values()[random.nextInt(Difficulty.values().length)];
		
		ArrayList<Skill> skillsRequired = getSkillsRequired(category);
		
		/* Minimum modifier is 0.2 */
		float categoryModifier = 0.3f + Math.min(0.2f, trend.categoryTrends.get(category));
		
		/* Base project properties */
		int features = getNumFeatures(difficulty);
		int revenue = (int) ((getMoneyPerFeature(difficulty) * features) * categoryModifier);
		int reputation = (int) ((getReputationPerFeature(difficulty) * features) * categoryModifier);
		int timePerWork = getTimePerWork(difficulty);
		
		
		/* Apply modifiers */
		for (Skill s : skillsRequired) {
			if (studioReputation.containsKey(s)) {
				revenue += revenue * studioReputation.get(s)/250f;
			}
		}
		revenue *= GameModifiers.revenueModifier;
		reputation *= GameModifiers.reputationModifier;
		
		/* Change project properties based on type of project */
		switch (type) {
			case CORPORATE:
				double ratio = 1 + random.nextDouble();
				revenue *= 1 + ratio;
				reputation -= (reputation * ratio);
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
		
		/* Time limit */
		int timeLimit = getTimeLimit(features, timePerWork, difficulty);
		
		return new ProjectActivity(features, revenue, reputation, timePerWork, timeLimit, type, difficulty, skillsRequired);
	}
	
	
	private static int getTimeLimit(int features, int timePerWork, Difficulty difficulty) {
		int minTime = features * timePerWork;
		int time = (int) (minTime + (minTime * Math.max(0.3f + random.nextDouble(), 0.75f)));
		
		int daysLimit = time/GameTime.HOURS_IN_DAY + 2;
		
		return daysLimit;

	}


	private static ArrayList<Skill> getSkillsRequired(ProjectCategory category) {
		ArrayList<Skill> skillsRequired = new ArrayList<>();
		List<Skill> skills = category.getSkillsRequired();
		Collections.shuffle(skills);
		
		for (int i = 0; i < 1 + random.nextInt(skills.size()); i++) {
			skillsRequired.add(skills.get(i));
		}
		
		return skillsRequired;
		
	}


	private static int getNumFeatures(Difficulty difficulty) {
		
		switch (difficulty) {
			case VERY_EASY: /* Between 2 and 4 features */
				return 2 + random.nextInt(2);
				
			case EASY: /* Between 3 and 5 features */
				return 3 + random.nextInt(2);
				
			case NORMAL: /* Between 3 and 7 features */
				return 3 + random.nextInt(2);
				
			case HARD: /* Between 5 and 7 features */
				return 5 + random.nextInt(2);
				
			case VERY_HARD: /* Between 8 and 10 features */
				return 8 + random.nextInt(2);
				
			default:
				throw new IllegalArgumentException();
		}
	}
	
	private static int getMoneyPerFeature(Difficulty difficulty) {
		
		switch(difficulty) {
			case VERY_EASY: /* Between 25 to 35 */ 
				return 100 + random.nextInt(50);
				
			case EASY: /* Between 35 to 50 */
				return 100 + random.nextInt(75);
				
			case NORMAL: /* 45 to 65 */
				return 100 + random.nextInt(100);
				
			case HARD: /* 55 to 80 */
				return 125 + random.nextInt(50);
				
			case VERY_HARD: /* 65 to 90 */
				return 125 + random.nextInt(75);
				
			default:
				throw new IllegalArgumentException();
			
		}
	}
	
	private static int getReputationPerFeature(Difficulty difficulty) {
		switch(difficulty) {
			case VERY_EASY: /* Between 1 to 3 */
				return 5 + random.nextInt(2);
				
			case EASY: /* Between 2 to 4 */
				return 5 + random.nextInt(5);
				
			case NORMAL: /* Between 3 to 7 */
				return 5 + random.nextInt(10);
				
			case HARD: /* Between 6 to 8 */
				return 8 + random.nextInt(7);
				
			case VERY_HARD: /* Between 8 to 10 */
				return 8 + random.nextInt(12);
				
			default:
				throw new IllegalArgumentException();
		
		}
	}
	
	private static int getTimePerWork(Difficulty difficulty) {
		int minutesPerWork = ProjectActivity.HOURS_PER_WORK;
		
		switch(difficulty) {
			case VERY_EASY:
				return (int) (minutesPerWork * 0.85);
				
			case EASY:
				return (int) (minutesPerWork * 0.9);
	
			case NORMAL:
				return minutesPerWork;
				
			case HARD:
				return (int) (minutesPerWork * 1.2);
				
			case VERY_HARD:
				return (int) (minutesPerWork * 1.5);
				
			default:
				return minutesPerWork;
		
		}
		
	}

}
