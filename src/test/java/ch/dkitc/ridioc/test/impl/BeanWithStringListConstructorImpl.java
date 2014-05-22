package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithStringListConstructor;

public class BeanWithStringListConstructorImpl implements BeanWithStringListConstructor {
    private final List<String> testStrings;

    public BeanWithStringListConstructorImpl(List<String> testStrings) {
        this.testStrings = testStrings;
    }

    @Override
    public List<String> getTestStrings() {
        return testStrings;
    }
}
