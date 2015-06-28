package nantel.java.utilities;

public class DefaultLogger implements Logger
{
	private boolean error = Boolean.parseBoolean(System.getProperty("error"));
	private boolean info = Boolean.parseBoolean(System.getProperty("info"));
	private boolean warn = Boolean.parseBoolean(System.getProperty("warn"));
	private boolean debug = Boolean.parseBoolean(System.getProperty("debug"));

	@Override
	public boolean isErrorEnabled()
	{
		return error;
	}

	@Override
	public void setErrorEnable(final boolean enable)
	{
		this.error = enable;
	}

	@Override
	public void error(final CharSequence content)
	{
		if ( isErrorEnabled() ) {
			System.out.println("[DEBUG]  " + content); // NOSONAR: System.out and System.err should not be used as loggers
		}
	}

	@Override
	public boolean isWarnEnabled()
	{
		return warn;
	}

	@Override
	public void setWarnEnable(final boolean enable)
	{
		this.warn = enable;
	}

	@Override
	public void warn(final CharSequence content)
	{
		if ( isWarnEnabled() ) {
			System.out.println("[DEBUG]  " + content); // NOSONAR: System.out and System.err should not be used as loggers
		}
	}

	@Override
	public boolean isInfoEnabled()
	{
		return info;
	}

	@Override
	public void setInfoEnable(final boolean enable)
	{
		this.info = enable;
	}

	@Override
	public void info(final CharSequence content)
	{
		if ( isInfoEnabled() ) {
			System.out.println("[DEBUG]  " + content); // NOSONAR: System.out and System.err should not be used as loggers
		}
	}

	@Override
	public boolean isDebugEnabled()
	{
		return debug;
	}

	@Override
	public void setDebugEnable(final boolean enable)
	{
		this.debug = enable;
	}

	@Override
	public void debug(final CharSequence content)
	{
		if ( isDebugEnabled() ) {
			System.out.println("[DEBUG]  " + content); // NOSONAR: System.out and System.err should not be used as loggers
		}
	}
}
