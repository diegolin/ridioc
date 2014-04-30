package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveLongConstructor;

public class BeanWithPrimitiveLongConstructorImpl implements BeanWithPrimitiveLongConstructor {

    private final long testLong;

    public BeanWithPrimitiveLongConstructorImpl(long testLong) {
        this.testLong = testLong;
    }

    @Override
    public long getTestLong() {
        return testLong;
    }
}
