package ch.dkitc.ridioc.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_newInstanceMethod_negativeTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_newInstanceMethod_negativeTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_newInstanceMethod_negativeTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void checkBeanButNoImplementation() {
        try {
            objectFactory.newInstance(BeanButNoImpl.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    @Test
    public void newInstanceNegativeTests_checkBeanWithMultipleImplementations() {
        try {
            objectFactory.newInstance(BeanWithMultipleImpls.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    @Test
    public void checkBeanWithPrivateDefaultConstructor() {
        try {
            objectFactory.newInstance(BeanPrivateDefaultConstructor.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    @Test
    public void checkPrimitiveByte() {
        try {
            objectFactory.newInstance(BeanWithPrimitiveByteConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkJavaLangByte() {
        try {
            objectFactory.newInstance(BeanWithJavaLangByteConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkPrimitiveByteArray() {
        try {
            objectFactory.newInstance(BeanWithPrimitiveByteArrayConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkJavaLangByteArray() {
        try {
            objectFactory.newInstance(BeanWithJavaLangByteArrayConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkJavaLangByteList() {
        try {
            objectFactory.newInstance(BeanWithJavaLangByteListConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkPrimitiveShort() {
        try {
            objectFactory.newInstance(BeanWithPrimitiveShortConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkJavaLangShort() {
        try {
            objectFactory.newInstance(BeanWithJavaLangShortConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkPrimitiveShortArray() {
        try {
            objectFactory.newInstance(BeanWithPrimitiveShortArrayConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkJavaLangShortArray() {
        try {
            objectFactory.newInstance(BeanWithJavaLangShortArrayConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    @Test
    public void checkJavaLangShortList() {
        try {
            objectFactory.newInstance(BeanWithJavaLangShortListConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }
}
