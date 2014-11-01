package downup;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoardHandler {
	
	ScoreboardManager sbMan;
	Scoreboard scoreboard;
	
	//Objectives
	Objective totalPoints;
	
	public ScoreBoardHandler(DownUp downUp) {
		sbMan = Bukkit.getScoreboardManager();
		scoreboard = sbMan.getNewScoreboard();
		
		totalPoints = scoreboard.registerNewObjective("points", "dummy");
		totalPoints.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		totalPoints.setDisplayName("Points:");
	}
	
	public void setScore(Player player, String s, int score) {
		getObjective(s).getScore(player).setScore(score);
	}
	
	public void setScore(Player player, Objective obj, int score) {
		Score sc = obj.getScore(player);
		sc.setScore(score);
	}
	
	public int getScore(Player player, Objective obj) {
		return obj.getScore(player).getScore();
	}
	
	public int getScore(Player player, String s) {
		return getObjective(s).getScore(player).getScore();
	}
	
	public Objective getObjective(String s) {
		return scoreboard.getObjective(s);
	}
}
