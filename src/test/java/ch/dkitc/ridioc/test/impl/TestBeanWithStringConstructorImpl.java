package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithStringConstructor;

public class TestBeanWithStringConstructorImpl implements TestBeanWithStringConstructor {

    private final String testString;

    public TestBeanWithStringConstructorImpl(String testString) {
        this.testString = testString;
    }

    @Override
    public String getTestString() {
        return testString;
    }
}
