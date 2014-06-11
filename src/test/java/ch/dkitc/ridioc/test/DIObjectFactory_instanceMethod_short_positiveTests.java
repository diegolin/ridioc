package ch.dkitc.ridioc.test;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.DIUtils;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_instanceMethod_short_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_short_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_short_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void checkPrimitiveShort() {
        short testShort= Short.MAX_VALUE;
        objectFactory.registerStringLiteral("testShort", Short.toString(testShort));

        // BeanWithPrimitiveShortConstructor bean1
        BeanWithPrimitiveShortConstructor bean1 = objectFactory.instance(BeanWithPrimitiveShortConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestShort(), testShort);

        // BeanWithPrimitiveShortConstructor bean1
        BeanWithPrimitiveShortConstructor bean2 = objectFactory.instance(BeanWithPrimitiveShortConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestShort(), testShort);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestShort(), bean2.getTestShort());
    }

    @Test
    public void checkPrimitiveShortArray() {
        // register string literal array for shorts
        Short[] testShorts = new Short[]{Short.MAX_VALUE, -20, -10, -2 , -1, 0, 1, 2, 10, 20, Short.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testShorts", testShorts);

        // create BeanWithPrimitiveShortArrayConstructor bean1
        BeanWithPrimitiveShortArrayConstructor bean1 = objectFactory.instance(BeanWithPrimitiveShortArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShorts());
        assertArrayEquals((short[]) DIUtils.unboxToPrimitiveShortArray(testShorts), bean1.getTestShorts());

        // create BeanWithPrimitiveShortArrayConstructor bean2
        BeanWithPrimitiveShortArrayConstructor bean2 = objectFactory.instance(BeanWithPrimitiveShortArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShorts());
        assertArrayEquals((short[]) DIUtils.unboxToPrimitiveShortArray(testShorts), bean2.getTestShorts());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestShorts(), bean2.getTestShorts());
    }

    @Test
    public void checkJavaLangShort() {
        Short testShort = Short.MAX_VALUE;
        objectFactory.registerStringLiteral("testShort", testShort.toString());

        // BeanWithShortConstructor bean1
        BeanWithJavaLangShortConstructor bean1 = objectFactory.instance(BeanWithJavaLangShortConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShort());
        assertEquals(bean1.getTestShort(), testShort);

        // BeanWithShortConstructor bean2
        BeanWithJavaLangShortConstructor bean2 = objectFactory.instance(BeanWithJavaLangShortConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShort());
        assertEquals(bean2.getTestShort(), testShort);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestShort(), bean2.getTestShort());
    }

    @Test
    public void checkJavaLangShortArray() {
        // register string literal array for shorts
        Short[] testShorts = new Short[]{Short.MAX_VALUE, -1, 0, 1, Short.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testShorts", testShorts);

        // create BeanWithJavaLangShortArrayConstructor bean1
        BeanWithJavaLangShortArrayConstructor bean1 = objectFactory.instance(BeanWithJavaLangShortArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShorts());
        assertArrayEquals(testShorts, bean1.getTestShorts());

        // create BeanWithJavaLangByteArrayConstructor bean2
        BeanWithJavaLangShortArrayConstructor bean2 = objectFactory.instance(BeanWithJavaLangShortArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShorts());
        assertArrayEquals(testShorts, bean2.getTestShorts());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestShorts(), bean2.getTestShorts());
    }

    @Test
    public void checkJavaLangShortList() {
        // register value List<Short>
        Short[] testShorts = new Short[]{Short.MAX_VALUE, -1000, -100, -10, -1, 0, 1, 10, 100, 1000, Short.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testShorts", testShorts);

        // create BeanWithJavaLangByteListConstructor bean1
        BeanWithJavaLangShortListConstructor bean1 = objectFactory.instance(BeanWithJavaLangShortListConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShorts());
        assertEquals(Arrays.asList(testShorts), bean1.getTestShorts());
        assertFalse(testShorts == bean1.getTestShorts().toArray());

        // create BeanWithJavaLangByteListConstructor bean1
        BeanWithJavaLangShortListConstructor bean2 = objectFactory.instance(BeanWithJavaLangShortListConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShorts());
        assertEquals(Arrays.asList(testShorts), bean2.getTestShorts());
        assertFalse(testShorts == bean2.getTestShorts().toArray());

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestShorts(), bean2.getTestShorts());
        assertTrue(bean1.getTestShorts() == bean2.getTestShorts());
    }

}
