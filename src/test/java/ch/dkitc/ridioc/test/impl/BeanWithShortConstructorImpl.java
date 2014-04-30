package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithShortConstructor;

public class BeanWithShortConstructorImpl implements BeanWithShortConstructor {

    private final Short testShort;

    public BeanWithShortConstructorImpl(Short testShort) {
        this.testShort = testShort;
    }

    @Override
    public Short getTestShort() {
        return testShort;
    }
}
