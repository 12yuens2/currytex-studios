package objs.workers;

public enum Addiction {
	NONE, SOMETIMES, FREQUENT, ADDICTED;
	
	@Override
	public String toString() {
		switch(this) {
			case ADDICTED: 	return "Addicted";
			case FREQUENT:	return "Frequent";
			case NONE: 		return "None";
			case SOMETIMES: return "Sometimes";
			default:		throw new IllegalArgumentException();
		}
	}
	
	public int drinkChance() {
		switch(this) {
			case NONE:		return -1;
			case ADDICTED: 	return 100;
			case FREQUENT:	return 150;
			case SOMETIMES:	return 250;
			default:		throw new IllegalArgumentException();
		}
	}
	
	public int stallChance() {
		switch(this) {
			case NONE:		return -1;
			case ADDICTED: 	return 2;
			case FREQUENT:	return 4;
			case SOMETIMES:	return 6;
			default:		throw new IllegalArgumentException();
		}
	}

	public String level() {
		switch(this) {
			case NONE: 		return "None";
			case ADDICTED:  return "!!!";
			case FREQUENT:  return "!!";
			case SOMETIMES: return "!";
			default: 		throw new IllegalArgumentException();		
		}
	}
}
