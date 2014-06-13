package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangFloatConstructor;

public class BeanWithJavaLangFloatConstructorImpl implements BeanWithJavaLangFloatConstructor {

    private final Float testFloat;

    public BeanWithJavaLangFloatConstructorImpl(Float testFloat) {
        this.testFloat = testFloat;
    }

    @Override
    public Float getTestFloat() {
        return testFloat;
    }
}
