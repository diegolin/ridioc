package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithDefaultConstructor;
import ch.dkitc.ridioc.test.api.BeanWithSingleTypeConstructor;

public class BeanWithSingleTypeConstructorImpl implements BeanWithSingleTypeConstructor {

    private final BeanWithDefaultConstructor beanWithDefaultConstructor;

    public BeanWithSingleTypeConstructorImpl(BeanWithDefaultConstructor beanWithDefaultConstructor) {
        this.beanWithDefaultConstructor = beanWithDefaultConstructor;
    }

    @Override
    public BeanWithDefaultConstructor getBeanWithDefaultConstructor() {
        return beanWithDefaultConstructor;
    }
}
