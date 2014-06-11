package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveFloatArrayConstructor;

public class BeanWithPrimitiveFloatArrayConstructorImpl implements BeanWithPrimitiveFloatArrayConstructor {

    private final float[] testFloats;

    public BeanWithPrimitiveFloatArrayConstructorImpl(float[] testFloats) {
        this.testFloats = testFloats;
    }

    @Override
    public float[] getTestFloats() {
        return testFloats;
    }
}
