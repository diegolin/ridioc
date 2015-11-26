package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangDoubleListConstructor;

public class BeanWithJavaLangDoubleListConstructorImpl implements BeanWithJavaLangDoubleListConstructor {

    private final List<Double> testDoubles;

    public BeanWithJavaLangDoubleListConstructorImpl(List<Double> testDoubles) {
        this.testDoubles = testDoubles;
    }

    @Override
    public List<Double> getTestDoubles() {
        return testDoubles;
    }
}
