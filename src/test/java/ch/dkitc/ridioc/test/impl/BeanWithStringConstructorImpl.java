package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithStringConstructor;

public class BeanWithStringConstructorImpl implements BeanWithStringConstructor {

    private final String testString;

    public BeanWithStringConstructorImpl(String testString) {
        this.testString = testString;
    }

    @Override
    public String getTestString() {
        return testString;
    }
}
