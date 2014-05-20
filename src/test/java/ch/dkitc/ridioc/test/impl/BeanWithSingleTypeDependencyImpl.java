package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithDefaultConstructor;
import ch.dkitc.ridioc.test.api.BeanWithSingleTypeDependency;

public class BeanWithSingleTypeDependencyImpl implements BeanWithSingleTypeDependency {

    private final BeanWithDefaultConstructor beanWithDefaultConstructor;

    public BeanWithSingleTypeDependencyImpl(BeanWithDefaultConstructor beanWithDefaultConstructor) {
        this.beanWithDefaultConstructor = beanWithDefaultConstructor;
    }

    @Override
    public BeanWithDefaultConstructor getBeanWithDefaultConstructor() {
        return beanWithDefaultConstructor;
    }
}
