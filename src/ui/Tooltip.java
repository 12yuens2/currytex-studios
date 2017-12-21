package ui;

import java.util.ArrayList;

import app.CurryTeXStudios;
import game.DrawEngine;

import processing.core.PConstants;
import ui.menus.Menu;


/**
 * Menu to display tips and notes.
 *
 */
public class Tooltip extends Menu {

	public String tip;
	
	public Tooltip(String tip, int width, int height) {
		super(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, width, height);
		
		this.tip = tip;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		drawFormattedText(drawEngine);
	}
	
	/**
	 * Format the text to fit in the menu width.
	 * @param drawEngine
	 */
	private void drawFormattedText(DrawEngine drawEngine) {
		ArrayList<String> lines = new ArrayList<>();
		String[] words = tip.split(" ");
		int maxLength = width;
		int length = 0;
		String line = "";
		
		/* Add words to the line until the line length is greater than the width of the menu */
		for (String word : words) {
			length += 10 + word.length() * 5;
			
			if (length < maxLength) {
				line += word + " ";
			}
			else {
				lines.add(line);
				line = word + " ";
				length = 0;
			}
		}
		if (line.length() > 0) lines.add(line);
		
		int xPos = (int) (position.x - width + 50);
		int yPos = (int) (position.y - height + 75);
		for (String l : lines) {
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, l, xPos, yPos, DrawEngine.BLACK);
			yPos += 25;
		}
	}


}
