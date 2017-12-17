package objs;

public enum Skill {
	RUBY, JAVA, C, HASKELL, PYTHON, WEB, SQL;
	
	@Override
	public String toString() {
		switch(this) {
			case C: 		return "C";
			case HASKELL: 	return "Haskell";
			case JAVA: 		return "Java";
			case RUBY: 		return "Ruby";
			case PYTHON: 	return "Python";
			case SQL: 		return "SQL";
			case WEB: 		return "Web.js";
			default: 		throw new IllegalArgumentException();
		}
	}
}
