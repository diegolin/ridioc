package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithFloatConstructor;

public class BeanWithFloatConstructorImpl implements BeanWithFloatConstructor {

    private final Float testFloat;

    public BeanWithFloatConstructorImpl(Float testFloat) {
        this.testFloat = testFloat;
    }

    @Override
    public Float getTestFloat() {
        return testFloat;
    }
}
