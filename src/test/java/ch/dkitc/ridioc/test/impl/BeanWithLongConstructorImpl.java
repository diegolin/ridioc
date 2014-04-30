package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithLongConstructor;

public class BeanWithLongConstructorImpl implements BeanWithLongConstructor {

    private final Long testLong;

    public BeanWithLongConstructorImpl(Long testLong) {
        this.testLong = testLong;
    }

    @Override
    public Long getTestLong() {
        return testLong;
    }
}
