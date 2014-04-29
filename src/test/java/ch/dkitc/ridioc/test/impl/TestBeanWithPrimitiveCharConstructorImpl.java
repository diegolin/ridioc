package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveCharConstructor;

public class TestBeanWithPrimitiveCharConstructorImpl implements TestBeanWithPrimitiveCharConstructor {

    private final char testChar;

    public TestBeanWithPrimitiveCharConstructorImpl(char testChar) {
        this.testChar = testChar;
    }

    @Override
    public char getTestChar() {
        return testChar;
    }

}
