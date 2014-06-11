package ch.dkitc.ridioc.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.BeanButNoImpl;
import ch.dkitc.ridioc.test.api.BeanPrivateDefaultConstructor;
import ch.dkitc.ridioc.test.api.BeanWithMultipleImpls;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_instanceMethod_negativeTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_negativeTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_negativeTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void instanceNegativeTests_checkBeanButNoImplementation() {
        try {
            objectFactory.instance(BeanButNoImpl.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    @Test
    public void instanceNegativeTests_checkBeanWithMultipleImplementations() {
        try {
            objectFactory.instance(BeanWithMultipleImpls.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    @Test
    public void instanceNegativeTests_checkBeanWithPrivateDefaultConstructor() {
        try {
            objectFactory.instance(BeanPrivateDefaultConstructor.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }
}