package objs.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.event.ListSelectionEvent;

import objs.workers.Skill;

import java.util.Random;

public enum ProjectCategory {
	WEB, GAME, FINTECH, DATA, SYSTEMS;	
	
	
	public List<Skill> getSkillsRequired() {

		switch (this) {
			case DATA:
				return Arrays.asList(Skill.PYTHON, Skill.HASKELL, Skill.SQL);
				
			case FINTECH:
				return Arrays.asList(Skill.RUBY, Skill.JS, Skill.JAVA, Skill.HASKELL);
				
			case GAME:
				return Arrays.asList(Skill.C, Skill.JAVA, Skill.PYTHON);
				
			case SYSTEMS:
				return Arrays.asList(Skill.C, Skill.PYTHON);
				
			case WEB:
				return Arrays.asList(Skill.JS, Skill.SQL, Skill.RUBY);
				
			default:
				throw new IllegalArgumentException();
		}
	}
		
};
