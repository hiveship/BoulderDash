package nantel.java.boulder.models.entities;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Global interface for all the entities. We need to insure that all class
 * implementing this interface are serializable.
 */
public interface Entity extends Serializable
{
	/**
	 * Default size (in pixels) for each individual sprite element.
	 */
	int DEFAULT_SPRITE_SIZE = 16;

	/**
	 * Indicate whether this entity must be unique in the play field.
	 */
	public boolean isUnique();

	/**
	 * Indicate whether this entity can be moved.
	 */
	public boolean isMovable();

	/**
	 * Indicate whether this entity can be reached by Rockford.
	 */
	public boolean isConsomable();

	/**
	 * Indicate whether this entity can create a new explosion.
	 */
	public boolean isExplodable();

	/**
	 * Indicate whether this entity can explode if a neighbouring entity
	 * explode.
	 */
	public boolean canChainExplosion();

	/**
	 * Indicate whether this entity is rounded.
	 */
	public boolean isRounded();

	/**
	 * Indicate if the spritesheet of the entity must be animated.
	 */
	public boolean isAnimated();

	// Debug/Development only
	public String getName();

	/**
	 * Get the image corresponding to the entity
	 */
	public BufferedImage getSprite();

}
