package ch.dkitc.ridioc.test;

import org.junit.Test;
import static org.junit.Assert.fail;

import ch.dkitc.ridioc.DI;
import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.EmptyInterface;
import ch.dkitc.ridioc.test.api.InterfaceWithFiveMethodsButNotAnObjectFactory;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DITest extends DIBaseTest {

    public DITest() {
    }

    @Test
    public void createFactoryPositiveTests() {
        DI.createObjectFactory(DIObjectFactory.class, PACKAGE_PREFIX);
    }

    @Test
    public void createFactoryNegativeTests() {
        try {
            DI.createObjectFactory(null, PACKAGE_PREFIX);
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            DI.createObjectFactory(Object.class, PACKAGE_PREFIX);
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            DI.createObjectFactory(EmptyInterface.class, PACKAGE_PREFIX);
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            DI.createObjectFactory(InterfaceWithFiveMethodsButNotAnObjectFactory.class, PACKAGE_PREFIX);
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            DI.createObjectFactory(DIObjectFactory.class, null);
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            DI.createObjectFactory(DIObjectFactory.class, "");
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }
}
