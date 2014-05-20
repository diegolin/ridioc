package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.AnotherBeanWithDefaultConstructor;
import ch.dkitc.ridioc.test.api.BeanWithDefaultConstructor;
import ch.dkitc.ridioc.test.api.BeanWithMultipleTypesConstructor;

public class BeanWithMultipleTypesConstructorImpl implements BeanWithMultipleTypesConstructor {

    private final BeanWithDefaultConstructor beanWithDefaultConstructor;
    private final AnotherBeanWithDefaultConstructor anotherBeanWithDefaultConstructor;

    public BeanWithMultipleTypesConstructorImpl(BeanWithDefaultConstructor beanWithDefaultConstructor, AnotherBeanWithDefaultConstructor anotherBeanWithDefaultConstructor) {
        this.beanWithDefaultConstructor = beanWithDefaultConstructor;
        this.anotherBeanWithDefaultConstructor = anotherBeanWithDefaultConstructor;
    }

    @Override
    public BeanWithDefaultConstructor getBeanWithDefaultConstructor() {
        return beanWithDefaultConstructor;
    }

    @Override
    public AnotherBeanWithDefaultConstructor getAnotherBeanWithDefaultConstructor() {
        return anotherBeanWithDefaultConstructor;
    }
}
