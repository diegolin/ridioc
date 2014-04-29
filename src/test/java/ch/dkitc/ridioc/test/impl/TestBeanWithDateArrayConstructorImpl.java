package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithDateArrayConstructor;

import java.util.Date;

public class TestBeanWithDateArrayConstructorImpl implements TestBeanWithDateArrayConstructor {

    private final Date[] testDates;

    public TestBeanWithDateArrayConstructorImpl(Date[] testDates) {
        this.testDates = testDates;
    }

    @Override
    public Date[] getTestDates() {
        return testDates;
    }
}
