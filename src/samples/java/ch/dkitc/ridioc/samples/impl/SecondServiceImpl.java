package ch.dkitc.ridioc.samples.impl;

import ch.dkitc.ridioc.samples.api.NanoService;
import ch.dkitc.ridioc.samples.api.SecondService;

public class SecondServiceImpl implements SecondService {

    private final NanoService nanoService;

    public SecondServiceImpl(NanoService nanoService) {
        this.nanoService = nanoService;
    }

    @Override
    public void executeSecondService() {
        System.out.println("+ 2nd service started!");
        nanoService.executeNanoService();
        System.out.println("+ 2nd service finished!");
    }
}
