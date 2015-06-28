package nantel.java.boulder.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nantel.java.boulder.models.exceptions.LevelLoadingException;
import nantel.java.utilities.DefaultLogger;
import nantel.java.utilities.Logger;

public class LevelRepository
{
	private static final Logger LOGGER = new DefaultLogger();

	/**
	 * Prefix that will be added to all level files.
	 */
	public static final String PERSISTANCE_FILE_LEVEL_PREFIX = "boulderdash-level-";

	/**
	 * Default path to save the levels.
	 */
	@SuppressWarnings("null")
	public static final String DEFAULT_FOLDER_PATH = LevelRepository.class.getResource("/levels/").getPath();

	/**
	 * Unique name that will be used if someone try to save a level without
	 * specifying a name.
	 */
	@SuppressWarnings("null")
	private static final String DEFAULT_UNIQUE_FILE_NAME = UUID.randomUUID().toString();

	//=======
	// CREATE
	//=======

	/**
	 * @param level
	 *            you want to save
	 * @param outputFileName
	 *            the name of the output file if you don't want to use the
	 *            level's name
	 * @throws IOException
	 */
	public static void save(final Level level, final String outputFileName) throws IOException
	{
		try ( ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DEFAULT_FOLDER_PATH + PERSISTANCE_FILE_LEVEL_PREFIX + outputFileName)); ) {
			outputStream.writeObject(level);
		}
	}

	/**
	 * Save the level using the default folder path.
	 * 
	 * @param level
	 *            you want to save
	 * @throws IOException
	 */
	public static void save(final Level level) throws IOException
	{
		String outputName;
		if ( level.getName() != null ) {
			outputName = level.getName();
		} else {
			outputName = DEFAULT_UNIQUE_FILE_NAME;
		}
		assert outputName != null;
		save(level, outputName);
		LOGGER.debug("A level has been successfully saved.");
	}

	//==============
	// GET / GET ALL
	//==============

	/**
	 * Load an existing level from the file name.
	 * 
	 * @param fileName
	 * @return the level
	 * @throws LevelLoadingException
	 *             if an error occurs while loading the level
	 */
	public static Level load(final String fileName) throws LevelLoadingException
	{
		try ( ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DEFAULT_FOLDER_PATH + PERSISTANCE_FILE_LEVEL_PREFIX + fileName)) ) {
			Level level = (Level) inputStream.readObject();
			if ( level != null ) {
				LOGGER.debug("A level has been successfully loaded.");
				return level;
			} else {
				throw new Exception(); // The type of the exception is not important here. It will be catched in the current method.
			}
		} catch ( Exception e ) {
			throw new LevelLoadingException("Can not load a level at '" + DEFAULT_FOLDER_PATH + PERSISTANCE_FILE_LEVEL_PREFIX + fileName);
		}
	}

	/**
	 * Get the list of all existing levels.
	 * 
	 * @param directoryPath
	 *            where to search for levels
	 * @return a list of names
	 */
	public static List<String> getAllExistingFileNames(final String directoryPath)
	{
		List<String> fileNames = new ArrayList<>();
		for ( File element : new File(directoryPath).listFiles() ) {
			if ( element.isFile() && element.getName().startsWith(PERSISTANCE_FILE_LEVEL_PREFIX) ) {
				String prettyName = element.getName().replace(PERSISTANCE_FILE_LEVEL_PREFIX, "");
				fileNames.add(prettyName);
				LOGGER.debug("A level has been found -> '" + element.getName() + "'");
			}
			// Don't do it recursively (ignore potential sub-directories).
		}
		return fileNames;
	}

	/**
	 * @return the list of all existing levels names at the default save path.
	 */
	public static List<String> getAllExistingLevelFileNames()
	{
		return getAllExistingFileNames(DEFAULT_FOLDER_PATH);
	}

	//=======
	// DELETE
	//=======

	/**
	 * Delete an existing level.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static void delete(final String fileName) throws IOException
	{
		Files.deleteIfExists(Paths.get(DEFAULT_FOLDER_PATH + PERSISTANCE_FILE_LEVEL_PREFIX + fileName)); // don't fail on unexisting file.
	}

}
