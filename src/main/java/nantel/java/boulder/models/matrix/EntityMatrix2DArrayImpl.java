package nantel.java.boulder.models.matrix;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.exceptions.IllegalEntityPlacementException;
import nantel.java.utilities.DefaultLogger;
import nantel.java.utilities.DefaultLoggerWithOutNewLine;
import nantel.java.utilities.Logger;

import java.io.Serializable;
import java.util.Arrays;

/**
 * An implementation of the EntityMatrix class using a java 2D array to store
 * data.
 */
final public class EntityMatrix2DArrayImpl implements EntityMatrix, Serializable
{
	private static final long serialVersionUID = 1620562427653382683L;

	private static final Logger MATRIX_LOGGER = new DefaultLoggerWithOutNewLine();
	private static final Logger LOGGER = new DefaultLogger(EntityMatrix2DArrayImpl.class);

	private final int rows; // x coord
	private final int columns; // y coord
	private Entity[][] data;

	public EntityMatrix2DArrayImpl(final int rows, final int columns)
	{
		this.rows = rows;
		this.columns = columns;
		this.data = new Entity[rows][columns];
	}

	@Override
	public void display()
	{
		for ( int i = 0; i < rows; i++ ) {
			for ( int j = 0; j < columns; j++ ) {
				MATRIX_LOGGER.debug(this.data[i][j].getName() + " ");
			}
			MATRIX_LOGGER.debug("\n");
		}
		MATRIX_LOGGER.debug("\n");
	}

	@Override
	public void setContentAt(final int x, final int y, final Entity entity)
	{
		checkCoords(x, y);
		assert entity != null;
		this.data[x][y] = entity;
	}

	@Override
	public Entity getContentAt(final int x, final int y)
	{
		checkCoords(x, y);
		Entity checkedContent = this.data[x][y];
		assert checkedContent != null;
		return checkedContent;
	}

	@Override
	public int getRowsCount()
	{
		return this.rows;
	}

	@Override
	public int getColumnsCount()
	{
		return this.columns;
	}

	private void checkCoords(final int x, final int y)
	{
		if ( x > getRowsCount() || x < 0 || y < 0 || y > getColumnsCount() ) {
			throw new IllegalEntityPlacementException("Invalid coordinates for x = " + x + " and y = " + y);
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + this.columns;
		result = prime * result + Arrays.hashCode(this.data);
		result = prime * result + this.rows;
		return result;
	}

	@Override
	public boolean equals(final @Nullable Object object)
	{
		if ( object == null || object.getClass() != this.getClass() ) {
			LOGGER.debug("Erreur -> les deux objets passés à la méthode equals ne sont pas de la même classe.");
			return false;
		}

		EntityMatrix other = (EntityMatrix) object;

		// Compare sizes
		if ( this.getColumnsCount() != other.getColumnsCount() || this.getRowsCount() != other.getRowsCount() ) {
			LOGGER.debug("Erreur -> les deux matrices n'ont pas la même taille.");
			return false;
		}

		// Compare types
		for ( int i = 0; i < rows; i++ ) {
			for ( int j = 0; j < columns; j++ ) {
				if ( this.getContentAt(i, j).getClass() != other.getContentAt(i, j).getClass() ) {
					LOGGER.debug("Erreur -> au moins une case des deux matrices ne contient pas le même type d'objet.");
					return false;
				}
			}
		}
		return true;
	}
}
