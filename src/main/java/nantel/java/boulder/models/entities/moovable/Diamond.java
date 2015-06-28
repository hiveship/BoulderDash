package nantel.java.boulder.models.entities.moovable;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import nantel.java.annotations.Nullable;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.entities.animations.SpriteRepository;

public class Diamond extends AbstractMovableEntity
{
	private static final long serialVersionUID = -7037446801291172949L;
	private static final String SPRITE_NAME = "diamond";
	private int currentFrame;
	private static List<BufferedImage> images = new LinkedList<>();

	static {
		// Initialise le list of individual sprites for the animated element.
		Diamond.initSprites();
	}

	public Diamond(final @Nullable Integer x, final @Nullable Integer y)
	{
		super(x, y);
		this.currentFrame = 0;
	}

	public Diamond()
	{
		this(null, null);
	}

	@Override
	public boolean isConsomable()
	{
		return true;
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
		return "DI";
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

	// =======
	// SPRITES
	// =======

	public static String getSpriteName()
	{
		return SPRITE_NAME;
	}

	@Override
	public BufferedImage getSprite()
	{
		try {
			BufferedImage sprite = images.get(currentFrame);
			currentFrame++;
			assert sprite != null;
			return sprite;
		} catch ( IndexOutOfBoundsException e ) {
			this.currentFrame = 0;
			return getSprite();
		}

	}

	private static void initSprites()
	{
		if ( images.isEmpty() ) {
			for ( int i = 0; i < getIndividualSpriteCount(); i++ ) {
				images.add(SpriteRepository.getIndividualSprite(getSpriteSheet(), getBlocSize(), Entity.DEFAULT_SPRITE_SIZE, i));
			}
		}
	}

	private static int getBlocSize()
	{
		return 24;
	}

	public static int getIndividualSpriteCount()
	{
		return 8;
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
