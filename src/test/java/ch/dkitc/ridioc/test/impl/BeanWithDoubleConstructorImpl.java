package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithDoubleConstructor;

public class BeanWithDoubleConstructorImpl implements BeanWithDoubleConstructor {

    private final Double testDouble;

    public BeanWithDoubleConstructorImpl(Double testDouble) {
        this.testDouble = testDouble;
    }

    @Override
    public Double getTestDouble() {
        return testDouble;
    }
}
