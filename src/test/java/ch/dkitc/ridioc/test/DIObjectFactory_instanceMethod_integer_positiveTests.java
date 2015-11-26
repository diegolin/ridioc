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

public class DIObjectFactory_instanceMethod_integer_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_integer_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_integer_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void checkPrimitiveInteger() {
        int testInt = Integer.MAX_VALUE;
        objectFactory.registerInstance(int.class, testInt);

        // BeanWithPrimitiveIntegerConstructor bean1
        BeanWithPrimitiveIntegerConstructor bean1 = objectFactory.instance(BeanWithPrimitiveIntegerConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInt(), testInt);

        // BeanWithPrimitiveIntegerConstructor bean2
        BeanWithPrimitiveIntegerConstructor bean2 = objectFactory.instance(BeanWithPrimitiveIntegerConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInt(), testInt);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestInt(), bean2.getTestInt());
        assertTrue(bean1.getTestInt() == bean2.getTestInt());
    }

    @Test
    public void checkPrimitiveIntegerArray() {
        // register string literal array for ints
        Integer[] testInts = new Integer[]{Integer.MIN_VALUE, -20, -10, -2 , -1, 0, 1, 2, 10, 20, Integer.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testInts", testInts);

        // create BeanWithPrimitiveShortArrayConstructor bean1
        BeanWithPrimitiveIntegerArrayConstructor bean1 = objectFactory.instance(BeanWithPrimitiveIntegerArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestInts());
        assertArrayEquals((int[]) DIUtils.unboxToPrimitiveIntegerArray(testInts), bean1.getTestInts());

        // create BeanWithPrimitiveShortArrayConstructor bean2
        BeanWithPrimitiveIntegerArrayConstructor bean2 = objectFactory.instance(BeanWithPrimitiveIntegerArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestInts());
        assertArrayEquals((int[]) DIUtils.unboxToPrimitiveIntegerArray(testInts), bean2.getTestInts());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestInts(), bean2.getTestInts());
    }

    @Test
    public void checkJavaLangInteger() {
        Integer testInteger = Integer.MAX_VALUE;
        objectFactory.registerInstance(Integer.class, testInteger);

        // BeanWithIntegerConstructor bean1
        BeanWithJavaLangIntegerConstructor bean1 = objectFactory.instance(BeanWithJavaLangIntegerConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInteger(), testInteger);

        // BeanWithIntegerConstructor bean2
        BeanWithJavaLangIntegerConstructor bean2 = objectFactory.instance(BeanWithJavaLangIntegerConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInteger(), testInteger);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestInteger(), bean2.getTestInteger());
        assertTrue(bean1.getTestInteger() == bean2.getTestInteger());
    }

    @Test
    public void checkJavaLangIntegerArray() {
        // register string literal array for shorts
        Integer[] testIntegers = new Integer[]{Integer.MAX_VALUE, -10000, -1000, -100, -10, -1, 0, 1, 10, 100, 1000, 10000, Integer.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testIntegers", testIntegers);

        // create BeanWithJavaLangIntegerArrayConstructor bean1
        BeanWithJavaLangIntegerArrayConstructor bean1 = objectFactory.instance(BeanWithJavaLangIntegerArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestIntegers());
        assertArrayEquals(testIntegers, bean1.getTestIntegers());

        // create BeanWithJavaLangIntegerArrayConstructor bean2
        BeanWithJavaLangIntegerArrayConstructor bean2 = objectFactory.instance(BeanWithJavaLangIntegerArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestIntegers());
        assertArrayEquals(testIntegers, bean2.getTestIntegers());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestIntegers(), bean2.getTestIntegers());
    }

    @Test
    public void checkJavaLangIntegerList() {
        // register value List<Integer>
        Integer[] testIntegers = new Integer[]{Integer.MIN_VALUE, -1000, -100, -10, -1, 0, 1, 10, 100, 1000, Integer.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testIntegers", testIntegers);

        // create BeanWithJavaLangIntegerListConstructor bean1
        BeanWithJavaLangIntegerListConstructor bean1 = objectFactory.instance(BeanWithJavaLangIntegerListConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestIntegers());
        assertEquals(Arrays.asList(testIntegers), bean1.getTestIntegers());
        assertFalse(testIntegers == bean1.getTestIntegers().toArray());

        // create BeanWithJavaLangIntegerListConstructor bean1
        BeanWithJavaLangIntegerListConstructor bean2 = objectFactory.instance(BeanWithJavaLangIntegerListConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestIntegers());
        assertEquals(Arrays.asList(testIntegers), bean2.getTestIntegers());
        assertFalse(testIntegers == bean2.getTestIntegers().toArray());

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestIntegers(), bean2.getTestIntegers());
        assertTrue(bean1.getTestIntegers() == bean2.getTestIntegers());
    }

}
