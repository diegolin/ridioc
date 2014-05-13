package ch.dkitc.ridioc.samples.impl;

import ch.dkitc.ridioc.samples.api.PicoService;

public class PicoServiceImpl implements PicoService {

    public PicoServiceImpl() {
    }

    @Override
    public void executePicoService() {
        System.out.println("++++ Pico service! (" + hashCode() + ") started!");
        System.out.println("++++ Pico service! (" + hashCode() + ") finished!");
    }
}
