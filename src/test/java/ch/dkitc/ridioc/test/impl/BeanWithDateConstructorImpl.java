package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithDateConstructor;

import java.util.Date;

public class BeanWithDateConstructorImpl implements BeanWithDateConstructor {

    private final Date testDate;

    public BeanWithDateConstructorImpl(Date testDate) {
        this.testDate = testDate;
    }

    @Override
    public Date getTestDate() {
        return testDate;
    }
}
