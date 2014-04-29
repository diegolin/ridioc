package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithIntegerConstructor;

public class TestBeanWithIntegerConstructorImpl implements TestBeanWithIntegerConstructor {

    private final Integer testInteger;

    public TestBeanWithIntegerConstructorImpl(Integer testInteger) {
        this.testInteger = testInteger;
    }

    @Override
    public Integer getTestInteger() {
        return testInteger;
    }
}
