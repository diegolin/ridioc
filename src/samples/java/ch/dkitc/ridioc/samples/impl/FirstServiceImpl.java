package ch.dkitc.ridioc.samples.impl;

import ch.dkitc.ridioc.samples.api.FirstService;
import ch.dkitc.ridioc.samples.api.MicroService;

public class FirstServiceImpl implements FirstService {

    private final MicroService microService;

    public FirstServiceImpl(MicroService microService) {
        this.microService = microService;
    }

    @Override
    public void executeFirstService() {
        System.out.println("+ 1st service started!");
        microService.executeMicroService();
        System.out.println("+ 1st service finished!");
    }
}
