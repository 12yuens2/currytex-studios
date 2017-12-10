package objs.factories;

import java.util.Random;

import objs.Worker;

public class WorkerFactory {

	public static Random random = new Random();
	
	public static Worker getRandomWorker() {
		//TODO random skills/wage
		Worker worker = new Worker("bob");

		worker.wage = random.nextInt(50); 
		
		return worker;
		
	}
}
