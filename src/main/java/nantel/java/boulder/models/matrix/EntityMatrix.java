package nantel.java.boulder.models.matrix;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.entities.Entity;

/**
 * EntityMatrix is used to store the elements of a playfield.
 */
public interface EntityMatrix
{
	// debug/development only
	void display();

	/**
	 * Store an entity for the specified coords.
	 * 
	 * @param x
	 * @param y
	 * @param entity
	 */
	void setContentAt(final int x, final int y, final Entity entity);

	/**
	 * Get the entity stored at the specified coords.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	Entity getContentAt(final int x, final int y);

	/**
	 * @return number of rows of the matrix
	 */
	int getRowsCount();

	/**
	 * @return number of columns of the matrix
	 */
	int getColumnsCount();

	@Override
	boolean equals(@Nullable Object other);

	@Override
	int hashCode();
}
