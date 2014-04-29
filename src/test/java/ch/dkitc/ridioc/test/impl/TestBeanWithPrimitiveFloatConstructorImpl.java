package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithPrimitiveFloatConstructor;

public class TestBeanWithPrimitiveFloatConstructorImpl implements TestBeanWithPrimitiveFloatConstructor {
    private final float testFloat;

    public TestBeanWithPrimitiveFloatConstructorImpl(float testFloat) {
        this.testFloat = testFloat;
    }

    @Override
    public float getTestFloat() {
        return testFloat;
    }
}
