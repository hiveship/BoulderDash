package nantel.java.boulder.models.exceptions;

/**
 * Thrown to indicate that a block of code has not been implemented. This
 * exception supplements UnsupportedOperationException by providing a more
 * semantically rich description of the problem.
 *
 */
public class BoulderDashUnexpectedException extends RuntimeException
{
	private static final long serialVersionUID = 6055018170086293771L;

	/**
	 * Constructs a new BoulderDashUnexpectedException with default message.
	 */
	public BoulderDashUnexpectedException()
	{
		super();
	}

	/**
	 * Constructs a new BoulderDashUnexpectedException with specified detail
	 * message.
	 * 
	 * @param message
	 */
	public BoulderDashUnexpectedException(final String message)
	{
		super(message);
	}

	/**
	 * Constructs a new BoulderDashUnexpectedException with specified detail
	 * message and nested Throwable.
	 * 
	 * @param message
	 * @param cause
	 */
	public BoulderDashUnexpectedException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Constructs a new BoulderDashUnexpectedException with specified nested
	 * Throwable and default message.
	 * 
	 * @param cause
	 */
	public BoulderDashUnexpectedException(final Throwable cause)
	{
		super(cause);
	}

}
