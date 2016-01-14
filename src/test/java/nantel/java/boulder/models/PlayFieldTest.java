package nantel.java.boulder.models;

import junit.framework.Assert;
import nantel.java.boulder.models.entities.Dirt;
import nantel.java.boulder.models.entities.EmptySpace;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.entities.moovable.Boulder;
import nantel.java.boulder.models.entities.moovable.Diamond;
import nantel.java.boulder.models.entities.moovable.Exit;
import nantel.java.boulder.models.entities.moovable.Rockford;
import nantel.java.boulder.models.entities.walls.BrickWall;
import nantel.java.boulder.models.entities.walls.SteelWall;
import nantel.java.boulder.models.exceptions.IllegalDeplacementException;
import nantel.java.boulder.models.exceptions.IllegalEntityPlacementException;

import org.junit.Before;
import org.junit.Test;

public class PlayFieldTest
{
	private PlayField playField;

	@Before
	public void setUp()
	{
		// Provides an initial playfield for each test.
		playField = new PlayField(10, 20);
	}

	@Test
	public void verifyInitResult()
	{
		final int rowsCount = playField.getRowsCount();
		final int columnsCount = playField.getColumnsCount();

		for ( int i = 0; i < rowsCount; i++ ) {
			for ( int j = 0; j < columnsCount; j++ ) {
				if ( i == 0 || i == rowsCount - 1 || j == 0 || j == columnsCount - 1 ) {
					Assert.assertTrue(playField.getContentAt(i, j) instanceof SteelWall);
				} else {
					Assert.assertTrue(playField.getContentAt(i, j) instanceof Dirt);
				}
			}
		}
	}

	@Test
	public void addEntity()
	{
		int x = 1;
		int y = 1;
		Dirt dirt = new Dirt();
		playField.addEntity(x, y, dirt);
		Assert.assertTrue(playField.getContentAt(x, y).equals(dirt));
	}

	@Test
	public void addEntityPreventSingeletons()
	{
		addEntityFailure(new Rockford());
		addEntityFailure(new Exit());
	}

	private void addEntityFailure(final Entity entity)
	{
		try {
			playField.addEntity(1, 1, entity);
			assert false;
		} catch ( IllegalEntityPlacementException e ) {
			assert true;
		}
	}

	@Test
	public void addMovableEntity()
	{
		int x = 1;
		int y = 1;
		Boulder boulder = new Boulder();
		playField.addEntity(x, y, boulder);
		Assert.assertTrue(playField.getContentAt(x, y).equals(boulder));
		Assert.assertTrue(boulder.getX() == x);
		Assert.assertTrue(boulder.getY() == y);
	}

	@Test
	public void moveRockfordAllowed()
	{
		playField.initRockfordPosition(1, 1);
		try {
			for ( int i = 0; i < 2; i++ ) {
				playField.moveRockford(Direction.RIGHT);
				playField.moveRockford(Direction.DOWN);
				playField.moveRockford(Direction.LEFT);
				playField.moveRockford(Direction.UP);
			}
			playField.addEntity(1, 2, new Diamond());
			playField.moveRockford(Direction.RIGHT);
		} catch ( IllegalDeplacementException e ) {
			assert false;
		}

	}

	@Test
	public void moveRockfordNotAllowed()
	{
		playField.initRockfordPosition(1, 1);

		playField.addEntity(2, 1, new Boulder());
		playField.addEntity(3, 1, new BrickWall());
		playField.addEntity(4, 1, new BrickWall());

		checkMoveFailure(Direction.UP);
		checkMoveFailure(Direction.LEFT);
		playField.moveRockford(Direction.RIGHT);
		playField.moveRockford(Direction.DOWN);
		checkMoveFailure(Direction.LEFT);
		playField.moveRockford(Direction.DOWN);
		checkMoveFailure(Direction.LEFT);
		playField.moveRockford(Direction.DOWN);
		checkMoveFailure(Direction.LEFT);
	}

	private void checkMoveFailure(final Direction direction)
	{
		try {
			playField.moveRockford(direction);
			assert false;
		} catch ( IllegalDeplacementException e ) {
			assert true;
		}
	}

	@Test
	public void updateMovableEntityFalling()
	{
		// Peut tomber si vide en dessous ou les crans à droite et en bas à droite idem gauche
	}

	@Test
	public void pushBoulderOnRight()
	{
		playField.initRockfordPosition(1, 1);

		Boulder boulder = new Boulder();
		playField.addEntity(1, 2, boulder);
		playField.addEntity(1, 3, new EmptySpace());

		int previousboulderXCoord = boulder.getX();
		int previousBoulderYCoord = boulder.getY();

		playField.moveRockford(Direction.RIGHT);

		Assert.assertTrue(previousboulderXCoord == boulder.getX());
		Assert.assertTrue(previousBoulderYCoord == boulder.getY() - 1);
	}

	@Test
	public void pushBoulderOnLeft()
	{
		playField.initRockfordPosition(1, 4);

		Boulder boulder = new Boulder();
		playField.addEntity(1, 3, boulder);
		playField.addEntity(1, 2, new EmptySpace());

		int previousX = boulder.getX();
		int previousY = boulder.getY();

		playField.moveRockford(Direction.LEFT);

		Assert.assertTrue(previousX == boulder.getX());
		Assert.assertTrue(previousY == boulder.getY() + 1);
	}

	@Test
	public void pushBoulderNotAllowedSides()
	{
		playField.initRockfordPosition(1, 1);

		pushBoulderNotAllowed(new SteelWall());
		pushBoulderNotAllowed(new BrickWall());
		pushBoulderNotAllowed(new Diamond());
		pushBoulderNotAllowed(new Boulder());
		pushBoulderNotAllowed(new Dirt());
	}

	@Test
	public void pushBoulderNotAllowedUp()
	{
		playField.initRockfordPosition(3, 1);
		playField.addEntity(2, 1, new Boulder());
		playField.addEntity(1, 1, new EmptySpace());
		try {
			playField.moveRockford(Direction.UP);
			assert false;
		} catch ( IllegalDeplacementException e ) {
			assert true;
		}
	}

	private void pushBoulderNotAllowed(final Entity entity)
	{
		playField.addEntity(1, 2, new Boulder());
		playField.addEntity(1, 3, entity);

		try {
			playField.moveRockford(Direction.RIGHT);
			assert false;
		} catch ( IllegalDeplacementException e ) {
			assert true;
		}
	}

	@Test
	public void crushedByBoulder()
	{
		playField.initRockfordPosition(3, 1);
		playField.addEntity(2, 1, new EmptySpace());
		playField.addEntity(1, 1, new Boulder());

		playField.updateMovableEntities();
		playField.updateMovableEntities();

		Assert.assertFalse(playField.getRockford().isAlive());

		Assert.assertTrue(playField.getContentAt(0, 0) instanceof SteelWall);
		Assert.assertTrue(playField.getContentAt(0, 1) instanceof SteelWall);
		Assert.assertTrue(playField.getContentAt(0, 2) instanceof SteelWall);
		Assert.assertTrue(playField.getContentAt(0, 3) instanceof SteelWall);
		Assert.assertTrue(playField.getContentAt(0, 4) instanceof SteelWall);
		Assert.assertTrue(playField.getContentAt(0, 5) instanceof SteelWall);
		Assert.assertTrue(playField.getContentAt(1, 0) instanceof SteelWall);
		Assert.assertTrue(playField.getContentAt(1, 1) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(2, 1) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(3, 1) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(4, 1) instanceof EmptySpace);
		Assert.assertFalse(playField.getContentAt(5, 1) instanceof EmptySpace);
	}

	@Test
	public void crushedByDiamond()
	{
		playField.initRockfordPosition(6, 3);
		playField.addEntity(2, 3, new EmptySpace());
		playField.addEntity(3, 3, new EmptySpace());
		playField.addEntity(4, 3, new EmptySpace());
		playField.addEntity(5, 3, new EmptySpace());
		playField.addEntity(1, 3, new Diamond());

		playField.updateMovableEntities();
		playField.updateMovableEntities();
		playField.updateMovableEntities();
		playField.updateMovableEntities();
		playField.updateMovableEntities();

		Assert.assertFalse(playField.getRockford().isAlive());
		Assert.assertTrue(playField.getContentAt(5, 2) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(5, 3) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(5, 4) instanceof EmptySpace);

		Assert.assertTrue(playField.getContentAt(6, 2) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(6, 3) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(6, 4) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(7, 2) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(7, 3) instanceof EmptySpace);
		Assert.assertTrue(playField.getContentAt(7, 4) instanceof EmptySpace);

		Assert.assertFalse(playField.getContentAt(5, 1) instanceof EmptySpace);
		Assert.assertFalse(playField.getContentAt(5, 5) instanceof EmptySpace);

		Assert.assertFalse(playField.getContentAt(6, 1) instanceof EmptySpace);
		Assert.assertFalse(playField.getContentAt(6, 5) instanceof EmptySpace);

		Assert.assertFalse(playField.getContentAt(7, 1) instanceof EmptySpace);
		Assert.assertFalse(playField.getContentAt(7, 5) instanceof EmptySpace);
	}

	@Test
	public void notCrushedByBoulder()
	{
		playField.initRockfordPosition(2, 1);
		playField.addEntity(1, 1, new Boulder());

		playField.updateMovableEntities();
		Assert.assertTrue(playField.getRockford().isAlive());
	}

	@Test
	public void notCrushedByDiamond()
	{
		playField.initRockfordPosition(2, 1);
		playField.addEntity(1, 1, new Diamond());

		playField.updateMovableEntities();
		Assert.assertTrue(playField.getRockford().isAlive());
	}
}
