package nantel.java.boulder.controllers;

import java.awt.Image;
import java.io.IOException;
import java.util.Observer;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.Level;
import nantel.java.boulder.models.LevelRepository;
import nantel.java.boulder.models.entities.Dirt;
import nantel.java.boulder.models.entities.EmptySpace;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.entities.moovable.Amoeba;
import nantel.java.boulder.models.entities.moovable.Boulder;
import nantel.java.boulder.models.entities.moovable.Butterfly;
import nantel.java.boulder.models.entities.moovable.Diamond;
import nantel.java.boulder.models.entities.moovable.Firefly;
import nantel.java.boulder.models.entities.walls.BrickWall;
import nantel.java.boulder.models.entities.walls.ExpandingWall;
import nantel.java.boulder.models.entities.walls.MagicWall;
import nantel.java.boulder.models.entities.walls.SteelWall;
import nantel.java.boulder.models.exceptions.IllegalEntityPlacementException;
import nantel.java.boulder.models.exceptions.NotImplementedException;
import nantel.java.boulder.views.editor.GameElement;

/**
 * Controller used to manage the game part of the application.
 */
public class LevelEditorController
{
	private final Level model;

	/**
	 * Start the level editor for a new level.
	 * 
	 * @param rows
	 *            of the new level
	 * @param columns
	 *            of the new level
	 * @param diamondsToWin
	 *            of the new level
	 */
	public LevelEditorController(final int rows, final int columns, final int diamondsToWin)
	{
		this.model = new Level(rows, columns, diamondsToWin);
	}

	/**
	 * Start the level editor for an existing level.
	 * 
	 * @param existingLevel
	 */
	public LevelEditorController(final Level existingLevel)
	{
		this.model = existingLevel;
	}

	public Level getModel()
	{
		return this.model;
	}

	/**
	 * Save the current level
	 * 
	 * @param levelName
	 *            name of level, can be null.
	 * @throws IOException
	 *             if an error occurs while saving the level
	 */
	public void save(final @Nullable String levelName) throws IOException
	{
		getModel().setName(levelName);
		LevelRepository.save(getModel());
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
	public Image getImageAt(final int y, final int x)
	{
		// TODO vérifier les bornes
		return getModel().getPlayField().getSpriteAt(y, x);
	}

	/**
	 * Add an element in the playfield.
	 * 
	 * @param x
	 *            row index
	 * @param y
	 *            column index
	 * @param element
	 *            game element you want to add
	 * @throws NotImplementedException
	 *             if you try to add an element that have not been implemented
	 *             yet
	 */
	public void setContentAt(final int x, final int y, final @Nullable GameElement element) throws NotImplementedException
	{
		// TODO vérifier les bornes x et y
		try {
			if ( element != null ) {
				switch ( element ) {
					case AMOEBA :
						getModel().addElement(x, y, new Amoeba());
						break;
					case BOULDER :
						getModel().addElement(x, y, new Boulder());
						break;
					case BRICK_WALL :
						getModel().addElement(x, y, new BrickWall());
						break;
					case BUTTERFLY :
						getModel().addElement(x, y, new Butterfly());
						break;
					case DIAMOND :
						getModel().addElement(x, y, new Diamond());
						break;
					case DIRT :
						getModel().addElement(x, y, new Dirt());
						break;
					case EMPTY_SPACE :
						getModel().addElement(x, y, new EmptySpace());
						break;
					case EXIT :
						model.initExitPosition(x, y);
						break;
					case EXPANDING_WALL :
						getModel().addElement(x, y, new ExpandingWall());
						break;
					case FIREFLY :
						getModel().addElement(x, y, new Firefly());
						break;
					case MAGIC_WALL :
						getModel().addElement(x, y, new MagicWall());
						break;
					case ROCKFORD :
						getModel().initRockfordPosition(x, y);
						break;
					case STEEL_WALL :
						getModel().addElement(x, y, new SteelWall());
						break;
					default :
						break;
				}
			}
		} catch ( IllegalEntityPlacementException e ) {
			// Just ignore the error and don't place anything into the playfield.
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
		getModel().addObserver(observer);
	}

	/**
	 * @return the default size for one sprite element.
	 */
	public static int getSprizeUnitSize()
	{
		return Entity.DEFAULT_SPRITE_SIZE;
	}

}
