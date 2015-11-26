package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangDoubleConstructor;

public class BeanWithJavaLangDoubleConstructorImpl implements BeanWithJavaLangDoubleConstructor {

    private final Double testDouble;

    public BeanWithJavaLangDoubleConstructorImpl(Double testDouble) {
        this.testDouble = testDouble;
    }

    @Override
    public Double getTestDouble() {
        return testDouble;
    }
}
