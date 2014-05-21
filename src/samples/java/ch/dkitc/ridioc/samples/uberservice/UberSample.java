package ch.dkitc.ridioc.samples.uberservice;

import ch.dkitc.ridioc.DI;
import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.samples.uberservice.api.UberService;

public class UberSample {

    public static final String PACKAGE_PREFIX = UberSample.class.getPackage().getName();

    public static void main(String[] args) {

        DIObjectFactory objectFactory = DI.createObjectFactory(DIObjectFactory.class, PACKAGE_PREFIX);

        UberService uberService = objectFactory.instance(UberService.class);

        uberService.executeUberService();
    }

}
