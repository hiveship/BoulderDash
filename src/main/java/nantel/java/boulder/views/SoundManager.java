package nantel.java.boulder.views;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import nantel.java.boulder.views.game.Sounds;

/**
 * The SoundManager is a class used to play a sound inside a JFrame.
 */
public class SoundManager
{
	@SuppressWarnings("null")
	private static final String DEFAULT_SOUNDS_PATH = SoundManager.class.getResource("/sounds/").getPath();

	public static void play(final Sounds sound)
	{
		assert DEFAULT_SOUNDS_PATH != null;
		File file = new File(DEFAULT_SOUNDS_PATH + sound.getSoundName());
		try ( AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file); ) {
			@SuppressWarnings("resource")
			// Closed in the finally bloc. Using a try-with-resource bloc avoid to sound to be playing (close method is called before playing the sound)
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			// FIXME Memory leak, find how to close the clip object only when the sound is finished.
		} catch ( Exception e ) {
			// Playing sound is not important for the game, so if an error occurs, we just ignore it.
		}
	}
}
