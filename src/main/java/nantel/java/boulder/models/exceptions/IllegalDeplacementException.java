package nantel.java.boulder.models.exceptions;

import nantel.java.boulder.models.entities.Entity;

public class IllegalDeplacementException extends RuntimeException
{
	private static final long serialVersionUID = 5267139803113442320L;

	/**
	 * Constructs a new IllegalDeplacementException with default message.
	 */
	public IllegalDeplacementException()
	{
		super();
	}

	/**
	 * Constructs a new IllegalDeplacementException with specified detail
	 * message.
	 * 
	 * @param message
	 */
	public IllegalDeplacementException(final String message)
	{
		super(message);
	}

	/**
	 * Constructs a new IllegalDeplacementException with specified detail
	 * message and nested Throwable.
	 * 
	 * @param message
	 * @param cause
	 */
	public IllegalDeplacementException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Constructs a new IllegalDeplacementException with specified nested
	 * Throwable and default message.
	 * 
	 * @param cause
	 */
	public IllegalDeplacementException(final Throwable cause)
	{
		super(cause);
	}

	/**
	 * Constructs a new IllegalDeplacementException with entity that prevent the
	 * deplacement.
	 * 
	 * @param neighboringEntity
	 */
	public IllegalDeplacementException(final Entity neighboringEntity)
	{
		super("Le déplacement n'est pas possible. Objet rencontré de type '" + neighboringEntity.getClass() + "'");
	}

}
