package ch.dkitc.ridioc.samples.typefactory.impl;

import ch.dkitc.ridioc.samples.typefactory.api.Bean;
import ch.dkitc.ridioc.samples.typefactory.api.Service;

public class BeanImpl implements Bean {

    private final Service service;

    public BeanImpl(Service service1) {
        this.service = service1;
    }

    @Override
    public Service getService() {
        return service;
    }

}
