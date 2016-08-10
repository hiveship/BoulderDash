package nantel.java.boulder.controllers;

import java.io.IOException;
import java.util.List;

import nantel.java.boulder.models.LevelRepository;
import nantel.java.boulder.views.MainWindow;
import nantel.java.boulder.views.editor.LevelEditorWindow;
import nantel.java.boulder.views.game.GameWindow;

@SuppressWarnings("static-method")
/**
 * Main controller of the application. It make the link between the game and the level editor.
 */
public class ApplicationController
{
	public ApplicationController()
	{
		// Nothing to do.
	}

	public List<String> getAllExistingLevelsName()
	{
		return LevelRepository.getAllExistingLevelFileNames();
	}

	@SuppressWarnings("unused")
	/**
	 * Create and display the main window of the application.
	 */
	public void launch()
	{
		new MainWindow(this);
	}

	@SuppressWarnings("unused")
	/**
	 * Start the level editor for the creation of a new level.
	 * @param rows for the new level
	 * @param columns for the new level
	 * @param diamondsToWin for the new level
	 */
	public void launchLevelEditor(final int rows, final int columns, final int diamondsToWin)
	{
		LevelEditorController editorController = new LevelEditorController(rows, columns, diamondsToWin);
		new LevelEditorWindow(editorController);
	}

	/**
	 * Start the level editor with an existing level.
	 * 
	 * @param levelQueried
	 *            name of the level you want to open in the level editor
	 */
	public void launchLevelEditor(final String levelQueried)
	{
		assert levelQueried != null;
		LevelEditorController controller = new LevelEditorController(LevelRepository.load(levelQueried));
		new LevelEditorWindow(controller);
	}

	/**
	 * Start the game.
	 * 
	 * @param levelQueried
	 *            name of the level you want to play
	 */
	public void lauchGame(final String levelQueried)
	{
		assert levelQueried != null;
		GameController controller = new GameController(LevelRepository.load(levelQueried));
		new GameWindow(controller);
	}

	/**
	 * Delete an existing level
	 * 
	 * @param levelName
	 *            name of the level you want to delete
	 * @throws IOException
	 *             if an error occurs when trying to delete the level
	 */
	public void deleteLevel(final String levelName) throws IOException
	{
		LevelRepository.delete(levelName);
	}
}
