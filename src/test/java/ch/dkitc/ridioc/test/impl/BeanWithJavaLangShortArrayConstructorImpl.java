package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangShortArrayConstructor;

public class BeanWithJavaLangShortArrayConstructorImpl implements BeanWithJavaLangShortArrayConstructor {

    private final Short[] testShorts;

    public BeanWithJavaLangShortArrayConstructorImpl(Short[] testShorts) {
        this.testShorts = testShorts;
    }

    @Override
    public Short[] getTestShorts() {
        return testShorts;
    }
}
