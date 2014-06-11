package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangByteConstructor;

public class BeanWithJavaLangByteConstructorImpl implements BeanWithJavaLangByteConstructor {
    private final Byte testByte;

    public BeanWithJavaLangByteConstructorImpl(Byte testByte) {
        this.testByte = testByte;
    }

    @Override
    public Byte getTestByte() {
        return testByte;
    }
}
