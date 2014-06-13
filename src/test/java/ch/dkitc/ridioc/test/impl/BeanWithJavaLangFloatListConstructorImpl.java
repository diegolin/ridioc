package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangFloatListConstructor;

public class BeanWithJavaLangFloatListConstructorImpl implements BeanWithJavaLangFloatListConstructor {

    private final List<Float> testFloats;

    public BeanWithJavaLangFloatListConstructorImpl(List<Float> testFloats) {
        this.testFloats = testFloats;
    }

    @Override
    public List<Float> getTestFloats() {
        return testFloats;
    }
}
