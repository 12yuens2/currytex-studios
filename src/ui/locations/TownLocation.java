package ui.locations;

import game.DrawEngine;
import game.GameTime;
import processing.core.PConstants;
import ui.Animation;

/**
 * A location in the town that has animation.
 *
 */
public abstract class TownLocation extends Location {

	public static final int TOWN_LOCATION_SIZE = 85;
	
	/* Animation that plays when a worker is in this location */
	public Animation animation;
	
	public TownLocation(float xPos, float yPos, String imagePrefix, GameTime time) {
		super(xPos, yPos, TOWN_LOCATION_SIZE, imagePrefix + "0.png", imagePrefix + "1.png");
		
		this.animation = new Animation(time, imagePrefix, 4, TOWN_LOCATION_SIZE*2);
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		if (workerCollision) {
			drawEngine.drawImage(PConstants.CENTER, hoverImage, position.x, position.y);
		}
		else {
			/* Draw animation if there are workers in this location */
			if (workers.size() > 0) {
				animation.display(position, drawEngine);
			} 
			else {
				super.display(drawEngine);
			}
		}
	}

}
