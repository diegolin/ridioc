package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveIntegerConstructor;

public class TestBeanWithPrimitiveIntegerConstructorImpl implements TestBeanWithPrimitiveIntegerConstructor {

    private final int testInt;

    public TestBeanWithPrimitiveIntegerConstructorImpl(int testInt) {
        this.testInt = testInt;
    }

    @Override
    public int getTestInt() {
        return testInt;
    }
}
