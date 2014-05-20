package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.AnotherBeanWithTypeArrayConstructor;
import ch.dkitc.ridioc.test.api.BeanWithMultipleImpls;

public class AnotherBeanWithTypeArrayConstructorImpl implements AnotherBeanWithTypeArrayConstructor {

    private final BeanWithMultipleImpls[] getMultipleImps;

    public AnotherBeanWithTypeArrayConstructorImpl(BeanWithMultipleImpls[] getMultipleImps) {
        this.getMultipleImps = getMultipleImps;
    }

    @Override
    public BeanWithMultipleImpls[] getMultipleImps() {
        return getMultipleImps;
    }
}
