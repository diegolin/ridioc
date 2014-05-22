package ch.dkitc.ridioc.test.base;

import static org.junit.Assert.assertNotNull;

import ch.dkitc.ridioc.DI;
import ch.dkitc.ridioc.DIObjectFactory;

public abstract class DIBaseTest {

    public static final String PACKAGE_PREFIX = DIBaseTest.class.getPackage().getName();

    protected DIBaseTest() {
    }

    protected static DIObjectFactory createObjectFactory() {
        DIObjectFactory objectFactory = DI.createObjectFactory(DIObjectFactory.class, PACKAGE_PREFIX);
        assertNotNull(objectFactory);
        return objectFactory;
    }

}
