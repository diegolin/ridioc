package ch.dkitc.ridioc.samples.impl;

import ch.dkitc.ridioc.samples.api.FirstService;
import ch.dkitc.ridioc.samples.api.SecondService;
import ch.dkitc.ridioc.samples.api.ThirdService;
import ch.dkitc.ridioc.samples.api.UberService;

public class UberServiceImpl implements UberService {

    private final FirstService firstService;
    private final SecondService secondService;
    private final ThirdService thirdService;

    public UberServiceImpl(FirstService firstService, SecondService secondService, ThirdService thirdService) {
        this.firstService = firstService;
        this.secondService = secondService;
        this.thirdService = thirdService;
    }

    @Override
    public void executeUberService() {
        System.out.println("UBER service started!");
        firstService.executeFirstService();
        secondService.executeSecondService();
        thirdService.executeThirdService();
        System.out.println("UEBER service finished!");
    }
}
