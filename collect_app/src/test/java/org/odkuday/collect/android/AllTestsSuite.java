package org.odkuday.collect.android;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.odkuday.collect.android.activities.MainActivityTest;
import org.odkuday.collect.android.utilities.CompressionTest;
import org.odkuday.collect.android.utilities.PermissionsTest;
import org.odkuday.collect.android.utilities.TextUtilsTest;

/**
 * Suite for running all unit tests from one place
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        //Name of tests which are going to be run by suite
        MainActivityTest.class,
        PermissionsTest.class,
        TextUtilsTest.class,
        CompressionTest.class
})

public class AllTestsSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}
