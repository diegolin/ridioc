package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithMultipleImpls;
import ch.dkitc.ridioc.test.api.BeanWithMultipleImplsArrayConstructor;

public class BeanWithMultipleImplsArrayConstructorImpl implements BeanWithMultipleImplsArrayConstructor {

    private final BeanWithMultipleImpls [] beanWithMultipleImplsArray;

    public BeanWithMultipleImplsArrayConstructorImpl(BeanWithMultipleImpls[] beanWithMultipleImplsArray) {
        this.beanWithMultipleImplsArray = beanWithMultipleImplsArray;
    }

    @Override
    public BeanWithMultipleImpls [] getMultipleImplsArray() {
        return this.beanWithMultipleImplsArray;
    }
}
