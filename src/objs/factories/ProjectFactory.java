package objs.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import objs.Skill;
import objs.activities.impl.ProjectActivity;

public class ProjectFactory {

	private static Random random = new Random();
	
	public enum ProjectType {FREE, NORMAL, CORPORATE};

	public enum Difficulty {VERY_EASY, EASY, NORMAL, HARD, VERY_HARD};
	
	public static ProjectActivity getRandomProject(HashMap<Skill, Integer> studioReputation) {
		
		ProjectType type = ProjectType.values()[random.nextInt(ProjectType.values().length)];
		ProjectCategory category = ProjectCategory.values()[random.nextInt(ProjectCategory.values().length)];
		Difficulty difficulty = Difficulty.values()[random.nextInt(Difficulty.values().length)];
		
		ArrayList<Skill> skillsRequired = getSkillsRequired(category);
		
		int features = getNumFeatures(difficulty);
		int revenue = getMoneyPerFeature(difficulty) * features;
		int reputation = getReputationPerFeature(difficulty) * features;
		int timePerWork = getTimePerWork(difficulty);
		
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
		
		return new ProjectActivity("TODO name", features, revenue, reputation, timePerWork, type, difficulty, skillsRequired);
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
				
			case VERY_HARD: /* Between 6 and 8 features */
				return 6 + random.nextInt(2);
				
			default:
				throw new IllegalArgumentException();
		}
	}
	
	private static int getMoneyPerFeature(Difficulty difficulty) {
		
		switch(difficulty) {
			case VERY_EASY: /* Between 25 to 35 */ 
				return 35 + random.nextInt(10);
				
			case EASY: /* Between 35 to 50 */
				return 45 + random.nextInt(15);
				
			case NORMAL: /* 45 to 65 */
				return 55 + random.nextInt(20);
				
			case HARD: /* 55 to 80 */
				return 65 + random.nextInt(15);
				
			case VERY_HARD: /* 65 to 90 */
				return 75 + random.nextInt(25);
				
			default:
				throw new IllegalArgumentException();
			
		}
	}
	
	private static int getReputationPerFeature(Difficulty difficulty) {
		switch(difficulty) {
			case VERY_EASY: /* Between 1 to 3 */
				return 1 + random.nextInt(2);
				
			case EASY: /* Between 2 to 4 */
				return 2 + random.nextInt(2);
				
			case NORMAL: /* Between 3 to 7 */
				return 3 + random.nextInt(4);
				
			case HARD: /* Between 6 to 8 */
				return 6 + random.nextInt(2);
				
			case VERY_HARD: /* Between 8 to 10 */
				return 8 + random.nextInt(2);
				
			default:
				throw new IllegalArgumentException();
		
		}
	}
	
	private static int getTimePerWork(Difficulty difficulty) {
		int minutesPerWork = ProjectActivity.HOURS_PER_WORK;
		return minutesPerWork;
		/*
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
		*/
	}

}
