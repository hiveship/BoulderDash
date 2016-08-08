package nantel.java.boulder.views;

import javax.swing.*;

/**
 * Commons actions for each frames of the application.
 */
public final class ViewUtilities
{
	public static void setGeneralParameters(final JFrame window)
	{
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.validate();
		window.pack();
		window.setVisible(true);
	}

}
