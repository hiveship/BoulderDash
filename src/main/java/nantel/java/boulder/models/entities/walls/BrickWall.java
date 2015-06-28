package nantel.java.boulder.models.entities.walls;

import java.awt.image.BufferedImage;

import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.entities.animations.SpriteRepository;

public class BrickWall implements Entity
{
	private static final long serialVersionUID = -3855319217579610139L;
	private static final String SPRITE_NAME = "brickwall";

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
		return true;
	}

	@Override
	public boolean isRounded()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "BW";
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
		// TODO Auto-generated method stub
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
