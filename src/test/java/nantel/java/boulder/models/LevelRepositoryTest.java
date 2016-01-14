package nantel.java.boulder.models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import junit.framework.Assert;
import nantel.java.boulder.models.entities.moovable.Boulder;
import nantel.java.boulder.models.entities.moovable.Diamond;
import nantel.java.boulder.models.exceptions.LevelLoadingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//@Test methods can not be declared as static with JUnit.
@SuppressWarnings("static-method")
public class LevelRepositoryTest
{
	private static final String TEST_FILE_NAME = "TEST";

	private static Level niveau;

	@Before
	public void setUp() throws IOException
	{
		niveau = new Level(10, 10, 1);
		LevelRepository.save(niveau, TEST_FILE_NAME);
	}

	@After
	public void tearDown()
	{
		try {
			LevelRepository.delete(TEST_FILE_NAME);
		} catch ( IOException e ) {
			// Maybe the file does not exists. It is not a problem.
		}
	}

	@Test
	public void save()
	{
		assert niveau != null;
		try {
			LevelRepository.save(niveau, TEST_FILE_NAME);
		} catch ( IOException e ) {
			assert false : "Saving a level should not fail";
		}
	}

	@Test
	public void delete()
	{
		try {
			LevelRepository.delete(LevelRepository.DEFAULT_FOLDER_PATH + LevelRepository.PERSISTANCE_FILE_LEVEL_PREFIX + TEST_FILE_NAME);
		} catch ( IOException e ) {
			assert false : "The suppression of the existing level should not fail.";
		}
	}

	@Test
	public void loadSameLevel()
	{
		Level niveau = LevelRepositoryTest.niveau;
		assert niveau != null;

		try {
			Level niveauLoaded = LevelRepository.load(TEST_FILE_NAME);
			Assert.assertNotNull(niveauLoaded);
			Assert.assertTrue(niveauLoaded.getPlayField().equals(niveau.getPlayField()));
		} catch ( LevelLoadingException e ) {
			assert false : "Loading a level should not fail.";
		}
	}

	@Test
	public void loadAnotherLevel() throws IOException
	{
		// Create another level
		Level anotherNiveau = new Level(10, 10, 1);

		// Change the content of the new level
		anotherNiveau.getPlayField().addEntity(1, 8, new Diamond());
		anotherNiveau.getPlayField().addEntity(2, 7, new Boulder());
		LevelRepository.save(anotherNiveau, "other-level");

		Level niveauLoaded = LevelRepository.load("other-level");
		Assert.assertNotNull(niveauLoaded);
		Assert.assertFalse(niveauLoaded.getPlayField().equals(niveau.getPlayField()));
		LevelRepository.delete("other-level");
	}

	@Test
	public void levelEquals()
	{
		// Level equals itself
		assert niveau != null;
		final Level niveauChecked = niveau;
		Assert.assertTrue(niveauChecked.getPlayField().equals(niveauChecked.getPlayField()));

		// Create another instance of the same level
		Level same = new Level(10, 10, 1);
		Assert.assertTrue(same.getPlayField().equals(niveauChecked.getPlayField()));
	}

	@Test
	public void loadWrongFileName() throws IOException
	{
		//		String fakeFileName = "target/fake-level-save";
		String fakeFileName = LevelRepositoryTest.class.getResource("/levels/").getPath() + "fake-level-save";

		// Create a file without the good prefix name.
		try ( ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fakeFileName)); ) {
			outputStream.writeObject(null);
		}

		List<String> existingFiles = LevelRepository.getAllExistingLevelFileNames();
		Assert.assertFalse(existingFiles.contains(fakeFileName));

		// Revert changes.
		Files.delete(Paths.get(fakeFileName)); // don't fail on inexisting file.
	}

}
