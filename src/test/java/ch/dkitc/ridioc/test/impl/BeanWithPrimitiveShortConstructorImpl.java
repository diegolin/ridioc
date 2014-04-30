package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveShortConstructor;

public class BeanWithPrimitiveShortConstructorImpl implements BeanWithPrimitiveShortConstructor {
    private final short testShort;

    public BeanWithPrimitiveShortConstructorImpl(short testShort) {
        this.testShort = testShort;
    }

    @Override
    public short getTestShort() {
        return testShort;
    }
}
