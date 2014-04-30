package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithByteConstructor;

public class BeanWithByteConstructorImpl implements BeanWithByteConstructor {
    private final Byte testByte;

    public BeanWithByteConstructorImpl(Byte testByte) {
        this.testByte = testByte;
    }

    @Override
    public Byte getTestByte() {
        return testByte;
    }
}
