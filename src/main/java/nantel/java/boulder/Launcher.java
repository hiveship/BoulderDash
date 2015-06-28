package nantel.java.boulder;

import java.io.IOException;

import nantel.java.boulder.controllers.ApplicationController;

/**
 * Main class of the application. It will start the main window and let the user
 * access the game and the level editor.
 *
 */
public class Launcher
{
	@SuppressWarnings("unused")
	public static void main(final String[] args) throws IOException
	{
		new ApplicationController().launch();
	}
}
