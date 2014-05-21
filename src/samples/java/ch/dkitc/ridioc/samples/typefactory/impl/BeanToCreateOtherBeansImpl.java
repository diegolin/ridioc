package ch.dkitc.ridioc.samples.typefactory.impl;

import java.util.List;
import java.util.Random;

import ch.dkitc.ridioc.DITypeFactory;
import ch.dkitc.ridioc.samples.typefactory.api.Bean;
import ch.dkitc.ridioc.samples.typefactory.api.BeanToCreateOtherBeans;
import ch.dkitc.ridioc.samples.typefactory.api.Service;

public class BeanToCreateOtherBeansImpl implements BeanToCreateOtherBeans {

    private final Random random;
    private final DITypeFactory<Bean> beanDITypeFactory;
    private final List<Service> serviceList;

    public BeanToCreateOtherBeansImpl(DITypeFactory<Bean> beanDITypeFactory, List<Service> serviceList) {
        this.random = new Random();
        this.beanDITypeFactory = beanDITypeFactory;
        this.serviceList = serviceList;
    }

    @Override
    public Bean createBean() {
        int nextIndex = random.nextInt(serviceList.size());
        return beanDITypeFactory.newInstance(serviceList.get(nextIndex));
    }
}
