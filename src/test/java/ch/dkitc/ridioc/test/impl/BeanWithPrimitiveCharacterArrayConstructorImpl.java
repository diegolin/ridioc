package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveCharacterArrayConstructor;

public class BeanWithPrimitiveCharacterArrayConstructorImpl implements BeanWithPrimitiveCharacterArrayConstructor {

    private final char[] testChars;

    public BeanWithPrimitiveCharacterArrayConstructorImpl(char[] testChars) {
        this.testChars = testChars;
    }

    @Override
    public char[] getTestChars() {
        return testChars;
    }
}
