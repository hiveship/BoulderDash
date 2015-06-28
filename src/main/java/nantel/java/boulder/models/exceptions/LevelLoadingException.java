package nantel.java.boulder.models.exceptions;

public class LevelLoadingException extends RuntimeException
{
	private static final long serialVersionUID = 5267139803113442320L;

	/**
	 * Constructs a new LevelLoadingException with default message.
	 */
	public LevelLoadingException()
	{
		super();
	}

	/**
	 * Constructs a new LevelLoadingException with specified detail message.
	 * 
	 * @param message
	 */
	public LevelLoadingException(final String message)
	{
		super(message);
	}

}
