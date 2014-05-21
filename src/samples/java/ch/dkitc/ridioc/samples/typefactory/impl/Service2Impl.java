package ch.dkitc.ridioc.samples.typefactory.impl;

import ch.dkitc.ridioc.samples.typefactory.api.Service;

public class Service2Impl implements Service {

    public Service2Impl() {
    }

    @Override
    public void executeService() {
        System.out.println("Service 2 is being executed!");
    }
}
