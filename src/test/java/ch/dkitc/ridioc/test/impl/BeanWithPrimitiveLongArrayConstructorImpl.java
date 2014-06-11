package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveLongArrayConstructor;

public class BeanWithPrimitiveLongArrayConstructorImpl implements BeanWithPrimitiveLongArrayConstructor {

    private final long[] testLongs;

    public BeanWithPrimitiveLongArrayConstructorImpl(long[] testLongs) {
        this.testLongs = testLongs;
    }

    @Override
    public long[] getTestLongs() {
        return testLongs;
    }
}
