package nantel.java.boulder.models.exceptions;


public class ExitNotAllowedException extends RuntimeException
{
	private static final long serialVersionUID = 5267139803113442320L;

	/**
	 * Constructs a new ExitNotAllowedException with default message.
	 */
	public ExitNotAllowedException()
	{
		super();
	}

	/**
	 * Constructs a new ExitNotAllowedException with specified detail message.
	 * 
	 * @param message
	 */
	public ExitNotAllowedException(final String message)
	{
		super(message);
	}

}
