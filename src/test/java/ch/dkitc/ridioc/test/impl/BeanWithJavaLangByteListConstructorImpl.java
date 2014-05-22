package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangByteListConstructor;

public class BeanWithJavaLangByteListConstructorImpl implements BeanWithJavaLangByteListConstructor {

    private final List<Byte> testBytes;

    public BeanWithJavaLangByteListConstructorImpl(List<Byte> testBytes) {
        this.testBytes = testBytes;
    }

    @Override
    public List<Byte> getTestBytes() {
        return testBytes;
    }
}
