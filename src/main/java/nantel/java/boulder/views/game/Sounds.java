package nantel.java.boulder.views.game;

/**
 * Sounds available during the game. The files must be 16 bits PCM encoded.
 */
public enum Sounds
{
	FINISH_WIN("finish_win.wav"),
	FINISH_LOOSE("finish_loose.wav"),
	COLLECT_DIAMOND("collect_diamond.wav"),
	START_GAME("new_game.wav");

	private String soundName;

	Sounds(final String soundName)
	{
		this.soundName = soundName;
	}

	public String getSoundName()
	{
		return soundName;
	}

	public void setSoundName(final String soundName)
	{
		this.soundName = soundName;
	}
}
