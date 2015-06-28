package nantel.java.boulder.models.entities.moovable;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.Direction;
import nantel.java.boulder.models.entities.Entity;

public abstract class AbstractMovableEntity implements Entity
{
	private static final long serialVersionUID = -4359632056257471398L;
	// Integers and not 'int' because at the initialisation, we don't know any coordinates.
	@Nullable
	private Integer x;
	@Nullable
	private Integer y;
	/**
	 * If an entity is falling, it has inertie.
	 */
	private boolean inertie;

	// ============
	// CONSTRUCTORS
	// ============

	public AbstractMovableEntity(@Nullable final Integer x, @Nullable final Integer y)
	{
		this.x = x;
		this.y = y;
		this.inertie = false;
	}

	public AbstractMovableEntity()
	{
		this(null, null);
	}

	// =================
	// GETTERS / SETTERS
	// =================

	public void setY(final int y)
	{
		this.y = y;
	}

	public void setCoordonnees(final int x, final int y)
	{
		this.setX(x);
		this.setY(y);
	}

	@SuppressWarnings("null")
	public Integer getX()
	{
		//assert x != null;
		return x;
	}

	public void setX(final int x)
	{
		this.x = x;
	}

	@SuppressWarnings("null")
	public Integer getY()
	{
		//assert y != null;
		return y;
	}

	public boolean haveInertie()
	{
		return inertie;
	}

	public void setInertie(final boolean inertie)
	{
		this.inertie = inertie;
	}

	// ==============
	// ENTITY METHODS
	// ==============

	@Override
	public boolean isMovable()
	{
		return true;
	}

	public void move(final Direction direction)
	{
		switch ( direction ) {
			case DOWN :
				setX(getX() + 1);
				break;
			case LEFT :
				setY(getY() - 1);
				break;
			case RIGHT :
				setY(getY() + 1);
				break;
			case UP :
				setX(getX() - 1);
				break;
			default :
				break;
		}
	}

}
