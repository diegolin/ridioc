package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveByteConstructor;

public class BeanWithPrimitiveByteConstructorImpl implements BeanWithPrimitiveByteConstructor {

    private final byte testByte;

    public BeanWithPrimitiveByteConstructorImpl(byte testByte) {
        this.testByte = testByte;
    }

    @Override
    public byte getTestByte() {
        return testByte;
    }
}
