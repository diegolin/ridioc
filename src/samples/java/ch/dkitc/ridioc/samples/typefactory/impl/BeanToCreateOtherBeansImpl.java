package ch.dkitc.ridioc.samples.typefactory.impl;

import java.util.List;
import java.util.Random;

import ch.dkitc.ridioc.samples.typefactory.api.Bean;
import ch.dkitc.ridioc.samples.typefactory.api.BeanFactory;
import ch.dkitc.ridioc.samples.typefactory.api.BeanToCreateOtherBeans;
import ch.dkitc.ridioc.samples.typefactory.api.Service;

public class BeanToCreateOtherBeansImpl implements BeanToCreateOtherBeans {

    private final Random random;
    private final BeanFactory beanFactory;
    private final List<Service> serviceList;

    public BeanToCreateOtherBeansImpl(BeanFactory beanFactory, List<Service> serviceList) {
        this.random = new Random();
        this.beanFactory = beanFactory;
        this.serviceList = serviceList;
    }

    @Override
    public Bean createBean() {
        int nextIndex = random.nextInt(serviceList.size());
        return beanFactory.createBean(serviceList.get(nextIndex));
    }
}
