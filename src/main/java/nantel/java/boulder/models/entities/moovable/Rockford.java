package nantel.java.boulder.models.entities.moovable;

import java.awt.image.BufferedImage;

import nantel.java.boulder.models.entities.animations.SpriteRepository;

public final class Rockford extends AbstractMovableEntity
{
	private static final long serialVersionUID = -3989530524530574632L;
	private static final String SPRITE_NAME = "rockford";

	private int diamonds;
	private boolean alive;

	public Rockford()
	{
		super();
		this.alive = true;
		this.diamonds = 0;
	}

	@Override
	public boolean isMovable()
	{
		return true;
	}

	@Override
	public boolean isConsomable()
	{
		return false;
	}

	@Override
	public boolean isRounded()
	{
		return false;
	}

	@Override
	public boolean isExplodable()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "RF";
	}

	public int getDiamondsCount()
	{
		return diamonds;
	}

	public void collectDiamond()
	{
		this.diamonds = this.diamonds + 1;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void die()
	{
		this.alive = false;
	}

	@Override
	public boolean isUnique()
	{
		return true;
	}

	@Override
	public boolean canChainExplosion()
	{
		return true;
	}

	@Override
	public boolean isAnimated()
	{
		return true;
	}

	public static String getSpriteName()
	{
		return SPRITE_NAME;
	}

	@Override
	public BufferedImage getSprite()
	{
		return getSpriteSheet();
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
