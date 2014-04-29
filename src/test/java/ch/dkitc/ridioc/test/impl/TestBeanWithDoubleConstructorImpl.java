package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithDoubleConstructor;

public class TestBeanWithDoubleConstructorImpl implements TestBeanWithDoubleConstructor {

    private final Double testDouble;

    public TestBeanWithDoubleConstructorImpl(Double testDouble) {
        this.testDouble = testDouble;
    }

    @Override
    public Double getTestDouble() {
        return testDouble;
    }
}
