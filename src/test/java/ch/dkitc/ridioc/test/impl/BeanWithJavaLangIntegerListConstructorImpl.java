package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangIntegerListConstructor;

public class BeanWithJavaLangIntegerListConstructorImpl implements BeanWithJavaLangIntegerListConstructor {

    private final List<Integer> testIntegers;

    public BeanWithJavaLangIntegerListConstructorImpl(List<Integer> testIntegers) {
        this.testIntegers = testIntegers;
    }

    @Override
    public List<Integer> getTestIntegers() {
        return testIntegers;
    }
}
