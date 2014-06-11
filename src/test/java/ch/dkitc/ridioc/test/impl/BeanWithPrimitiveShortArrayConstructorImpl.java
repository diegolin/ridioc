package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveShortArrayConstructor;

public class BeanWithPrimitiveShortArrayConstructorImpl implements BeanWithPrimitiveShortArrayConstructor {

    private final short[] testShorts;

    public BeanWithPrimitiveShortArrayConstructorImpl(short[] testShorts) {
        this.testShorts = testShorts;
    }

    @Override
    public short[] getTestShorts() {
        return testShorts;
    }
}
