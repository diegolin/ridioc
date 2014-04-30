package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveDoubleConstructor;

public class BeanWithPrimitiveDoubleConstructorImpl implements BeanWithPrimitiveDoubleConstructor {
    private final double testDouble;

    public BeanWithPrimitiveDoubleConstructorImpl(double testDouble) {
        this.testDouble = testDouble;
    }

    @Override
    public double getTestDouble() {
        return testDouble;
    }
}
