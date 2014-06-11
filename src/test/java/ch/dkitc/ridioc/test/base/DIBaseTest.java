package ch.dkitc.ridioc.test.base;

import static org.junit.Assert.assertNotNull;

import ch.dkitc.ridioc.DI;
import ch.dkitc.ridioc.DIObjectFactory;

public abstract class DIBaseTest {

    private final String packagePrefix;

    protected DIBaseTest(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    protected DIObjectFactory createObjectFactory() {
        DIObjectFactory objectFactory = DI.createObjectFactory(DIObjectFactory.class, packagePrefix);
        assertNotNull(objectFactory);
        return objectFactory;
    }

}
