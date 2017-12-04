package objs;

public class Level {

	//TODO max level?
	
	public int level, expToLevel, exp;
	
	public Level() {
		this.level = 1;
		this.expToLevel = 100;
		this.exp = 0;
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
}
