package downup;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Location;

import downup.tasks.MusicPlayerTask;

public class DUSFX {

	public static DUSFX test = new DUSFX(new File("cakedata/music/test.txt"));
	public static DUSFX ll_call = new DUSFX(new File("cakedata/music/ll_call.txt"));
	public static DUSFX return_sender = new DUSFX(new File("cakedata/music/return_sender.txt"));
	
	public ArrayList<String> mus_file = new ArrayList<String>();
	
	public static void init() {
		test = new DUSFX(new File("cakedata/music/test.txt"));
		ll_call = new DUSFX(new File("cakedata/music/ll_call.txt"));
		return_sender = new DUSFX(new File("cakedata/music/return_sender.txt"));
	}
	
	public static void play(DUSFX s, Location loc, int del) {
		MusicPlayerTask mpt = new MusicPlayerTask(loc, s);
		mpt.runTaskTimer(DownUp.downup, 0, 0);
	}
	
	public DUSFX (File file) {
		mus_file = IOTools.readFile(file);
	}
}
