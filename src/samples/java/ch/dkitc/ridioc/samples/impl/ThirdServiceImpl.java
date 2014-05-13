package ch.dkitc.ridioc.samples.impl;

import ch.dkitc.ridioc.samples.api.PicoService;
import ch.dkitc.ridioc.samples.api.ThirdService;

public class ThirdServiceImpl implements ThirdService {

    private final PicoService picoService;

    public ThirdServiceImpl(PicoService picoService) {
        this.picoService = picoService;
    }

    @Override
    public void executeThirdService() {
        System.out.println("+ 3rd service started!");
        picoService.executePicoService();
        System.out.println("+ 3rd service finished!");
    }
}
