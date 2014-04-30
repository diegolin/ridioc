package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithDateArrayConstructor;

import java.util.Date;

public class BeanWithDateArrayConstructorImpl implements BeanWithDateArrayConstructor {

    private final Date[] testDates;

    public BeanWithDateArrayConstructorImpl(Date[] testDates) {
        this.testDates = testDates;
    }

    @Override
    public Date[] getTestDates() {
        return testDates;
    }
}
