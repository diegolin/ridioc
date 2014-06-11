package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangIntegerArrayConstructor;

public class BeanWithJavaLangIntegerArrayConstructorImpl implements BeanWithJavaLangIntegerArrayConstructor {

    private final Integer[] testIntegers;

    public BeanWithJavaLangIntegerArrayConstructorImpl(Integer[] testIntegers) {
        this.testIntegers = testIntegers;
    }

    @Override
    public Integer[] getTestIntegers() {
        return testIntegers;
    }
}
