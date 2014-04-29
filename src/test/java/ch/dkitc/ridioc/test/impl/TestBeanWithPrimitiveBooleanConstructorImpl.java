package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveBooleanConstructor;

public class TestBeanWithPrimitiveBooleanConstructorImpl implements TestBeanWithPrimitiveBooleanConstructor {

    private final boolean testBoolean;

    public TestBeanWithPrimitiveBooleanConstructorImpl(boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    @Override
    public boolean getTestBoolean() {
        return testBoolean;
    }
}
