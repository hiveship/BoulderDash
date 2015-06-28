package nantel.java.boulder.models;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.entities.Dirt;
import nantel.java.boulder.models.entities.EmptySpace;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.entities.moovable.AbstractMovableEntity;
import nantel.java.boulder.models.entities.moovable.Boulder;
import nantel.java.boulder.models.entities.moovable.Diamond;
import nantel.java.boulder.models.entities.moovable.Exit;
import nantel.java.boulder.models.entities.moovable.MovableEntityComparator;
import nantel.java.boulder.models.entities.moovable.Rockford;
import nantel.java.boulder.models.entities.walls.SteelWall;
import nantel.java.boulder.models.exceptions.ExitNotAllowedException;
import nantel.java.boulder.models.exceptions.IllegalDeplacementException;
import nantel.java.boulder.models.exceptions.IllegalEntityPlacementException;
import nantel.java.boulder.models.matrix.EntityMatrix;
import nantel.java.boulder.models.matrix.EntityMatrix2DArrayImpl;
import nantel.java.utilities.DefaultLogger;
import nantel.java.utilities.Logger;

public class PlayField implements Serializable
{
	private static final long serialVersionUID = 4034944613388319432L;

	private static final Logger LOGGER = new DefaultLogger();
	private final EntityMatrix matrix;

	private final Rockford rockford;
	private final Exit exit;

	public PlayField(final int rows, final int columns)
	{
		this.matrix = new EntityMatrix2DArrayImpl(rows, columns);
		this.rockford = new Rockford();
		this.exit = new Exit();
		init();
	}

	//==================
	// GETTERS / SETTERS
	//==================

	/**
	 * Get the matrix used to store the elements in the playfield
	 * */
	private EntityMatrix getMatrix()
	{
		return this.matrix;
	}

	public static Logger getLOGGER()
	{
		return LOGGER;
	}

	//========================
	// PLAYING FIELD UTILITIES
	//========================

	/**
	 * Initialise the playfield with default elements. Fill the playfield with
	 * dirt and put Steel Walls on each extremities.
	 */
	private void init()
	{
		final int rowsCount = getMatrix().getRowsCount();
		final int columnsCount = getMatrix().getColumnsCount();

		for ( int i = 0; i < rowsCount; i++ ) {
			for ( int j = 0; j < columnsCount; j++ ) {
				// Initialise le terrain avec uniquement de la terre + des murs indestructibles sur les contours.
				if ( i == 0 || i == rowsCount - 1 || j == 0 || j == columnsCount - 1 ) {
					getMatrix().setContentAt(i, j, new SteelWall());
				} else {
					getMatrix().setContentAt(i, j, new Dirt());
				}
			}
		}
		LOGGER.debug("La matrice de taille " + rowsCount + "x" + columnsCount + " a bien été initialisée avec de la terre et des StellWall sur les contours.");
	}

	// FIXME debug/development only.
	public void display()
	{
		getMatrix().display();
	}

	/**
	 * @return the number of rows in the current playfield
	 */
	public int getRowsCount()
	{
		return getMatrix().getRowsCount();
	}

	/**
	 * @return the number of columns in the current playfield
	 */
	public int getColumnsCount()
	{
		return getMatrix().getColumnsCount();
	}

	//======================
	// NEIGHTBORING ENTITIES
	//======================

	private Entity getEntityBelow(final int x, final int y)
	{
		return getMatrix().getContentAt(x + 1, y);
	}

	private Entity getEntityAbove(final int x, final int y)
	{
		return getMatrix().getContentAt(x - 1, y);
	}

	private Entity getEntityOnRight(final int x, final int y)
	{
		return getMatrix().getContentAt(x, y + 1);
	}

	private Entity getEntityOnLeft(final int x, final int y)
	{
		return getMatrix().getContentAt(x, y - 1);
	}

	private Entity getEntityBelowRight(final int x, final int y)
	{
		return getMatrix().getContentAt(x + 1, y + 1);
	}

	private Entity getEntityBelowLeft(final int x, final int y)
	{
		return getMatrix().getContentAt(x + 1, y - 1);
	}

	private Entity getEntityAboveRight(final int x, final int y)
	{
		return getMatrix().getContentAt(x - 1, y + 1);
	}

	private Entity getEntityAboveLeft(final int x, final int y)
	{
		return getMatrix().getContentAt(x - 1, y - 1);
	}

	//================
	// MANAGE ENTITIES
	//================

	/**
	 * Get the individual sprite element at the specified coords.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public BufferedImage getSpriteAt(final int x, final int y)
	{
		return getMatrix().getContentAt(x, y).getSprite();
	}

	/**
	 * Get the entity present at the specified coords.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Entity getContentAt(final int x, final int y)
	{
		return getMatrix().getContentAt(x, y);
	}

	/**
	 * @return the list of all diamonds and boulders present in the playfield.
	 */
	private List<AbstractMovableEntity> getDiamondsAndBoulders()
	{
		List<AbstractMovableEntity> list = new ArrayList<>();
		for ( int i = 0; i < getRowsCount(); i++ ) {
			for ( int j = 0; j < getColumnsCount(); j++ ) {
				if ( getContentAt(i, j).isMovable() ) {
					AbstractMovableEntity entity = (AbstractMovableEntity) getContentAt(i, j);
					if ( entity instanceof Boulder || entity instanceof Diamond ) {
						list.add((AbstractMovableEntity) getContentAt(i, j));
					}
				}
			}
		}
		return list;
	}

	/**
	 * Add an entity to the playfield. If the entity is movable then save it's
	 * coords.
	 * 
	 * @param x
	 * @param y
	 * @param entity
	 */
	public void addEntity(final int x, final int y, final Entity entity)
	{
		if ( entity.isUnique() ) { // Contournement du pattern singleton
			throw new IllegalEntityPlacementException(entity);
		} else {
			if ( entity.isMovable() ) {
				AbstractMovableEntity moovableEntity = (AbstractMovableEntity) entity;
				moovableEntity.setCoordonnees(x, y); // Save the coords
				getMatrix().setContentAt(x, y, moovableEntity);
				LOGGER.debug("Une entité de type '" + moovableEntity.getClass() + "' viens d'être ajoutée au terrain. Ses coordonnées ont été mémorisée (x=" + x + " : y=" + y);
			} else {
				getMatrix().setContentAt(x, y, entity);
				LOGGER.debug("Une entité de type '" + entity.getClass() + "' viens d'être ajoutée au terrain.");
			}
		}
	}

	@SuppressWarnings("null")
	/**
	 * Init the position of Rockford in the play field.
	 * @param rowIndex
	 * @param columnIndex
	 */
	public void initRockfordPosition(final int rowIndex, final int columnIndex)
	{
		if ( getRockford().getX() != null || getRockford().getY() != null ) {
			// If rockford was already existing, then move it to the neww coords
			getMatrix().setContentAt(getRockford().getX(), getRockford().getY(), new Dirt());
		}
		this.rockford.setCoordonnees(rowIndex, columnIndex);
		getMatrix().setContentAt(rowIndex, columnIndex, rockford);
		LOGGER.debug("Rockford a été placé sur le terrain.");
	}

	@SuppressWarnings("null")
	/**
	 * Init the position of the Exit door in the play field.
	 * @param rowIndex
	 * @param columnIndex
	 */
	public void initExitPosition(final int rowIndex, final int columnIndex)
	{
		if ( getExit().getX() != null || getExit().getY() != null ) {
			// If the exit was already existing, then move it to the neww coords
			getMatrix().setContentAt(getExit().getX(), getExit().getY(), new Dirt());
		}
		this.exit.setCoordonnees(rowIndex, columnIndex);
		getMatrix().setContentAt(rowIndex, columnIndex, exit);
		LOGGER.debug("La sortie a été placée sur le terrain.");
	}

	//====================
	// ROCKFORD MANAGEMENT
	//====================

	/**
	 * @return the unique Rockford instance of the playfield
	 */
	public Rockford getRockford()
	{
		return this.rockford;
	}

	/**
	 * @return the unique Exit door instance of the playfield
	 */
	public Exit getExit()
	{
		return this.exit;
	}

	/**
	 * Try for move Rockford in the specified direction
	 * 
	 * @param direction
	 * @throws IllegalDeplacementException
	 *             if Rockford can not move in the given direction
	 * @throws ExitNotAllowedException
	 *             if Rockford has reached the Exit door but is not allowed to
	 *             finish the level.
	 */
	public void moveRockford(final Direction direction) throws IllegalDeplacementException, ExitNotAllowedException
	{
		switch ( direction ) {
			case DOWN :
				move(getEntityBelow(rockford.getX(), rockford.getY()), Direction.DOWN);
				break;
			case LEFT :
				move(getEntityOnLeft(rockford.getX(), rockford.getY()), Direction.LEFT);
				break;
			case RIGHT :
				move(getEntityOnRight(rockford.getX(), rockford.getY()), Direction.RIGHT);
				break;
			case UP :
				move(getEntityAbove(rockford.getX(), rockford.getY()), Direction.UP);
				break;
			default :
				break;
		}
	}

	//FIXME refactor
	private void move(final Entity neighboringEntity, final Direction direction) throws IllegalDeplacementException, ExitNotAllowedException
	{
		if ( neighboringEntity.isConsomable() ) {
			if ( neighboringEntity instanceof Diamond ) {
				rockford.collectDiamond();
			}
			deplace(rockford, direction);
			LOGGER.debug("Rockford a bougé d'une case dans la direction -> " + direction.name().toLowerCase());
		} else if ( neighboringEntity instanceof Boulder && (direction == Direction.RIGHT || direction == Direction.LEFT) ) { // Can only push boulder and only on the sides.
			if ( !pushBoulder((Boulder) neighboringEntity, direction) ) {
				throw new IllegalDeplacementException(neighboringEntity);
			}
		} else if ( neighboringEntity.equals(getExit()) ) {
			LOGGER.debug("Rockford a bougé d'une case dans la direction -> " + direction.name().toLowerCase());
			if ( exit.isAllowed() ) {
				deplace(rockford, direction);

				exit.setReached(true); // will end the game
			} else {
				throw new ExitNotAllowedException();
			}
		} else {
			LOGGER.debug("Rockford ne peut pas se déplacer d'une case dans la direction -> " + direction.name());
			throw new IllegalDeplacementException(neighboringEntity);
		}
	}

	/**
	 * Check if Rockford can push the boulder in the given direction. Rockford
	 * is only allowed to push a boulder horizontally of there is nothing behind
	 * the boulder.
	 * 
	 * @param boulder
	 * @param direction
	 * @return true if the boulder has been pushed, false otherwise
	 */
	// FIXME: this method should be refactored
	private boolean pushBoulder(final Boulder boulder, final Direction direction)
	{
		if ( direction == Direction.LEFT && getEntityOnLeft(boulder.getX(), boulder.getY()) instanceof EmptySpace ) {
			deplace(boulder, direction);
			deplace(rockford, direction);
			LOGGER.debug("A boulder has been pushed to the left.");
			return true;
		} else if ( direction == Direction.RIGHT && getEntityOnRight(boulder.getX(), boulder.getY()) instanceof EmptySpace ) { // Right
			deplace(boulder, direction);
			deplace(rockford, direction);
			LOGGER.debug("A boulder has been pushed to the right.");
			return true;
		}
		return false;
	}

	//=============
	// GAME UPDATES
	//=============

	/**
	 * For each movable entity of the playfield, check if it can fall or round.
	 */
	public void updateMovableEntities()
	{
		// Insure the list is sorted before updating the entities (priority)
		List<AbstractMovableEntity> entities = getDiamondsAndBoulders();
		entities.sort(new MovableEntityComparator());

		List<AbstractMovableEntity> moovableEntitiesCopy = new ArrayList<>(entities);
		for ( AbstractMovableEntity entity : moovableEntitiesCopy ) {
			assert entity != null;
			fall(entity);
		}
	}

	// FIXME: this method must be refactored to avoid the use of 'instaceof' and improve readability
	private void fall(final AbstractMovableEntity entity)
	{
		if ( getEntityBelow(entity.getX(), entity.getY()) instanceof EmptySpace ) {
			LOGGER.debug("Il y a du vide en dessous d'une entitée, je peux tomber...");
			deplace(entity, Direction.DOWN);
			entity.setInertie(true);
		} else if ( getEntityBelow(entity.getX(), entity.getY()).isRounded() ) {
			if ( getEntityOnRight(entity.getX(), entity.getY()) instanceof EmptySpace && getEntityBelowRight(entity.getX(), entity.getY()) instanceof EmptySpace ) {
				LOGGER.debug("En dessous ce n'est pas vide, mais à droite et en bas à droite oui, je peux rouler !");
				deplace(entity, Direction.RIGHT);
				deplace(entity, Direction.DOWN);
				entity.setInertie(true);
			} else if ( getEntityOnLeft(entity.getX(), entity.getY()) instanceof EmptySpace && getEntityBelowLeft(entity.getX(), entity.getY()) instanceof EmptySpace ) {
				LOGGER.debug("En dessous ce n'est pas vide, ce n'est pas vide à droite ou en bas à droite oui, mais c'est vide à gauche et en bas à gauche: je peux rouler !");
				deplace(entity, Direction.LEFT);
				deplace(entity, Direction.DOWN);
				entity.setInertie(true);
			}
		} else if ( getEntityBelow(entity.getX(), entity.getY()).isExplodable() && entity.haveInertie() ) {
			if ( getEntityBelow(entity.getX(), entity.getY()).equals(getRockford()) ) {
				getRockford().die();
			}
			deplace(entity, Direction.DOWN);
			explose(entity);
		} else {
			entity.setInertie(false);
		}
	}

	// FIXME: this method must be refactored to use a loop.
	/**
	 * Manage the explosion of an entity. All the height neighbours of an entity
	 * can explode. When a movable entity is deleted, we must also delete if
	 * from the movable list.
	 * 
	 * @param entity
	 *            entity that will explode
	 */
	private void explose(final AbstractMovableEntity entity)
	{
		assert entity.isExplodable();
		LOGGER.debug("An explosion will happen on the entity '" + entity.getClass() + "' !");
		// get the coords of the entity that will explode
		int x = entity.getX();
		int y = entity.getY();

		if ( getEntityAbove(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x - 1, y, new EmptySpace());
			LOGGER.debug("The entity above(" + getEntityAbove(x, y).getClass() + ") have exploded.");
		}
		if ( getEntityAboveLeft(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x - 1, y - 1, new EmptySpace());
			LOGGER.debug("The entity alove-left(" + getEntityAboveLeft(x, y).getClass() + ") have exploded.");
		}
		if ( getEntityAboveRight(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x - 1, y + 1, new EmptySpace());
			LOGGER.debug("The entity above-right(" + getEntityAboveRight(x, y).getClass() + ") have exploded.");
		}
		if ( getEntityOnLeft(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x, y - 1, new EmptySpace());
			LOGGER.debug("The entity on the left(" + getEntityOnLeft(x, y).getClass() + ") have exploded.");
		}
		if ( getEntityOnRight(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x, y + 1, new EmptySpace());
			LOGGER.debug("The entity on the right(" + getEntityOnRight(x, y).getClass() + ") have exploded.");
		}
		if ( getEntityBelow(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x + 1, y, new EmptySpace());
			LOGGER.debug("The entity below(" + getEntityBelow(x, y).getClass() + ") have exploded.");
		}
		if ( getEntityBelowLeft(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x + 1, y - 1, new EmptySpace());
			LOGGER.debug("The entity below-left(" + getEntityBelowLeft(x, y).getClass() + ") have exploded.");
		}
		if ( getEntityBelowRight(x, y).canChainExplosion() ) {
			getMatrix().setContentAt(x + 1, y + 1, new EmptySpace());
			LOGGER.debug("The entity below-right(" + getEntityBelowRight(x, y).getClass() + ") have exploded.");
		}
		getMatrix().setContentAt(x, y, new EmptySpace());
	}

	private void deplace(final AbstractMovableEntity entity, final Direction direction)
	{
		// FIXME: don't delete the Exit if Rockford reach it and don't has enough diamonds yet
		if ( entity.equals(getRockford()) ) {
			getMatrix().setContentAt(entity.getX(), entity.getY(), new EmptySpace());
			getRockford().move(direction);
			getMatrix().setContentAt(entity.getX(), entity.getY(), entity);
		} else {
			getMatrix().setContentAt(entity.getX(), entity.getY(), new EmptySpace());
			entity.move(direction);
			getMatrix().setContentAt(entity.getX(), entity.getY(), entity);
		}
	}

	//==============
	// MISCELLANEOUS
	//==============

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + this.exit.hashCode();
		result = prime * result + this.matrix.hashCode();
		result = prime * result + this.rockford.hashCode();
		return result;
	}

	/**
	 * Check if two playfield are equals (same content at the same position).
	 */
	@Override
	public boolean equals(final @Nullable Object obj)
	{
		assert obj != null;
		PlayField other = (PlayField) obj;
		return getMatrix().equals(other.getMatrix()); // Only check the content of the EntityMatrix to see if it's equals or not.
	}

}
