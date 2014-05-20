package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithSingleTypeConstructorWithDependencies;
import ch.dkitc.ridioc.test.api.BeanWithSingleTypeDependency;

public class BeanWithSingleTypeConstructorWithDependenciesImpl implements BeanWithSingleTypeConstructorWithDependencies {

    private final BeanWithSingleTypeDependency beanWithSingleTypeDependency;

    public BeanWithSingleTypeConstructorWithDependenciesImpl(BeanWithSingleTypeDependency beanWithSingleTypeDependency) {
        this.beanWithSingleTypeDependency = beanWithSingleTypeDependency;
    }

    @Override
    public BeanWithSingleTypeDependency getBeanWithSingleTypeDependency() {
        return beanWithSingleTypeDependency;
    }
}
