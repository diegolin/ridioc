package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithStringArrayConstructor;

public class TestBeanWithStringArrayConstructorImpl implements TestBeanWithStringArrayConstructor {

    private final String[] testStrings;

    public TestBeanWithStringArrayConstructorImpl(String[] testStrings) {
        this.testStrings = testStrings;
    }

    @Override
    public String[] getTestStrings() {
        return testStrings;
    }
}
