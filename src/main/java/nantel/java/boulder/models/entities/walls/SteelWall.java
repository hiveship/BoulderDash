package nantel.java.boulder.models.entities.walls;

import java.awt.image.BufferedImage;

import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.entities.animations.SpriteRepository;

public class SteelWall implements Entity
{
	private static final long serialVersionUID = 9092544313736382686L;
	private static final String SPRITE_NAME = "steelwall";

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
		return "SW";
	}

	@Override
	public boolean isUnique()
	{
		return false;
	}

	@Override
	public boolean isAnimated()
	{
		return false;
	}

	@Override
	public boolean canChainExplosion()
	{
		return false;
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
