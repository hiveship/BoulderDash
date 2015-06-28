package nantel.java.boulder.models.exceptions;

import nantel.java.boulder.models.entities.Entity;

public class IllegalEntityPlacementException extends RuntimeException
{
	private static final long serialVersionUID = 5267139803113442320L;

	/**
	 * Constructs a new IllegalDeplacementException with default message.
	 */
	public IllegalEntityPlacementException()
	{
		super();
	}

	/**
	 * Constructs a new IllegalEntityPlacementException with specified detail
	 * message.
	 * 
	 * @param message
	 */
	public IllegalEntityPlacementException(final String message)
	{
		super(message);
	}

	/**
	 * Constructs a new IllegalEntityPlacementException for the given entity.
	 * 
	 * @param entity
	 */
	public IllegalEntityPlacementException(final Entity entity)
	{
		super("The entity of class '" + entity.getClass() + "' must be unique. Initialise it using the appropriate init method.");
	}

}
