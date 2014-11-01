package downup;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;

public class DUSFXUtil {

	public static void playGlobalNote() {
		
	}
	
	public static void playNote(World w, Location l, String ins, int tone, int octave, float vol) {
		float pitch = getPitch(octave, tone);
		vol = vol / 10.0f;
		switch (ins) {
		case "piano":
			w.playSound(l, Sound.NOTE_PIANO, vol, pitch);
			break;
		case "bass":
			w.playSound(l, Sound.NOTE_BASS, vol, pitch); 
			break;
		case "bass_drum":
			w.playSound(l, Sound.NOTE_BASS_DRUM, vol, pitch); 
			break;
		case "bass_guitar":
			w.playSound(l, Sound.NOTE_BASS_GUITAR, vol, pitch); 
			break;
		case "pling":
			w.playSound(l, Sound.NOTE_PLING, vol, pitch);
			break;
		case "snare_drum":
			w.playSound(l, Sound.NOTE_SNARE_DRUM, vol, pitch);
			break;
		case "sticks":
			w.playSound(l, Sound.NOTE_STICKS, vol, pitch);
			break;
		default:
			w.playSound(l, Sound.NOTE_PIANO, vol, pitch);
			break;
		}
	}

	public static float getPitch(int oct, int id) {
		switch (id) {
		case 0: return 0.5F + (0.5f * oct); //F#
		case 1: return 0.53F + (0.5f * oct);//G
		case 2: return 0.56F + (0.5f * oct);//G#
		case 3: return 0.6F + (0.5f * oct);//A
		case 4: return 0.63F + (0.5f * oct);//A#
		case 5: return 0.67F + (0.5f * oct);//B
		case 6: return 0.7F + (0.5f * oct);//C
		case 7: return 0.76F + (0.5f * oct);//C#
		case 8: return 0.8F + (0.5f * oct);//D
		case 9: return 0.84F + (0.5f * oct);//D#
		case 10: return 0.9F + (0.5f * oct);//E
		case 11: return 0.94F + (0.5f * oct);//F
		case 12: return 1.0F + (0.5f * oct);//F#
		case 13: return 1.06F + (0.5f * oct);//G
		case 14: return 1.12F + (0.5f * oct);//G#
		case 15: return 1.18F + (0.5f * oct);//A
		case 16: return 1.26F + (0.5f * oct);//A#
		case 17: return 1.34F + (0.5f * oct);//B
		case 18: return 1.42F + (0.5f * oct);//C
		case 19: return 1.5F + (0.5f * oct);//C#
		case 20: return 1.6F + (0.5f * oct);//D
		case 21: return 1.68F + (0.5f * oct);//D#
		case 22: return 1.78F + (0.5f * oct);//E
		case 23: return 1.88F + (0.5f * oct);//F
		case 24: return 2.0F + (0.5f * oct);//F#
		default: return 0.0F + (0.5f * oct);
		}
	}
}
