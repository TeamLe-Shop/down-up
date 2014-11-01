package downup.tasks;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import downup.DUSFX;
import downup.DUSFXUtil;

public class MusicPlayerTask extends BukkitRunnable {

	int channel_count;
	
	ArrayList<Channel> channels = new ArrayList<Channel>();
	ArrayList<String> data = new ArrayList<String>();
	Location loc;
	
	public MusicPlayerTask(Location loc, DUSFX sfx) {
		this.loc = loc;
		channel_count = 0;
		Channel curr_channel = null;
		int line = 0;
		for (String s : sfx.mus_file) {
			s = s.trim();
			if (s.startsWith("::")) { // Channel Definer
				channel_count++;
				curr_channel = new Channel();
				channels.add(curr_channel);
				String[] sargs = s.substring(2).split(" ");
				curr_channel.id = Byte.parseByte(sargs[0]);
				curr_channel.material = sargs[1];
			} else if (s.startsWith(">>")) { // Start Music!
				curr_channel.start_line = line;
				curr_channel.curr_line = line;
			}
			line++;
			data.add(s);
		}
		
	}
	
	public void run() {
		boolean all_ended = false;
		int ended_c = 0;
		for (int e = 0; e < channels.size(); e++) {
			Channel c = channels.get(e);
			if (c.has_ended) {
				channels.remove(c);
				ended_c++;
				continue;
			}
			if (c.rest_tick > 0) {
				c.rest_tick--;
				continue;
			}
			String s = data.get(c.curr_line).substring(c.ln_pos);
			System.out.println(s);
			if (data.get(c.curr_line).startsWith("*")) { // Commands
				String[] sargs = s.substring(1).split(";");
				switch (sargs[0]) {
				case "rl":
					c.rest_length = Integer.parseInt(sargs[1]);
					break;
				case "v":
					c.volume = Integer.parseInt(sargs[1]);
					break;
				case "o":
					c.octave = Integer.parseInt(sargs[1]);
					break;
				case "uo":
					c.octave++;
					break;
				case "do":
					c.octave--;
					break;
				}
				c.curr_line++;
			} else if (s.equals("}")) {
				c.has_ended = true;
				continue;
 			} else {
				for (int i = 0; i < s.length(); i++) {
					char ch = s.charAt(i);
					if (i == s.length()-1) {
						c.curr_line++;
						c.ln_pos = 0;
					}
					
					if (ch == ' ') {
						c.ln_pos++;
						c.rest_tick = c.rest_length;
						break;
					} else {
						switch (ch) {
						case 'a':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 3, c.octave, c.volume);
							break;
						case 'A':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 4, c.octave, c.volume);
							break;
						case 'b':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 5, c.octave, c.volume);
							break;
						case 'c':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 6, c.octave, c.volume);
							break;
						case 'C':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 7, c.octave, c.volume);
							break;
						case 'd':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 8, c.octave, c.volume);
							break;
						case 'D':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 9, c.octave, c.volume);
							break;
						case 'e':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 10, c.octave, c.volume);
							break;
						case 'f':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 11, c.octave, c.volume);
							break;
						case 'F':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 12, c.octave, c.volume);
							break;
						case 'g':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 13, c.octave, c.volume);
							break;
						case 'G':
							DUSFXUtil.playNote(loc.getWorld(), loc, c.material, 14, c.octave, c.volume);
							break;
						}
					}
				}
			}
		}
		
		if (ended_c == channel_count) {
			all_ended = true;
			this.cancel();
		}
	}
	
	private class Channel {
		int rest_length = 0;
		int rest_tick = 0;
		int volume = 1;
		int octave = 0;
		byte id = -1;
		int ln_pos = 0;
		int start_line = 0;
		int curr_line = start_line;
		boolean has_ended = false;
		String material = "piano";
		
	}
}
