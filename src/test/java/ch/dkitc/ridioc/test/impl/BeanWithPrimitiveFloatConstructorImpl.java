package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithPrimitiveFloatConstructor;

public class BeanWithPrimitiveFloatConstructorImpl implements BeanWithPrimitiveFloatConstructor {
    private final float testFloat;

    public BeanWithPrimitiveFloatConstructorImpl(float testFloat) {
        this.testFloat = testFloat;
    }

    @Override
    public float getTestFloat() {
        return testFloat;
    }
}
