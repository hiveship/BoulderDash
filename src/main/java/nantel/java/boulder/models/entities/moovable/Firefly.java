package nantel.java.boulder.models.entities.moovable;

import java.awt.image.BufferedImage;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.entities.animations.SpriteRepository;
import nantel.java.boulder.models.exceptions.NotImplementedException;

public class Firefly extends AbstractMovableEntity
{
	private static final long serialVersionUID = -3397040700560304221L;
	private static final String SPRITE_NAME = "firefly";

	public Firefly(final @Nullable Integer x, final @Nullable Integer y)
	{
		super(x, y);
		throw new NotImplementedException("Firefly is not implemented yet");
	}

	public Firefly()
	{
		this(null, null);
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
		return false;
	}

	@Override
	public String getName()
	{
		return "FF";
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
