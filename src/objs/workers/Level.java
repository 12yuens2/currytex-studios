package objs.workers;

public class Level implements Comparable {

	//TODO max level?
	
	public int level, expToLevel, exp;
	
	public Level() {
		this.level = 1;
		this.expToLevel = 100;
		this.exp = 0;
	}
	
	public Level(int level) {
		this();
		
		for (int i = 0; i < level - 1; i++) levelUp();
	}
	
	public void gainExp(int expGain) {
		exp += expGain;
		if (exp >= expToLevel) {
			exp = 0;
			levelUp();
		}
	}
	
	private void levelUp() {
		level++;
		expToLevel = expToLevel*level;
	}

	@Override
	public int compareTo(Object o) {
		Level otherLevel = (Level) o;
		
		if (level > otherLevel.level) {
			return 1;
		} 
		else if (otherLevel.level > level) {
			return -1;
		}
		else {
			if (exp > otherLevel.exp) {
				return 1;
			}
			else if (otherLevel.exp > exp) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
}
