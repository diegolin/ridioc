package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveIntegerConstructor;

public class BeanWithPrimitiveIntegerConstructorImpl implements BeanWithPrimitiveIntegerConstructor {

    private final int testInt;

    public BeanWithPrimitiveIntegerConstructorImpl(int testInt) {
        this.testInt = testInt;
    }

    @Override
    public int getTestInt() {
        return testInt;
    }
}
