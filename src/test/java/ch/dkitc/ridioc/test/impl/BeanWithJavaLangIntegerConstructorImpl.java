package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangIntegerConstructor;

public class BeanWithJavaLangIntegerConstructorImpl implements BeanWithJavaLangIntegerConstructor {

    private final Integer testInteger;

    public BeanWithJavaLangIntegerConstructorImpl(Integer testInteger) {
        this.testInteger = testInteger;
    }

    @Override
    public Integer getTestInteger() {
        return testInteger;
    }
}
