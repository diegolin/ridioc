package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangShortListConstructor;

public class BeanWithJavaLangShortListConstructorImpl implements BeanWithJavaLangShortListConstructor {

    private final List<Short> testShorts;

    public BeanWithJavaLangShortListConstructorImpl(List<Short> testShorts) {
        this.testShorts = testShorts;
    }

    @Override
    public List<Short> getTestShorts() {
        return testShorts;
    }
}
