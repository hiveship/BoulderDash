package nantel.java.boulder.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This custom JUnit runner is used to run all tests case at the same time
 * (JUnit Suite).
 */
@RunWith(Suite.class)
@SuiteClasses(
		value = { LevelRepositoryTest.class, PlayFieldTest.class, LevelTest.class })
public class RunAllTests
{
	// Nothing to do.
}
