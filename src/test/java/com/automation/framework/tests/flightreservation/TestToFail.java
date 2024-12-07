package com.automation.framework.tests.flightreservation;

import com.automation.framework.utils.TestGroups;
import org.testng.annotations.Test;

public class TestToFail {

    @Test(groups = {TestGroups.REGRESSION, TestGroups.SMOKE})
    public void failTest() {
        assert false;
    }

}
