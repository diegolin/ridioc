package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.AnotherBeanWithSingleTypeDependency;
import ch.dkitc.ridioc.test.api.BeanWithMultipleTypesConstructorWithDependencies;
import ch.dkitc.ridioc.test.api.BeanWithSingleTypeDependency;

public class BeanWithMultipleTypesConstructorWithDependenciesImpl implements BeanWithMultipleTypesConstructorWithDependencies {

    private final BeanWithSingleTypeDependency beanWithSingleTypeDependency;
    private final AnotherBeanWithSingleTypeDependency anotherBeanWithSingleTypeDependency;

    public BeanWithMultipleTypesConstructorWithDependenciesImpl(BeanWithSingleTypeDependency beanWithSingleTypeDependency, AnotherBeanWithSingleTypeDependency anotherBeanWithSingleTypeDependency) {
        this.beanWithSingleTypeDependency = beanWithSingleTypeDependency;
        this.anotherBeanWithSingleTypeDependency = anotherBeanWithSingleTypeDependency;
    }

    @Override
    public BeanWithSingleTypeDependency getBeanWithSingleTypeDependency() {
        return beanWithSingleTypeDependency;
    }

    @Override
    public AnotherBeanWithSingleTypeDependency getAnotherBeanWithSingleTypeDependency() {
        return anotherBeanWithSingleTypeDependency;
    }
}
