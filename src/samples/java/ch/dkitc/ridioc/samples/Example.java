package ch.dkitc.ridioc.samples;

import ch.dkitc.ridioc.DI;
import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.samples.api.UberService;

public class Example {

    public static final String PACKAGE_PREFIX = Example.class.getPackage().getName();

    public static void main(String[] args) {
        DIObjectFactory objectFactory = DI.createObjectFactory(DIObjectFactory.class, PACKAGE_PREFIX);
        UberService uberService = objectFactory.instance(UberService.class);
        uberService.executeUberService();
    }

}
