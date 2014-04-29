package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithShortConstructor;

public class TestBeanWithShortConstructorImpl implements TestBeanWithShortConstructor {

    private final Short testShort;

    public TestBeanWithShortConstructorImpl(Short testShort) {
        this.testShort = testShort;
    }

    @Override
    public Short getTestShort() {
        return testShort;
    }
}
