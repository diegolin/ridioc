package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithLongConstructor;

public class TestBeanWithLongConstructorImpl implements TestBeanWithLongConstructor {

    private final Long testLong;

    public TestBeanWithLongConstructorImpl(Long testLong) {
        this.testLong = testLong;
    }

    @Override
    public Long getTestLong() {
        return testLong;
    }
}
