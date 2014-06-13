package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangBooleanConstructor;

public class BeanWithJavaLangBooleanConstructorImpl implements BeanWithJavaLangBooleanConstructor {

    private final Boolean testBoolean;

    public BeanWithJavaLangBooleanConstructorImpl(Boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    @Override
    public Boolean getTestBoolean() {
        return testBoolean;
    }
}
