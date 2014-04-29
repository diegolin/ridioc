package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveByteConstructor;

public class TestBeanWithPrimitiveByteConstructorImpl implements TestBeanWithPrimitiveByteConstructor {

    private final byte testByte;

    public TestBeanWithPrimitiveByteConstructorImpl(byte testByte) {
        this.testByte = testByte;
    }

    @Override
    public byte getTestByte() {
        return testByte;
    }
}
