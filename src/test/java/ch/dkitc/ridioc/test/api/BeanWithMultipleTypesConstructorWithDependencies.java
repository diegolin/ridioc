package ch.dkitc.ridioc.test.api;

public interface BeanWithMultipleTypesConstructorWithDependencies {
    BeanWithSingleTypeDependency getBeanWithSingleTypeDependency();
    AnotherBeanWithSingleTypeDependency getAnotherBeanWithSingleTypeDependency();
}
