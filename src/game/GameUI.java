package game;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import app.DevStudios;
import game.states.GameState;
import objs.Addiction;
import objs.Level;
import objs.Skill;
import objs.Worker;
import objs.activities.impl.MoreMoneyStatActivity;
import processing.core.PVector;
import ui.UIObject;
import ui.WorkerBox;
import ui.WorkerInfo;
import ui.buttons.impl.MenuButton;
import ui.locations.Location;
import ui.locations.impl.GetCoffeeLocation;
import ui.locations.impl.GetNewProjectLocation;
import ui.locations.impl.MoreMoneyStatLocation;
import ui.locations.impl.MoreReputationStatLocation;
import ui.locations.impl.ProjectLocation;
import ui.locations.impl.RecruitLocation;
import ui.locations.impl.RestLocation;
import ui.menus.Menu;
import ui.menus.impl.UpgradesMenu;

public class GameUI {

	public float mouseX, mouseY;
	
	public GameContext context;
	
	public ArrayList<MenuButton> openMenuButtons;
	public ArrayList<Location> locations; /* Represents locations */
	public ArrayList<ProjectLocation> projectLocations; /* Represents project locations */
	public ArrayList<WorkerBox> boxes; /* Represents workers */
	public ArrayList<WorkerInfo> workerInfos; /* Represents workers */
	
	public GameUI(GameContext context) {
		this.context = context;
		
		this.openMenuButtons = new ArrayList<>();
		this.locations = new ArrayList<>();
		this.projectLocations = new ArrayList<>();
		this.boxes = new ArrayList<>();
		this.workerInfos = new ArrayList<>();
		
		testInit();
	}

	private void testInit() {
		
		//TODO debug
		Worker a = new Worker("a");
		Level java = new Level();
//		java.level = 2;
//		a.skills.put(Skill.JAVA, java);
//		a.addMoreMoneyLevel();
//		a.addictionLevel = Addiction.ADDICTED;
		
		Worker b = new Worker("b");
		
		if (context != null ) {
			context.workers.add(a);
//			context.workers.add(b);
		}
		
		/* Draw developer boxes */
		int yCoord = 75;
		int numWorkers = 8;
		for (int i = 0; i < numWorkers; i++) {
			boxes.add(new WorkerBox(DevStudios.SCREEN_X - 250, yCoord + (((DevStudios.SCREEN_Y - 50)/numWorkers) * i), 45));
		}
		boxes.get(0).worker = a;
		
		for (WorkerBox box : boxes) {
			workerInfos.add(new WorkerInfo(box));
		}
//		boxes.get(1).worker = b;
		
		/* Draw project locations */
		int xCoord = 100;
		int numProjects = 4;
		for (int i = 0; i < numProjects; i++) {
			projectLocations.add(new ProjectLocation(xCoord + (((DevStudios.SCREEN_X - 525)/numProjects) * i), DevStudios.SCREEN_Y - 200, 100));
		}
		
		/* Rest location */
		locations.add(new RestLocation());
		
		/* Recruit location */
		locations.add(new RecruitLocation());
		
		/* Get new project location */
		locations.add(new GetNewProjectLocation(projectLocations));
		
		/* Get coffee location */
		locations.add(new GetCoffeeLocation());
		
		
		locations.add(new MoreMoneyStatLocation());
		locations.add(new MoreReputationStatLocation());
		
		/* Draw bottom menu buttons */
		drawBottomMenu();



		
	}
	
	//TODO make actual menus not the abstract menu class
	private void drawBottomMenu() {
		/* Draw all workers menu */
		openMenuButtons.add(new MenuButton(175, 850, 100, 30, DrawEngine.parent.color(20, 20, 200), 
				   new Menu(DevStudios.SCREEN_X/2, DevStudios.SCREEN_Y/2, 600, 300)));
		
		/* Draw upgrades menu */
		openMenuButtons.add(new MenuButton(400, 850, 100, 30, DrawEngine.parent.color(20, 200, 20),
				   new UpgradesMenu()));
		
		/* Draw some other menu */
	}
	
	
	public void display(DrawEngine drawEngine) {
		
		/* Draw top UI for information */
		context.studio.display(drawEngine);
		
		/* Display game time */
		context.gameTime.display(drawEngine);
		
		
		drawUIObjects(drawEngine,
				openMenuButtons, projectLocations, locations, workerInfos);
		
		for (WorkerInfo info : workerInfos) {
			if (info.workerBox.mouseLocked) info.workerBox.display(drawEngine);
		}
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
	

	public void updateMouse(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		updateBoxes();
		
	}
	
	private void updateBoxes() {
		for (WorkerBox b : boxes) {
			b.mouseOver = b.contains(mouseX, mouseY);
		}
		
	}

	public GameState handleLeftClick(GameState currentState) {
		
		Optional<GameState> result = Stream.of(boxes.stream(), locations.stream(), projectLocations.stream(), openMenuButtons.stream())
				.flatMap(Function.identity())
				.filter(o -> o.contains(mouseX, mouseY))
				.map(o -> o.handleLeftClick(mouseX, mouseY, context, currentState))
				.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
				.findFirst();
		
		return result.isPresent() ? result.get() : currentState;
	}
	
	public GameState handleRightClick(GameState currentState) {
		Optional<GameState> result = Stream.of(boxes.stream(), locations.stream(), projectLocations.stream())
				.flatMap(Function.identity())
				.filter(o -> o.contains(mouseX, mouseY))
				.map(o -> o.handleRightClick(mouseX, mouseY, context, currentState))
				.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
				.findFirst();
		
		return result.isPresent() ? result.get() : currentState;
	}
	
//		for (WorkerBox b : boxes) {
//			b.handleLeftClick(mouseX, mouseY);
//		}
//		
//		Optional<GameState> result = boxes.stream()
//			.filter(b -> b.contains(mouseX, mouseY))
//			.map(b -> b.handleLeftClick(mouseX, mouseY))
//			.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
//			.findFirst();
//		
//		System.out.println(result);
//		
//		for (Location location : locations) {
//			if (location.contains(mouseX, mouseY)) {
//				location.handleLeftClick(mouseX, mouseY);
//			}
//		}
//		return currentState;
		
//	}


	public void handleMouseDrag() {
		for (WorkerBox box : boxes) {
			if (box.mouseLocked) {
				box.position = new PVector(mouseX - box.mouseXOffset, mouseY - box.mouseYOffset);
			}
		}
	}

	public void resetWorkerBoxes() {
		for (WorkerBox workerBox : boxes) {
			workerBox.reset();
		}
		
	}
	
}
