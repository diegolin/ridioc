package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangShortConstructor;

public class BeanWithJavaLangShortConstructorImpl implements BeanWithJavaLangShortConstructor {

    private final Short testShort;

    public BeanWithJavaLangShortConstructorImpl(Short testShort) {
        this.testShort = testShort;
    }

    @Override
    public Short getTestShort() {
        return testShort;
    }
}
