package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveDoubleArrayConstructor;

public class BeanWithPrimitiveDoubleArrayConstructorImpl implements BeanWithPrimitiveDoubleArrayConstructor {

    private final double[] testDoubles;

    public BeanWithPrimitiveDoubleArrayConstructorImpl(double[] testDoubles) {
        this.testDoubles = testDoubles;
    }

    @Override
    public double[] getTestDoubles() {
        return testDoubles;
    }
}
