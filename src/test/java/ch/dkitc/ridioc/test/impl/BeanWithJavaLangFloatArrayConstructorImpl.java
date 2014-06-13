package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangFloatArrayConstructor;

public class BeanWithJavaLangFloatArrayConstructorImpl implements BeanWithJavaLangFloatArrayConstructor {

    private final Float[] testFloats;

    public BeanWithJavaLangFloatArrayConstructorImpl(Float[] testFloats) {
        this.testFloats = testFloats;
    }

    @Override
    public Float[] getTestFloats() {
        return testFloats;
    }
}
