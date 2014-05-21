package ch.dkitc.ridioc.samples.typefactory.impl;

import ch.dkitc.ridioc.samples.typefactory.api.Service;

public class Service1Impl implements Service {

    public Service1Impl() {
    }

    @Override
    public void executeService() {
        System.out.println("Service 1 is being executed!");
    }
}
