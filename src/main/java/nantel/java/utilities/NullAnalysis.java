package nantel.java.utilities;

import java.util.Collection;

import nantel.java.annotations.Nullable;

public final class NullAnalysis
{
	// ===================
	// PRIVATE CONSTRUCTOR
	// ===================

	private NullAnalysis()
	{
		assert false;
	}

	// ==========
	// CHECK NULL
	// ==========

	private static void throwException(final String name, final String message)
	{
		throw new IllegalArgumentException(name + " -- " + message);
	}

	public static void checkNull(final String name, final String message, @Nullable final Object value)
	{
		if ( value == null ) {
			throwException(name, message);
		}
	}

	public static void checkNull(final String name, @Nullable final Object value)
	{
		checkNull(name, "The argument should not be 'null'.", value);
	}

	public static void checkNull(final String name, @Nullable final Collection<?> collection)
	{
		if ( collection == null ) {
			throwException(name, "The collection should not be 'null'.");
		} else {
			checkNullContent(name, collection);
		}
	}

	public static <T> void checkNull(final String name, @Nullable final T[] array)
	{
		if ( array == null ) {
			throwException(name, "The array should not be 'null'.");
		} else {
			checkNullContent(name, array);
		}
	}

	public static void checkNullContent(final String name, final Collection<?> collection)
	{
		if ( collection.contains(null) ) {
			throwException(name, "The collection should not contain any 'null' element.");
		}
	}

	public static <T> void checkNullContent(final String name, final T[] array)
	{
		for ( T element : array ) {
			checkNull(name, "The array should not contain any 'null' element.", element);
		}
	}

	public static int getOrdinal(final String name, final Enum<?> value)
	{
		checkNull(name, value);
		return value.ordinal();
	}
}
