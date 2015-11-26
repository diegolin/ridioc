package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangDoubleArrayConstructor;

public class BeanWithJavaLangDoubleArrayConstructorImpl implements BeanWithJavaLangDoubleArrayConstructor {

    private final Double[] testDoubles;

    public BeanWithJavaLangDoubleArrayConstructorImpl(Double[] testDoubles) {
        this.testDoubles = testDoubles;
    }

    @Override
    public Double[] getTestDoubles() {
        return testDoubles;
    }
}
