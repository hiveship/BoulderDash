package nantel.java.boulder.models.entities.moovable;

import java.awt.image.BufferedImage;

import nantel.java.boulder.models.entities.animations.SpriteRepository;

public class Boulder extends AbstractMovableEntity
{
	private static final long serialVersionUID = 9099724342117813554L;
	private static final String SPRITE_NAME = "boulder";

	public Boulder(final Integer x, final Integer y)
	{
		super(x, y);
	}

	public Boulder()
	{
		super();
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
		return "BD";
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
