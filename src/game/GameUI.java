package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import app.CurryTeXStudios;
import game.states.GameState;
import objs.factories.WorkerFactory;
import objs.workers.Worker;
import processing.core.PVector;
import ui.UIObject;
import ui.WorkerBox;
import ui.WorkerInfo;
import ui.buttons.impl.MenuButton;
import ui.locations.Location;
import ui.locations.impl.GetNewProjectLocation;
import ui.locations.impl.ProjectLocation;
import ui.menus.impl.ReputationMenu;
import ui.menus.impl.SalaryMenu;
import ui.menus.impl.UpgradesMenu;

public class GameUI {

	public float mouseX, mouseY;
	
	public GameContext context;
	
	public ArrayList<MenuButton> openMenuButtons;
	public ArrayList<Location> locations; 				
	public ArrayList<ProjectLocation> projectLocations; 
	public ArrayList<WorkerBox> boxes; 					
	public ArrayList<WorkerInfo> workerInfos; 			
	
	public GameUI(GameContext context) {
		this.context = context;
		
		this.openMenuButtons = new ArrayList<>();
		this.locations = new ArrayList<>();
		this.projectLocations = new ArrayList<>();
		this.boxes = new ArrayList<>();
		this.workerInfos = new ArrayList<>();
		
		init();
	}

	private void init() {
		Worker firstWorker = WorkerFactory.getPlainWorker(context.studio);
		
		if (context != null ) {
			context.workers.add(firstWorker);
		}
		
		/* Draw developer boxes */
		int yCoord = 75;
		int numWorkers = 8;
		for (int i = 0; i < numWorkers; i++) {
			boxes.add(new WorkerBox(CurryTeXStudios.SCREEN_X - 235, yCoord + (((CurryTeXStudios.SCREEN_Y - 40)/numWorkers) * i), 45));
		}
		boxes.get(0).worker = firstWorker;
		
		for (WorkerBox box : boxes) {
			workerInfos.add(new WorkerInfo(box));
		}
		
		/* Unlock second worker box */
		workerInfos.get(1).locked = false;
		
		drawLocations();
		drawBottomMenu();
	}
	
	private void drawLocations() {
		
		/* Draw project locations */
		int xCoord = 100;
		int numProjects = 4;
		for (int i = 0; i < numProjects; i++) {
			projectLocations.add(new ProjectLocation(
					xCoord + (((CurryTeXStudios.SCREEN_X - 525)/numProjects) * i), CurryTeXStudios.SCREEN_Y - 195, 100));
		}

		/* Get new project location */
		locations.add(new GetNewProjectLocation(projectLocations));

	}
	

	private void drawBottomMenu() {
		/* Draw all workers menu */
		openMenuButtons.add(new MenuButton("Salaries", 175, 850, 100, 30, new SalaryMenu(workerInfos)));
		
		/* Draw upgrades menu */
		openMenuButtons.add(new MenuButton("Upgrades", 400, 850, 100, 30, new UpgradesMenu()));
		
		/* Draw reputations menu */
		openMenuButtons.add(new MenuButton("Reputation", 625, 850, 100, 30, new ReputationMenu(context.studio)));
	}
	
	
	public void display(DrawEngine drawEngine) {
		
		/* Draw top UI for information */
		context.gameTime.display(drawEngine);
		context.studio.display(drawEngine);
		
		/* Draw all other UI objects */
		drawUIObjects(drawEngine,
				openMenuButtons, projectLocations, locations, workerInfos);
		
		/* Draw dragging worker in front of all objects */
		drawDraggedWorker(drawEngine);		
	}
	
	/**
	 * Draw all UI objects
	 * @param drawEngine
	 * @param uiObjectLists - Vararg of UI object lists
	 */
	private void drawUIObjects(DrawEngine drawEngine, ArrayList<? extends UIObject>... uiObjectLists) {
		for (ArrayList<? extends UIObject> uiObjects : uiObjectLists) {
			for (UIObject object : uiObjects) {
				object.display(drawEngine);
			}
		}
	}
	
	/**
	 * Draws any worker box being dragged on top of all other UI elements.
	 * @param drawEngine
	 */
	private void drawDraggedWorker(DrawEngine drawEngine) {
		boolean hasDrag = false;
		
		Iterator<WorkerInfo> infoIt = workerInfos.iterator();
		while(infoIt.hasNext() && !hasDrag) {
			WorkerInfo info = infoIt.next();
			
			if (info.workerBox.mouseLocked) {
				info.workerBox.display(drawEngine);
				hasDrag = true;
			}
		}
	}
	

	/**
	 * Update stored mouse position.
	 * @param mouseX
	 * @param mouseY
	 */
	public void updateMouse(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		updateBoxes();
	}
	
	/**
	 * Update WorkerBox's mouseOver.
	 */
	private void updateBoxes() {
		for (WorkerBox b : boxes) {
			b.mouseOver = b.contains(mouseX, mouseY);
		}
	}
	

	/**
	 * Handler for left click.
	 * Calls the left click handler for all relevant UI objects and returns the first new game state found. 
	 * @param currentState
	 * @return The next game state.
	 */
	public GameState handleLeftClick(GameState currentState) {

		Optional<GameState> result = Stream.of(boxes.stream(), locations.stream(), projectLocations.stream(), openMenuButtons.stream())
				.flatMap(Function.identity())
				.filter(o -> o.contains(mouseX, mouseY))
				.map(o -> o.handleLeftClick(mouseX, mouseY, context, currentState))
				.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
				.findFirst();
		
		return result.isPresent() ? result.get() : currentState;
	}
	
	/**
	 * Handler for right click. 
	 * Calls the right click handler for all relevant UIObjects and returns the first new game state found.
	 * @param currentState
	 * @return The next game state.
	 */
	public GameState handleRightClick(GameState currentState) {
		Optional<GameState> result = Stream.of(boxes.stream(), workerInfos.stream(), locations.stream(), projectLocations.stream())
				.flatMap(Function.identity())
				.filter(o -> o.contains(mouseX, mouseY))
				.map(o -> o.handleRightClick(mouseX, mouseY, context, currentState))
				.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
				.findFirst();
		
		return result.isPresent() ? result.get() : currentState;
	}


	/**
	 * Handler for mouse drag. 
	 * Moves any worker box that is drag locked.
	 */
	public void handleMouseDrag() {
		for (WorkerBox box : boxes) {
			if (box.mouseLocked) {
				box.position = new PVector(mouseX - box.mouseXOffset, mouseY - box.mouseYOffset);
				box.checkCollision(locations);
			}
		}
	}

	/**
	 * Reset all worker boxes to their original positions.
	 */
	public void resetWorkerBoxes() {
		for (WorkerBox workerBox : boxes) {
			workerBox.reset();
		}
		
	}
	
}
