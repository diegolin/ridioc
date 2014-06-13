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

public class DIObjectFactory_instanceMethod_long_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_long_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_long_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void checkPrimitiveLong() {
        long testLong = Long.MAX_VALUE;
        objectFactory.registerStringLiteral("testLong", Long.toString(testLong));

        // BeanWithPrimitiveLongConstructor bean1
        BeanWithPrimitiveLongConstructor bean1 = objectFactory.instance(BeanWithPrimitiveLongConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestLong(), testLong);

        // BeanWithPrimitiveLongConstructor bean2
        BeanWithPrimitiveLongConstructor bean2 = objectFactory.instance(BeanWithPrimitiveLongConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestLong(), testLong);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestLong(), bean2.getTestLong());
    }

    @Test
    public void checkPrimitiveLongArray() {
        // register string literal array for longs
        Long[] testLongs = new Long[]{Long.MIN_VALUE, -20l, -10l, -2l, -1l, 0l, 1l, 2l, 10l, 20l, Long.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testLongs", testLongs);

        // create BeanWithPrimitiveLongArrayConstructor bean1
        BeanWithPrimitiveLongArrayConstructor bean1 = objectFactory.instance(BeanWithPrimitiveLongArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestLongs());
        assertArrayEquals((long[]) DIUtils.unboxToPrimitiveLongArray(testLongs), bean1.getTestLongs());

        // create BeanWithPrimitiveShortArrayConstructor bean2
        BeanWithPrimitiveLongArrayConstructor bean2 = objectFactory.instance(BeanWithPrimitiveLongArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestLongs());
        assertArrayEquals((long[]) DIUtils.unboxToPrimitiveLongArray(testLongs), bean2.getTestLongs());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestLongs(), bean2.getTestLongs());
    }

    @Test
    public void checkJavaLangLong() {
        Long testLong = Long.MAX_VALUE;
        objectFactory.registerStringLiteral("testLong", testLong.toString());

        // BeanWithLongConstructor bean1
        BeanWithLongConstructor bean1 = objectFactory.instance(BeanWithLongConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestLong());
        assertEquals(bean1.getTestLong(), testLong);

        // BeanWithLongConstructor bean2
        BeanWithLongConstructor bean2 = objectFactory.instance(BeanWithLongConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestLong());
        assertEquals(bean2.getTestLong(), testLong);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestLong(), bean2.getTestLong());
    }

    @Test
    public void checkJavaLangLongArray() {
        // register string literal array for floats
        Long[] testLongs = new Long[]{Long.MIN_VALUE, -20l, -10l, -2l, -1l, 0l, 1l, 2l, 10l, 20l, Long.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testLongs", testLongs);

        // create BeanWithJavaLangLongArrayConstructor bean1
        BeanWithJavaLangLongArrayConstructor bean1 = objectFactory.instance(BeanWithJavaLangLongArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestLongs());
        assertArrayEquals(testLongs, bean1.getTestLongs());

        // create BeanWithJavaLangLongArrayConstructor bean2
        BeanWithJavaLangLongArrayConstructor bean2 = objectFactory.instance(BeanWithJavaLangLongArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestLongs());
        assertArrayEquals(testLongs, bean2.getTestLongs());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestLongs(), bean2.getTestLongs());
    }

    @Test
    public void checkJavaLangLongList() {
        // register value List<Long>
        Long[] testLongs = new Long[]{Long.MIN_VALUE, -1000l, -100l, -10l, -1l, 0l, 1l, 10l, 100l, 1000l, Long.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testLongs", testLongs);

        // create BeanWithJavaLangLongListConstructor bean1
        BeanWithJavaLangLongListConstructor bean1 = objectFactory.instance(BeanWithJavaLangLongListConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestLongs());
        assertEquals(Arrays.asList(testLongs), bean1.getTestLongs());
        assertFalse(testLongs == bean1.getTestLongs().toArray());

        // create BeanWithJavaLangIntegerListConstructor bean1
        BeanWithJavaLangLongListConstructor bean2 = objectFactory.instance(BeanWithJavaLangLongListConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestLongs());
        assertEquals(Arrays.asList(testLongs), bean2.getTestLongs());
        assertFalse(testLongs == bean2.getTestLongs().toArray());

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestLongs(), bean2.getTestLongs());
        assertTrue(bean1.getTestLongs() == bean2.getTestLongs());
    }
}
