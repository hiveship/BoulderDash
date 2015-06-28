package nantel.java.boulder.controllers;

import java.awt.image.BufferedImage;
import java.util.Observer;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.Direction;
import nantel.java.boulder.models.Level;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.exceptions.IllegalDeplacementException;

/**
 * Controller used to manage the game part of the application.
 */
public class GameController
{
	private Level model;

	public GameController(final Level model)
	{
		this.model = model;
	}

	/**
	 * @return the number of columns in the current level play field
	 */
	public int getColumns()
	{
		return model.getPlayField().getColumnsCount();
	}

	/**
	 * @return the number of rows in the current level play field
	 */
	public int getRows()
	{
		return model.getPlayField().getRowsCount();
	}

	@Nullable
	/**
	 * Get the sprite element for the given coords.
	 * @param x
	 * @param y
	 * @return an individual sprite element
	 */
	public BufferedImage getImageAt(final int x, final int y)
	{
		// TODO Verifier les bornes
		return model.getPlayField().getContentAt(x, y).getSprite();
	}

	/**
	 * Moove rockford in the current playfield if the game is not paused.
	 * 
	 * @param direction
	 *            the direction you want to moove Rockford
	 * @throws IllegalDeplacementException
	 *             if the deplacement is not allowed (walls...)
	 */
	public void mooveRockford(final Direction direction) throws IllegalDeplacementException
	{
		if ( !model.inPause() ) {
			model.getPlayField().moveRockford(direction); // may throw
		}
	}

	/**
	 * @return the default size for one sprite element.
	 */
	public static int getSpriteSize()
	{
		return Entity.DEFAULT_SPRITE_SIZE;
	}

	/**
	 * Start the game loop for the current level.
	 */
	public void startGame()
	{
		try {
			model.start();
		} catch ( InterruptedException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Register a view as a model observer
	 * 
	 * @param observer
	 *            the view that will become observer
	 */
	public void registerObserver(final Observer observer)
	{
		model.addObserver(observer);
	}

	/**
	 * @return the number of diamonds that Rocford has already collected
	 */
	public int getDiamondsCollectedCount()
	{
		return model.getPlayField().getRockford().getDiamondsCount();
	}

	/**
	 * @return the number of diamonds you need to collect to be allowed to exit
	 *         the game. If you have collected more diamonds than the 'diamonds
	 *         to win' count, then returns 0.
	 */
	public int getDiamondsToWin()
	{
		int count = model.getDiamondsToWin() - getDiamondsCollectedCount();
		return count >= 0 ? count : 0;
	}

	public boolean isFinish()
	{
		return model.isFinish();
	}

	public boolean isWin()
	{
		// Win if level is finished and rockford alive
		return model.isFinish() && model.getPlayField().getRockford().isAlive();
	}

	/**
	 * If the game was paused, then resume it. Else, pause it.
	 */
	public void pauseOrResume()
	{
		if ( model.inPause() ) {
			model.setPause(false);
		} else {
			model.setPause(true);
		}
	}

}
