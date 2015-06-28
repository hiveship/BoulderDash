package nantel.java.boulder.models.entities.moovable;

import java.awt.image.BufferedImage;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.entities.animations.SpriteRepository;
import nantel.java.boulder.models.exceptions.NotImplementedException;

public class Butterfly extends AbstractMovableEntity
{
	private static final long serialVersionUID = -1826114001458400792L;
	private static final String SPRITE_NAME = "butterfly";

	public Butterfly(final @Nullable Integer x, final @Nullable Integer y)
	{
		super(x, y);
		throw new NotImplementedException("Butterfly is not implemented yet");
	}

	public Butterfly()
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
		return "BT";
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

	@Override
	public boolean canChainExplosion()
	{
		// TODO Auto-generated method stub
		return true;
	}
}
