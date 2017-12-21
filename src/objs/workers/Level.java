package objs.workers;


/**
 * Level for skills for workers. 
 * Levels need experience to level up. 
 *
 */
public class Level implements Comparable<Level> {

	public int level, expToLevel, exp;
	
	public Level() {
		this.level = 1;
		this.expToLevel = 100;
		this.exp = 0;
	}
	
	/**
	 * Constructor that starts at a given level.
	 * @param level - level to start at
	 */
	public Level(int level) {
		this();
		
		/* Level up to ensure exp and expToLevel are as expected */
		for (int i = 0; i < level - 1; i++) levelUp();
	}
	
	/**
	 * Gain exp for this level.
	 * Level up if there is enough exp. 
	 * @param expGain
	 */
	public void gainExp(int expGain) {
		exp += expGain;
		if (exp >= expToLevel) {
			exp -= expToLevel;
			levelUp();
		}
	}
	
	private void levelUp() {
		level++;
		expToLevel = expToLevel*level; //TODO
	}

	@Override
	public int compareTo(Level otherLevel) {
		
		/* Check if level is higher */
		if (level > otherLevel.level) {
			return 1;
		} 
		else if (otherLevel.level > level) {
			return -1;
		}
		else {
		
			/* If level was the same, check is exp is higher */
			if (exp > otherLevel.exp) {
				return 1;
			}
			else if (otherLevel.exp > exp) {
				return -1;
			}
			else {
				
				/* Levels are equivalent if level and exp are the same */
				return 0;
			}
		}
	}
}
