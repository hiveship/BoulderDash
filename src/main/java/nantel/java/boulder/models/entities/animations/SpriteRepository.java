package nantel.java.boulder.models.entities.animations;

import nantel.java.boulder.models.exceptions.BoulderDashUnexpectedException;
import nantel.java.utilities.DefaultLogger;
import nantel.java.utilities.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class used to manage the sprites elements.
 */
public class SpriteRepository
{
	private static final Logger LOGGER = new DefaultLogger(SpriteRepository.class);
	@SuppressWarnings("null")
	private static String SPRITE_DIRECTORY_PATH = SpriteRepository.class.getResource("/sprites/").getPath();
	private static String SPRITE_FILE_EXTENSION = ".gif";

	public SpriteRepository()
	{
		// Nothing to do (only static methods).
	}

	/**
	 * Load the sprite sheet for the given sprite name.
	 */
	public static BufferedImage load(final String spriteName)
	{
		try {
			BufferedImage spriteSheet = ImageIO.read(new File(SPRITE_DIRECTORY_PATH + spriteName + SPRITE_FILE_EXTENSION));
			assert spriteSheet != null;
			LOGGER.debug("The sprite sheet for '" + spriteName + "' has been loaded.");
			return spriteSheet;
		} catch ( IOException e ) {
			throw new BoulderDashUnexpectedException("Can not load the sprite sheet at '" + SPRITE_DIRECTORY_PATH + spriteName + SPRITE_FILE_EXTENSION);
		}
	}

	/**
	 * Returns the individual sprite image corresponding to the index in the
	 * linear (only one row) sprite sheet.
	 */
	public static BufferedImage getIndividualSprite(final BufferedImage spriteSheet, final int blocSize, final int individualSize, final int columnIndex)
	{
		BufferedImage subimage = spriteSheet.getSubimage(columnIndex * blocSize, 0, individualSize, individualSize);
		assert subimage != null;
		return subimage;
	}

	/**
	 * Returns the individual sprite image corresponding to the index in the
	 * multi row sprite sheet.
	 */
	public static BufferedImage getIndividualSprite(final BufferedImage spriteSheet, final int blocSize, final int individualSize, final int index, final int rowIndex)
	{
		BufferedImage subimage = spriteSheet.getSubimage(index * blocSize, rowIndex * blocSize, individualSize, individualSize);
		assert subimage != null;
		return subimage;
	}
}
