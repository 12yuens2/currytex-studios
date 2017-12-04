package objs;

import java.util.HashMap;

public class Worker {

	public String name;
	public HashMap<Skill, Level> skills;
	
	public int wage;
	
	public int workTimer;
	
	public Worker(String name) {
		this.name = name;
		this.wage = 0;
		this.skills = new HashMap<>();
	}
	
	
	public void integrate() {
		
	}
}
