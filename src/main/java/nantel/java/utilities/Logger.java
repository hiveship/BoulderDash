package nantel.java.utilities;

public interface Logger
{
	boolean isErrorEnabled();

	// Remove ?
	void setErrorEnable(boolean enable);

	void error(CharSequence content);

	boolean isWarnEnabled();

	void warn(CharSequence content);

	// Remove ?
	void setWarnEnable(boolean enable);

	boolean isInfoEnabled();

	void info(CharSequence content);

	// Remove ?
	void setInfoEnable(boolean enable);

	boolean isDebugEnabled();

	void debug(CharSequence content);

	// Remove ?
	void setDebugEnable(boolean enable);

}
