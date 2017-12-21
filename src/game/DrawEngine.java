package game;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import ui.UIObject;
import ui.WorkerInfo;

public class DrawEngine {
	
	public static PApplet parent;
	public static int BLACK;
	
	/* Standard box for menus/buttons */
	private PImage box;
	
	/* Icons */
	public PImage moneyIcon;
	public PImage reputationIcon;
	public PImage caffeineIcon;
	
	public PImage cIcon, haskellIcon, javaIcon, rubyIcon, pythonIcon, sqlIcon, jsIcon;
	
	/* Buttons */
	public PImage exitButton;
	
	/* Workers */
	public PImage workerInfoLocked, workerInfoUnlocked, workerInfo, workerBox;
	
    public DrawEngine(PApplet parent) {
    	DrawEngine.parent = parent;
    	BLACK = parent.color(0);
    	
    	box = parent.loadImage("imgs/box.png");
    	
    	loadIcons();
    	loadButtons();
    	loadWorkers();
    }
    
    private void loadIcons() {
    	int size = 35;
		moneyIcon = parent.loadImage("imgs/money-icon.png");
		reputationIcon = parent.loadImage("imgs/reputation-icon.png");
		caffeineIcon = parent.loadImage("imgs/coffee-icon.png");
		
		/* Language icons */
		cIcon = parent.loadImage("imgs/c-icon.png");
		haskellIcon = parent.loadImage("imgs/haskell-icon.png");
		javaIcon = parent.loadImage("imgs/java-icon.png");
		rubyIcon = parent.loadImage("imgs/ruby-icon.png");
		pythonIcon = parent.loadImage("imgs/python-icon.png");
		sqlIcon = parent.loadImage("imgs/sql-icon.png");
		jsIcon = parent.loadImage("imgs/js-icon.png");
		
		/* Resize all icons */
		resize(size, size, moneyIcon, reputationIcon, caffeineIcon,
				cIcon, haskellIcon, javaIcon, rubyIcon, pythonIcon, sqlIcon, jsIcon);
    }
    
    private void loadButtons() {
    	exitButton = parent.loadImage("imgs/exit.png");
    	exitButton.resize(40, 40);
    }
    
    private void loadWorkers() {
    	workerInfo = parent.loadImage("imgs/worker-info.png");
    	workerInfoLocked = parent.loadImage("imgs/worker-info-locked.png");
    	workerInfoUnlocked = parent.loadImage("imgs/worker-info-unlocked.png");
    	
    	/* Resize all worker info images */
    	resize(WorkerInfo.WIDTH * 2, WorkerInfo.HEIGHT * 2,
    			workerInfo, workerInfoLocked, workerInfoUnlocked);
    }
    
    /**
     * Helper function to resize any number of images
     * @param width
     * @param height
     * @param images
     */
    private void resize(int width, int height, PImage... images) {
    	for (PImage image : images) {
    		image.resize(width, height);
    	}
    }
    
    /**
     * Get a resized version of the normal box image.
     * @param width
     * @param height
     * @return
     */
    public PImage resizedBox(int width, int height) {
    	PImage box = this.box.copy();
    	box.resize(width, height);
    	
    	return box;
    }
    
    /**
     * Display all drawable objects.
     * @param drawables - List of drawable objects.
     */
    public void displayDrawables(ArrayList<? extends UIObject>... drawables) {  	
        for (ArrayList<? extends UIObject> drawList : drawables) {
	        	ArrayList<? extends UIObject> drawListCopy = new ArrayList<>(drawList);
	        	
	        	for (UIObject drawable : drawListCopy) {
	        		drawable.display(this);
	        	}
        }
    }

    /**
     * Generic image draw function for other classes to draw an image. 
     * @param mode - Processing mode for how to draw an object
     * @param image - image to draw
     * @param posX - x position of the image
     * @param posY - y position of the image
     */
    public void drawImage(int mode, PImage image, float posX, float posY) {
    	parent.imageMode(mode);
    	parent.image(image, posX, posY);
    }
    
    
	/**
	 * Generic draw text function for other classes to draw text to the screen with alignment.
	 */
	public void drawText(int alignX, int alignY, int textSize, String text, float posX, float posY, int col) {
		PFont font = parent.createFont("Lucida Sans Regular", textSize, true);
		
		parent.textFont(font, textSize);
		parent.fill(col);
		parent.textAlign(alignX, alignY);
		parent.text(text, posX, posY);
	}
	

	/**
	 * Draw text with center alignment. 
	 */
	public void drawText(int textSize, String text, float posX, float posY, int col) {	
		drawText(PConstants.CENTER, PConstants.CENTER, textSize, text, posX, posY, col);
	}
	
	/**
	 * Draw an ellipse based on given parameters.
	 * @param col - colour of the circle
	 * @param xPos - x coordinate of the circle 
	 * @param yPos - y coordinate of the circle
	 * @param width - width of the circle
	 * @param height - height of the circle 
	 * @param i 
	 */
	public void drawEllipse(int col, float xPos, float yPos, float width, float height) {
		parent.ellipseMode(PConstants.CENTER);
		parent.fill(col);
		parent.ellipse(xPos, yPos, width, height);
	}
	
	/**
	 * Draw an ellipse with opacity.
	 */
	public void drawEllipse(int col, float xPos, float yPos, float width, float height, int opacity) {
		parent.stroke(255);
		parent.ellipseMode(PConstants.CENTER);
		parent.fill(col, opacity);
		parent.ellipse(xPos, yPos, width, height);
	}
	
	
	/**
	 * Draw a rectangle based on given parameters.
	 * @param rectMode - Processing rectMode to specific how to draw the rectangle.
	 * @param col - colour of the rectangle
	 * @param xPos - x coordinate of the circle
	 * @param yPos - y coordinate of the circle
	 * @param width - width of the rectangle
	 * @param height - height of the rectangle
	 */
	public void drawRectangle(int rectMode, int col, float xPos, float yPos, float width, float height) {
		parent.rectMode(rectMode);
		parent.fill(col);
		parent.rect(xPos, yPos, width, height);
	}
	
	/**
	 * Draw a square.
	 */
	public void drawSquare(int rectMode, int col, PVector position, float size) {
		drawRectangle(rectMode, col, position.x, position.y, size, size);
	}
	
	/**
	 * Draw a rectangle with opacity.
	 */
	public void drawRectangle(int rectMode, int col, float xPos, float yPos, float width, float height, int opacity) {
		parent.rectMode(rectMode);
		parent.fill(col, opacity);
		parent.rect(xPos, yPos, width, height);
	}
	
	
	/**
	 * Draw an arc based on given parameters.
	 * @param col - colour of the arc.
	 * @param xPos - x coordinate
	 * @param yPos - y coordinate
	 * @param width - width of the arc
	 * @param height - height of the arc
	 * @param startValue - angle to start the arc in radians
	 * @param value - angle to end the arc in radians
	 */
	public void drawArc(int col, float xPos, float yPos, float width, float height, float startValue, float value) {
		parent.stroke(col);
		parent.strokeWeight(20);
		parent.noFill();
		parent.arc(xPos, yPos, width, height, startValue, value);
		
		/* Reset stroke weight to default. */
		parent.strokeWeight(1);
	}
	
	/**
	 * Draw an arc with opacity.
	 */
	public void drawArc(int col, float xPos, float yPos, float width, float height, float startValue, float value, float opacity) {
		parent.stroke(col, opacity);
		parent.strokeWeight(20);
		parent.noFill();
		parent.arc(xPos, yPos, width, height, startValue, value);
		
		/* Reset stroke weight to default. */
		parent.strokeWeight(1);
	}





    
}