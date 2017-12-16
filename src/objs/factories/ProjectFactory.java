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
			case VERY_EASY: /* Between 3 and 5 features */
				return 3 + random.nextInt(2);
				
			case EASY: /* Between 3 and 10 features */
				return 3 + random.nextInt(7);
				
			case NORMAL: /* Between 5 and 15 features */
				return 5 + random.nextInt(10);
				
			case HARD: /* Between 10 and 15 features */
				return 10 + random.nextInt(5);
				
			case VERY_HARD: /* Between 15 and 20 features */
				return 15 + random.nextInt(5);
				
			default:
				throw new IllegalArgumentException();
		}
	}
	
	private static int getMoneyPerFeature(Difficulty difficulty) {
		
		switch(difficulty) {
			case VERY_EASY: /* Between 5 to 10 */ 
				return 5 + random.nextInt(5);
				
			case EASY: /* Between 10 to 15 */
				return 10 + random.nextInt(5);
				
			case NORMAL: /* 10 to 20 */
				return 10 + random.nextInt(10);
				
			case HARD: /* 15 to 20 */
				return 15 + random.nextInt(5);
				
			case VERY_HARD: /* 20 to 25 */
				return 20 + random.nextInt(5);
				
			default:
				throw new IllegalArgumentException();
			
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
