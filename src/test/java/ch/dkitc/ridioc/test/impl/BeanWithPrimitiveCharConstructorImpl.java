package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveCharConstructor;

public class BeanWithPrimitiveCharConstructorImpl implements BeanWithPrimitiveCharConstructor {

    private final char testChar;

    public BeanWithPrimitiveCharConstructorImpl(char testChar) {
        this.testChar = testChar;
    }

    @Override
    public char getTestChar() {
        return testChar;
    }

}
