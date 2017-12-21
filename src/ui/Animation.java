package ui;

import java.util.ArrayList;

import game.DrawEngine;
import game.GameTime;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;


/**
 * Class to display animation using multiple images.
 * Code adapted from: https://processing.org/examples/animatedsprite.html
 *
 */
public class Animation {

	public GameTime time;
	
	public ArrayList<PImage> images;
	
	public Animation(GameTime time, String imagePrefix, int count, int size) {
		this.time = time;
		
		this.images = new ArrayList<>();
		
		for (int i = 0; i < count; i++) {
			String file = imagePrefix + i + ".png";
			PImage animationImage = DrawEngine.parent.loadImage(file);
			animationImage.resize(size, size);
			images.add(animationImage);
		}
	}
	
	public void display(PVector position, DrawEngine drawEngine) {
		/* Time between each animation frame */
		int frame = (int) time.hours / 3;

		drawEngine.drawImage(PConstants.CENTER, images.get(frame % images.size()), position.x, position.y);
		
	}
}
