package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestSQLHandler.class,
            TestNodeT.class,
            TestPathT.class
})

public class AllTests
{

}
