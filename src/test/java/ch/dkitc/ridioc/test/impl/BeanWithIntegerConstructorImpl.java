package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithIntegerConstructor;

public class BeanWithIntegerConstructorImpl implements BeanWithIntegerConstructor {

    private final Integer testInteger;

    public BeanWithIntegerConstructorImpl(Integer testInteger) {
        this.testInteger = testInteger;
    }

    @Override
    public Integer getTestInteger() {
        return testInteger;
    }
}
