package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveShortConstructor;

public class TestBeanWithPrimitiveShortConstructorImpl implements TestBeanWithPrimitiveShortConstructor {
    private final short testShort;

    public TestBeanWithPrimitiveShortConstructorImpl(short testShort) {
        this.testShort = testShort;
    }

    @Override
    public short getTestShort() {
        return testShort;
    }
}
