package nantel.java.boulder.models;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.utilities.DefaultLogger;
import nantel.java.utilities.Logger;

import java.io.Serializable;
import java.util.Observable;

/**
 * Main class of the model. A level is composed of a play field and some other
 * information (such as diamonds to win the level, a state (paused or not) and a
 * name.
 */
public class Level extends Observable implements Serializable
{
	private static final long serialVersionUID = 3704335212651627910L;
	private static final Logger LOGGER = new DefaultLogger(Level.class);
	private final PlayField playField;
	private final int diamondsToWin;
	private boolean pause;
	@Nullable
	private String name;

	//============
	// CONSTRUCTOR
	//============

	public Level(final int rows, final int columns, final int diamondsToWin)
	{
		this.playField = new PlayField(rows, columns);
		this.diamondsToWin = diamondsToWin;
	}

	//==================
	// GETTERS / SETTERS
	//==================

	/**
	 * @return the play field of the current level.
	 */
	public PlayField getPlayField()
	{
		return playField;
	}

	/**
	 * @return the number of diamonds that Rockford will have to collect to be
	 *         allowed to win the current level.
	 */
	public int getDiamondsToWin()
	{
		return diamondsToWin;
	}

	//==========
	// GAME LOOP
	//==========

	/**
	 * Game loop. Refresh the game elements and animate the sprites.
	 * 
	 * @throws InterruptedException
	 */
	public void start() throws InterruptedException
	{
		boolean updateGame = true;
		while ( !isFinish() ) {
			if ( !inPause() ) {
				// We update the elements only 1 round of 2
				if ( updateGame ) {
					updateExitState(); // Check if Rockford has collected enought diamonds to exit the level.
					getPlayField().updateMovableEntities();
					updateGame = false;
				} else {
					updateGame = true;
				}
			}
			Thread.sleep(175); //TODO: Make this value configurable (game speed)
			// Animate the sprite at every round (sprites still need to be animated is the game is in pause.)
			setChanged();
			notifyObservers();
		}
		LOGGER.debug("The game loop for the current level is now finished.");
	}

	public boolean inPause()
	{
		return this.pause;
	}

	/**
	 * The level is finished if Rockford is dead or if he had collected enought
	 * diamonds and has reach the exit door.
	 * 
	 * @return true if the game is finished, false otherwise.
	 */
	public boolean isFinish()
	{
		return !getPlayField().getRockford().isAlive() || getPlayField().getExit().isReached();
	}

	public void updateExitState()
	{
		if ( getPlayField().getRockford().getDiamondsCount() == getDiamondsToWin() ) {
			getPlayField().getExit().setAllowed(true);
		}
	}

	/**
	 * If name is null then a unique name will be used.
	 * 
	 * @return the name of the current level. The name is used to save and load
	 *         a level.
	 */
	@Nullable
	public String getName()
	{
		return name;
	}

	public void setName(final @Nullable String name)
	{
		this.name = name;
	}

	public void setPause(final boolean pause)
	{
		this.pause = pause;
	}

	/**
	 * Add an element to the play field and notify the observers that the
	 * playfield has changed.
	 * 
	 * @param x
	 * @param y
	 * @param entity
	 *            you want to add
	 */
	public void addElement(final int x, final int y, final Entity entity)
	{
		getPlayField().addEntity(x, y, entity);
		setChanged();
		notifyObservers();
	}

	/**
	 * Set Rockford's position in the playfield and notify the observers that
	 * the playfield has changed.
	 * 
	 * @param x
	 * @param y
	 */
	public void initRockfordPosition(final int x, final int y)
	{
		getPlayField().initRockfordPosition(x, y);
		setChanged();
		notifyObservers();
	}

	/**
	 * Set Exit position in the playfield and notify the observers that the
	 * playfield has changed.
	 * 
	 * @param x
	 * @param y
	 */
	public void initExitPosition(final int x, final int y)
	{
		getPlayField().initExitPosition(x, y);
		setChanged();
		notifyObservers();
	}
}
