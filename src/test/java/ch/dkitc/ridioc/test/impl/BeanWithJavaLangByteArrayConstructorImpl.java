package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangByteArrayConstructor;

public class BeanWithJavaLangByteArrayConstructorImpl implements BeanWithJavaLangByteArrayConstructor {

    private final Byte[] testBytes;

    public BeanWithJavaLangByteArrayConstructorImpl(Byte[] testBytes) {
        this.testBytes = testBytes;
    }

    @Override
    public Byte[] getTestBytes() {
        return testBytes;
    }
}
