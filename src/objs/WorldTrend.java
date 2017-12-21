package objs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

import game.DrawEngine;
import game.GameTime;
import objs.factories.ProjectCategory;
import objs.workers.Skill;
import processing.core.PApplet;

public class WorldTrend {

	public static final int MAX_HISTORY = 30;
	
	public HashMap<ProjectCategory, Float> categoryTrends;
	public HashMap<ProjectCategory, Queue<Float>> categoryTrendHistory;
	public HashMap<Skill, Float> skillTrends;
	
	public WorldTrend() {
		this.categoryTrends = new HashMap<>();
		this.categoryTrendHistory = new HashMap<>();
		this.skillTrends = new HashMap<>();
		
		initCategoryTrends();
		initHistory();
	}
	
	private void initCategoryTrends() {
		categoryTrends.put(ProjectCategory.DATA, 1f);
		categoryTrends.put(ProjectCategory.FINTECH, 1f);
		categoryTrends.put(ProjectCategory.GAME, 1f);
		categoryTrends.put(ProjectCategory.SYSTEMS, 1f);
		categoryTrends.put(ProjectCategory.WEB, 1f);
	}
	
	private void initHistory() {
		categoryTrendHistory.put(ProjectCategory.DATA, new LinkedList<>());
		categoryTrendHistory.put(ProjectCategory.FINTECH, new LinkedList<>());
		categoryTrendHistory.put(ProjectCategory.GAME, new LinkedList<>());
		categoryTrendHistory.put(ProjectCategory.SYSTEMS, new LinkedList<>());
		categoryTrendHistory.put(ProjectCategory.WEB, new LinkedList<>());
	}
	
	
	
	public void integrate(GameTime time) {
		float x = time.totalTime() / 100f;
		int y = 1;
		
		for (Entry<ProjectCategory, Float> entry : categoryTrends.entrySet()) {
			categoryTrends.put(entry.getKey(), DrawEngine.parent.noise(x,y));
			y++;
		}
		
		for (Entry<Skill, Float> entry : skillTrends.entrySet()) {
			skillTrends.put(entry.getKey(), DrawEngine.parent.noise(x,y));
			y++;
		}
		
	}
}
