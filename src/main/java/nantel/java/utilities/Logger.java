package nantel.java.utilities;

public interface Logger
{
	boolean isErrorEnabled();

	void setErrorEnable(boolean enable);

	void error(CharSequence content);

	boolean isWarnEnabled();

	void warn(CharSequence content);

	void setWarnEnable(boolean enable);

	boolean isInfoEnabled();

	void info(CharSequence content);

	void setInfoEnable(boolean enable);

	boolean isDebugEnabled();

	void debug(CharSequence content);

	void setDebugEnable(boolean enable);

}
