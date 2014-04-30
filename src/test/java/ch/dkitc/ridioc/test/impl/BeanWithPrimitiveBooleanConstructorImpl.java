package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveBooleanConstructor;

public class BeanWithPrimitiveBooleanConstructorImpl implements BeanWithPrimitiveBooleanConstructor {

    private final boolean testBoolean;

    public BeanWithPrimitiveBooleanConstructorImpl(boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    @Override
    public boolean getTestBoolean() {
        return testBoolean;
    }
}
