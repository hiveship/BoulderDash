package nantel.java.boulder.models.entities.walls;

import java.awt.image.BufferedImage;

import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.entities.animations.SpriteRepository;
import nantel.java.boulder.models.entities.moovable.Boulder;
import nantel.java.boulder.models.entities.moovable.Diamond;
import nantel.java.boulder.models.exceptions.NotImplementedException;

public class MagicWall implements Entity
{
	private static final long serialVersionUID = -3854511922123383768L;
	private static final String SPRITE_NAME = "magicwall";

	public MagicWall()
	{
		throw new NotImplementedException("MagicWall is not implemented yet");
	}

	@Override
	public boolean isMovable()
	{
		return false;
	}

	@Override
	public boolean isConsomable()
	{
		return false;
	}

	@Override
	public boolean isExplodable()
	{
		return false;
	}

	@Override
	public boolean isRounded()
	{
		return false;
	}

	@Override
	public String getName()
	{
		return "MW";
	}

	public static Boulder doMagic(final Diamond diamond)
	{
		return new Boulder(diamond.getX(), diamond.getY());
	}

	public static Diamond doMagic(final Boulder boulder)
	{
		return new Diamond(boulder.getX(), boulder.getY());
	}

	@Override
	public boolean isUnique()
	{
		return false;
	}

	@Override
	public boolean isAnimated()
	{
		return true;
	}

	@Override
	public boolean canChainExplosion()
	{
		return true;
	}

	@Override
	public BufferedImage getSprite()
	{
		return getSpriteSheet();
	}

	public static String getSpriteName()
	{
		return SPRITE_NAME;
	}

	//======================
	// SPRITESHEET SINGLETON
	//======================

	private static final class InstanceHolder // This class is only loaded on first access.
	{
		private static final BufferedImage spriteSheet = SpriteRepository.load(getSpriteName());

		private InstanceHolder()
		{
			// prevent instantiation
		}
	}

	static BufferedImage getSpriteSheet()
	{
		return InstanceHolder.spriteSheet;
	}
}
