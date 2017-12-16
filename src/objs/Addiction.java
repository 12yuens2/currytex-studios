package objs;

public enum Addiction {
	NONE, SOMETIMES, FREQUENT, ADDICTED;
	
	@Override
	public String toString() {
		switch(this) {
			case ADDICTED:
				return "Addicted";

			case FREQUENT:
				return "Frequent";
				
			case NONE:
				return "None";
				
			case SOMETIMES:
				return "Sometimes";
				
			default:
				throw new IllegalArgumentException();
			
		}
	}
}
