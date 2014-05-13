package ch.dkitc.ridioc.samples.uberservice.impl;

import ch.dkitc.ridioc.samples.uberservice.api.NanoService;
import ch.dkitc.ridioc.samples.uberservice.api.PicoService;

public class NanoServiceImpl implements NanoService {

    private final PicoService picoService;

    public NanoServiceImpl(PicoService picoService) {
        this.picoService = picoService;
    }

    @Override
    public void executeNanoService() {
        System.out.println("+++ Nano service! (" + hashCode() + ") started!");
        picoService.executePicoService();
        System.out.println("+++ Nano service! (" + hashCode() + ") finished!");
    }
}
