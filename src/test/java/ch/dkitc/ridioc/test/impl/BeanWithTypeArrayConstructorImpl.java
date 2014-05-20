package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithMultipleImpls;
import ch.dkitc.ridioc.test.api.BeanWithTypeArrayConstructor;

public class BeanWithTypeArrayConstructorImpl implements BeanWithTypeArrayConstructor {

    private final BeanWithMultipleImpls [] beanWithMultipleImplsArray;

    public BeanWithTypeArrayConstructorImpl(BeanWithMultipleImpls [] beanWithMultipleImplsArray) {
        this.beanWithMultipleImplsArray = beanWithMultipleImplsArray;
    }

    @Override
    public BeanWithMultipleImpls [] getMultipleImps() {
        return this.beanWithMultipleImplsArray;
    }
}
