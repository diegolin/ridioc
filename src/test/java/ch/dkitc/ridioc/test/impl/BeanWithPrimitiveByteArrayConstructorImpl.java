package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveByteArrayConstructor;

public class BeanWithPrimitiveByteArrayConstructorImpl implements BeanWithPrimitiveByteArrayConstructor {

    private final byte[] testBytes;

    public BeanWithPrimitiveByteArrayConstructorImpl(byte[] testBytes) {
        this.testBytes = testBytes;
    }

    @Override
    public byte[] getTestBytes() {
        return testBytes;
    }
}
