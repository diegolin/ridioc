package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithByteConstructor;

public class TestBeanWithByteConstructorImpl implements TestBeanWithByteConstructor {
    private final Byte testByte;

    public TestBeanWithByteConstructorImpl(Byte testByte) {
        this.testByte = testByte;
    }

    @Override
    public Byte getTestByte() {
        return testByte;
    }
}
