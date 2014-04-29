package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveDoubleConstructor;

public class TestBeanWithPrimitiveDoubleConstructorImpl implements TestBeanWithPrimitiveDoubleConstructor {
    private final double testDouble;

    public TestBeanWithPrimitiveDoubleConstructorImpl(double testDouble) {
        this.testDouble = testDouble;
    }

    @Override
    public double getTestDouble() {
        return testDouble;
    }
}
