package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveIntegerArrayConstructor;

public class BeanWithPrimitiveIntegerArrayConstructorImpl implements BeanWithPrimitiveIntegerArrayConstructor {

    private final int[] testInts;

    public BeanWithPrimitiveIntegerArrayConstructorImpl(int[] testInts) {
        this.testInts = testInts;
    }

    @Override
    public int[] getTestInts() {
        return testInts;
    }
}
