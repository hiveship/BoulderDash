package nantel.java.boulder.models.entities.moovable;

import java.awt.image.BufferedImage;

import nantel.java.boulder.models.entities.animations.SpriteRepository;

/**
 * The Exit entity is not movable but we need to conserve it's coords.
 */
public final class Exit extends AbstractMovableEntity
{
	private static final long serialVersionUID = 4419447896514479001L;
	private static final String SPRITE_NAME = "exit";

	private boolean allowed;
	private boolean reached;

	public Exit()
	{
		this.allowed = false;
		this.reached = false;
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

	public boolean isAllowed()
	{
		return allowed;
	}

	public void setAllowed(final boolean allowed)
	{
		this.allowed = allowed;
	}

	public boolean isReached()
	{
		return reached;
	}

	public void setReached(final boolean reached)
	{
		this.reached = reached;
	}

	@Override
	public boolean isRounded()
	{
		return false;
	}

	@Override
	public String getName()
	{
		return "XX";
	}

	@Override
	public boolean isUnique()
	{
		return true;
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
