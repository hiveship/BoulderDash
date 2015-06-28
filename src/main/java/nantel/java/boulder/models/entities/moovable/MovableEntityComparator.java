package nantel.java.boulder.models.entities.moovable;

import java.util.Comparator;

import nantel.java.annotations.Nullable;

/**
 * Check if a given AbstractMoovableEntity is upper than another entity
 * considering the x coord.
 */
public final class MovableEntityComparator implements Comparator<AbstractMovableEntity>
{
	@Override
	public int compare(final @Nullable AbstractMovableEntity first, final @Nullable AbstractMovableEntity second)
	{
		assert first != null && second != null;
		return first.getX().compareTo(second.getX());
	}

}
