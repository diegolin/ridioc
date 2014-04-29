package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithDateConstructor;

import java.util.Date;

public class TestBeanWithDateConstructorImpl implements TestBeanWithDateConstructor {

    private final Date testDate;

    public TestBeanWithDateConstructorImpl(Date testDate) {
        this.testDate = testDate;
    }

    @Override
    public Date getTestDate() {
        return testDate;
    }
}
