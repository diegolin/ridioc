package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithMultipleImpls;
import ch.dkitc.ridioc.test.api.BeanWithMultipleImplsListConstructor;

public class BeanWithMultipleImplsListConstructorImpl implements BeanWithMultipleImplsListConstructor {

    private final List<BeanWithMultipleImpls> beanWithMultipleImplsList;

    public BeanWithMultipleImplsListConstructorImpl(List<BeanWithMultipleImpls> beanWithMultipleImplsList) {
        this.beanWithMultipleImplsList = beanWithMultipleImplsList;
    }

    @Override
    public List<BeanWithMultipleImpls> getMultipleImplsList() {
        return beanWithMultipleImplsList;
    }
}
