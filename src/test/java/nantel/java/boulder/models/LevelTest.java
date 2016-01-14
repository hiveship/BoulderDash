package nantel.java.boulder.models;

import junit.framework.Assert;
import nantel.java.boulder.models.entities.moovable.Diamond;
import nantel.java.boulder.models.exceptions.ExitNotAllowedException;

import org.junit.Before;
import org.junit.Test;

public class LevelTest
{
	private Level level;

	@Before
	public void setUp()
	{
		// Provides an initial playfield for each test.
		level = new Level(10, 10, 3);
		level.getPlayField().initExitPosition(1, 5);
		level.getPlayField().initRockfordPosition(1, 1);
	}

	@Test
	public void exitNotAllowed()
	{
		level.getPlayField().addEntity(1, 2, new Diamond());
		level.getPlayField().addEntity(1, 3, new Diamond());

		Assert.assertFalse(level.isFinish());

		// Moove rockford to consume the good number of diamonds
		Assert.assertFalse(level.getPlayField().getExit().isAllowed());
		level.getPlayField().moveRockford(Direction.RIGHT);
		level.getPlayField().moveRockford(Direction.RIGHT);
		level.getPlayField().moveRockford(Direction.RIGHT);
		level.updateExitState();
		Assert.assertFalse(level.getPlayField().getExit().isAllowed());

		// Moove rockford in order to get into the exit case
		Assert.assertFalse(level.getPlayField().getExit().isReached());
		try {
			level.getPlayField().moveRockford(Direction.RIGHT);
			assert false;
		} catch ( ExitNotAllowedException e ) {
			assert true;
		}
		Assert.assertFalse(level.getPlayField().getExit().isReached()); // reached only if allowed

		Assert.assertFalse(level.isFinish());
	}

	@Test
	public void exitAllowed()
	{
		level.getPlayField().addEntity(1, 2, new Diamond());
		level.getPlayField().addEntity(1, 3, new Diamond());
		level.getPlayField().addEntity(1, 4, new Diamond());

		Assert.assertFalse(level.isFinish());

		// Moove rockford to consume the good number of diamonds
		Assert.assertFalse(level.getPlayField().getExit().isAllowed());
		level.getPlayField().moveRockford(Direction.RIGHT);
		level.getPlayField().moveRockford(Direction.RIGHT);
		level.getPlayField().moveRockford(Direction.RIGHT);
		level.updateExitState();
		Assert.assertTrue(level.getPlayField().getExit().isAllowed());

		// Moove rockford in order to get into the exit case
		Assert.assertFalse(level.getPlayField().getExit().isReached());
		level.getPlayField().moveRockford(Direction.RIGHT);
		Assert.assertTrue(level.getPlayField().getExit().isReached());

		Assert.assertTrue(level.isFinish());
	}

	@Test
	public void collectDiamond()
	{
		// Place diamonds on the playfield
		level.getPlayField().addEntity(1, 2, new Diamond());

		Assert.assertTrue(level.getPlayField().getRockford().getDiamondsCount() == 0);

		// Moove rockford ton consume diamonds
		int previousDiamondCount = level.getPlayField().getRockford().getDiamondsCount();
		level.getPlayField().moveRockford(Direction.RIGHT);

		Assert.assertTrue(level.getPlayField().getRockford().getDiamondsCount() == previousDiamondCount + 1);
	}

	@Test
	public void mooveWithoutCollectingDiamond()
	{
		// Moove rockford ton consume diamonds
		int previousDiamondCount = level.getPlayField().getRockford().getDiamondsCount();
		level.getPlayField().moveRockford(Direction.RIGHT);
		int newDiamondCount = level.getPlayField().getRockford().getDiamondsCount();
		Assert.assertTrue(newDiamondCount == previousDiamondCount);
	}

}
