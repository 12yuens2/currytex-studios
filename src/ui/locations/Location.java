package ui.locations;
import java.util.ArrayList;
import java.util.Optional;

import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;

import game.DrawEngine;
import game.GameContext;
import game.GameModifiers;
import game.states.GameState;
import objs.activities.Activity;
import objs.activities.impl.ProjectActivity;
import objs.workers.Skill;
import objs.workers.Worker;
import processing.core.PConstants;
import processing.core.PImage;
import ui.UIObject;

public abstract class Location extends UIObject {

	public static final int LOCATION_SIZE = 75;
	public final PImage image;
	public final PImage hoverImage;

	public boolean workerCollision;
	
	public ArrayList<Worker> workers;

	
	public Location(float xPos, float yPos, int size, String imageFile, String hoverImageFile) {
		super(xPos, yPos, size);
		
		this.workers = new ArrayList<>();
		this.workerCollision = false;
		
		this.image = DrawEngine.parent.loadImage(imageFile);
		this.image.resize(size*2, size*2);
		this.hoverImage = DrawEngine.parent.loadImage(hoverImageFile);
		this.hoverImage.resize(size*2, size*2);
	}
	
	public Location(float xPos, float yPos, int size, String imageFile) {
		this(xPos, yPos, size, imageFile, imageFile);
	}
	
	/**
	 * Create Activity associated with this location.
	 * @return
	 */
	public abstract Activity getActivity();
	
	/**
	 * Check if it is possible to add a new worker to this location.
	 * @return - If the worker can be added to this location.
	 */
	public boolean canAddWorker() {
		return workers.size() < GameModifiers.locationMaxWorkers;
	}
	
	/**
	 * Add a worker to this location.
	 */
	public void addWorker(Worker worker) {
		workers.add(worker);
	}
	
	
	@Override
	public void display(DrawEngine drawEngine) {
		if (workerCollision) {
			drawEngine.drawImage(PConstants.CENTER, hoverImage, position.x, position.y);
		}
		else {
			drawEngine.drawImage(PConstants.CENTER, image, position.x, position.y);
		}
	}
	

	@Override
	public Optional<GameState> handleLeftClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		manualDecrement(GameModifiers.manualClickPower);
		
		return Optional.empty();
	}

	@Override
	public Optional<GameState> handleRightClick(float mouseX, float mouseY, GameContext context,
			GameState currentState) {
		
		return Optional.empty();
	}

	
	protected void manualDecrement(float amount) {
		for (Worker worker : workers) {
			worker.work(amount);
//			worker.workTimer = Math.max(0, worker.workTimer - amount);
		}
	}


}
