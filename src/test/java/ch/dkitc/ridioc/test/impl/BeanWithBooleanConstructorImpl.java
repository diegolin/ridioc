package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithBooleanConstructor;

public class BeanWithBooleanConstructorImpl implements BeanWithBooleanConstructor {

    private final Boolean testBoolean;

    public BeanWithBooleanConstructorImpl(Boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    @Override
    public Boolean getTestBoolean() {
        return testBoolean;
    }
}
