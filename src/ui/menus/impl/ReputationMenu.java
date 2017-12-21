package ui.menus.impl;

import java.util.HashMap;
import java.util.Map.Entry;

import app.CurryTeXStudios;
import game.DrawEngine;
import objs.Studio;
import objs.workers.Skill;
import processing.core.PConstants;
import ui.menus.Menu;

/**
 * Menu to show reputation details.
 * Total reputation and skill reputation are calculated slightly differently and don't add up.
 *
 */
public class ReputationMenu extends Menu {

	public HashMap<Skill, Integer> reputations;
	
	public ReputationMenu(Studio studio) {
		super(CurryTeXStudios.SCREEN_X/2, CurryTeXStudios.SCREEN_Y/2, 200, 400);
		
		this.reputations = studio.reputation;
	}
	
	@Override
	public void display(DrawEngine drawEngine) {
		super.display(drawEngine);
		
		drawEngine.drawText(18, "Company reputation analytics", position.x, position.y - height + 75, DrawEngine.BLACK);
		drawReputations(drawEngine);
	}
	
	private void drawReputations(DrawEngine drawEngine) {
		int xPos = (int) (position.x - width + 100);
		int yPos = (int) (position.y - height + 150);
		
		/* Draw each skill and its reputation */
		for (Entry<Skill, Integer> entry : reputations.entrySet()) {
			drawEngine.drawImage(PConstants.CENTER, entry.getKey().icon(drawEngine), xPos, yPos);
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, entry.getKey().toString(), 
					xPos + 50, yPos, DrawEngine.BLACK);
			drawEngine.drawText(PConstants.LEFT, PConstants.CENTER, 16, entry.getValue().toString(),
					xPos + 150, yPos, DrawEngine.BLACK);
			
			yPos += 75;
		}
	}

}
