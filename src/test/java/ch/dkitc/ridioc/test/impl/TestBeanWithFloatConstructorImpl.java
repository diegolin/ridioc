package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithFloatConstructor;

public class TestBeanWithFloatConstructorImpl implements TestBeanWithFloatConstructor {

    private final Float testFloat;

    public TestBeanWithFloatConstructorImpl(Float testFloat) {
        this.testFloat = testFloat;
    }

    @Override
    public Float getTestFloat() {
        return testFloat;
    }
}
