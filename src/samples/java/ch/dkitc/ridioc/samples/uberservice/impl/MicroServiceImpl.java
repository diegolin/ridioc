package ch.dkitc.ridioc.samples.uberservice.impl;

import ch.dkitc.ridioc.samples.uberservice.api.MicroService;
import ch.dkitc.ridioc.samples.uberservice.api.NanoService;

public class MicroServiceImpl implements MicroService {

    private final NanoService nanoService;

    public MicroServiceImpl(NanoService nanoService) {
        this.nanoService = nanoService;
    }

    @Override
    public void executeMicroService() {
        System.out.println("++ Micro service! (" + hashCode() + ") started!");
        nanoService.executeNanoService();
        System.out.println("++ Micro service! (" + hashCode() + ") finished!");
    }
}
