package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithStringArrayConstructor;

public class BeanWithStringArrayConstructorImpl implements BeanWithStringArrayConstructor {

    private final String[] testStrings;

    public BeanWithStringArrayConstructorImpl(String[] testStrings) {
        this.testStrings = testStrings;
    }

    @Override
    public String[] getTestStrings() {
        return testStrings;
    }
}
