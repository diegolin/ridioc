package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.AnotherBeanWithSingleTypeDependency;
import ch.dkitc.ridioc.test.api.BeanWithDefaultConstructor;

public class AnotherBeanWithSingleTypeDependencyImpl implements AnotherBeanWithSingleTypeDependency {

    private final BeanWithDefaultConstructor beanWithDefaultConstructor;

    public AnotherBeanWithSingleTypeDependencyImpl(BeanWithDefaultConstructor beanWithDefaultConstructor) {
        this.beanWithDefaultConstructor = beanWithDefaultConstructor;
    }

    @Override
    public BeanWithDefaultConstructor getBeanWithDefaultConstructor() {
        return beanWithDefaultConstructor;
    }
}
