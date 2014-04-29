package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithBooleanConstructor;

public class TestBeanWithBooleanConstructorImpl implements TestBeanWithBooleanConstructor {

    private final Boolean testBoolean;

    public TestBeanWithBooleanConstructorImpl(Boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    @Override
    public Boolean getTestBoolean() {
        return testBoolean;
    }
}
