package objs.workers;

import game.DrawEngine;
import processing.core.PImage;

/**
 * All types of skills a worker could have.
 *
 */
public enum Skill {
	RUBY, JAVA, C, HASKELL, PYTHON, JS, SQL;
	
	@Override
	public String toString() {
		switch(this) {
			case C: 		return "C";
			case HASKELL: 	return "Haskell";
			case JAVA: 		return "Java";
			case RUBY: 		return "Ruby";
			case PYTHON: 	return "Python";
			case SQL: 		return "SQL";
			case JS: 		return "JS";
			default: 		throw new IllegalArgumentException();
		}
	}
	
	public PImage icon(DrawEngine drawEngine) {
		switch(this) {
			case C: 		return drawEngine.cIcon;
			case HASKELL:	return drawEngine.haskellIcon;
			case JAVA:		return drawEngine.javaIcon;
			case PYTHON: 	return drawEngine.pythonIcon;
			case RUBY:		return drawEngine.rubyIcon;
			case SQL:		return drawEngine.sqlIcon;
			case JS: 		return drawEngine.jsIcon;
			default: throw new IllegalArgumentException();
		}
	}
}
