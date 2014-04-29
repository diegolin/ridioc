package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveLongConstructor;

public class TestBeanWithPrimitiveLongConstructorImpl implements TestBeanWithPrimitiveLongConstructor {

    private final long testLong;

    public TestBeanWithPrimitiveLongConstructorImpl(long testLong) {
        this.testLong = testLong;
    }

    @Override
    public long getTestLong() {
        return testLong;
    }
}
