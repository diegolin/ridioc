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

public class DIObjectFactory_instanceMethod_double_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_double_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_double_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void checkPrimitiveDouble() {
        double testDouble = Double.MAX_VALUE;
        objectFactory.registerStringLiteral("testDouble", Double.toString(testDouble));

        // BeanWithPrimitiveDoubleConstructor bean1
        BeanWithPrimitiveDoubleConstructor bean1 = objectFactory.instance(BeanWithPrimitiveDoubleConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestDouble(), testDouble, 0.0);

        // BeanWithPrimitiveDoubleConstructor bean2
        BeanWithPrimitiveDoubleConstructor bean2 = objectFactory.instance(BeanWithPrimitiveDoubleConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestDouble(), testDouble, 0.0);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestDouble(), bean2.getTestDouble(), 0.0);
    }

    @Test
    public void checkPrimitiveDoubleArray() {
        // register string literal array for double
        Double[] testDoubles = new Double[]{Double.MAX_VALUE, -200.0d, -20.0d, -10.0d, -1.0d, -0.001d, 0.0d, 0.001d, 1.0d, 2.0d, 10.0d, 20.0d, 200.0d, Double.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testDoubles", testDoubles);

        // create BeanWithPrimitiveDoubleArrayConstructor bean1
        BeanWithPrimitiveDoubleArrayConstructor bean1 = objectFactory.instance(BeanWithPrimitiveDoubleArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDoubles());
        assertArrayEquals((double[]) DIUtils.unboxToPrimitiveDoubleArray(testDoubles), bean1.getTestDoubles(), /* delta */ 0.0f);

        // create BeanWithPrimitiveDoubleArrayConstructor bean2
        BeanWithPrimitiveDoubleArrayConstructor bean2 = objectFactory.instance(BeanWithPrimitiveDoubleArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDoubles());
        assertArrayEquals((double[]) DIUtils.unboxToPrimitiveDoubleArray(testDoubles), bean2.getTestDoubles(), /* delta */ 0.0f);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestDoubles(), bean2.getTestDoubles(), /* delta */ 0.0f);
    }

    @Test
    public void checkJavaLangDouble() {
        Double testDouble = Double.MAX_VALUE;
        objectFactory.registerStringLiteral("testDouble", testDouble.toString());

        // BeanWithDoubleConstructor bean1
        BeanWithJavaLangDoubleConstructor bean1 = objectFactory.instance(BeanWithJavaLangDoubleConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDouble());
        assertEquals(bean1.getTestDouble(), testDouble, 0.0);

        // BeanWithFloatConstructor bean2
        BeanWithJavaLangDoubleConstructor bean2 = objectFactory.instance(BeanWithJavaLangDoubleConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDouble());
        assertEquals(bean2.getTestDouble(), testDouble, 0.0);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestDouble(), bean2.getTestDouble(), 0.0);
    }

    @Test
    public void checkJavaLangDoubleArray() {
        // register string literal array for doubles
        Double[] testDoubles = new Double[]{Double.MAX_VALUE, -200.0d, -20.0d, -10.0d, -1.0d, -0.001d, 0.0d, 0.001d, 1.0d, 2.0d, 10.0d, 20.0d, 200.0d, Double.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testDoubles", testDoubles);

        // create BeanWithJavaLangDoubleArrayConstructor bean1
        BeanWithJavaLangDoubleArrayConstructor bean1 = objectFactory.instance(BeanWithJavaLangDoubleArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDoubles());
        assertArrayEquals(testDoubles, bean1.getTestDoubles());

        // create BeanWithJavaLangLongFloatConstructor bean2
        BeanWithJavaLangDoubleArrayConstructor bean2 = objectFactory.instance(BeanWithJavaLangDoubleArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDoubles());
        assertArrayEquals(testDoubles, bean2.getTestDoubles());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestDoubles(), bean2.getTestDoubles());
    }

    @Test
    public void checkJavaLangDoubleList() {
        // register string literal array for doubles
        Double[] testDoubles = new Double[]{Double.MAX_VALUE, -200.0d, -20.0d, -10.0d, -1.0d, -0.001d, 0.0d, 0.001d, 1.0d, 2.0d, 10.0d, 20.0d, 200.0d, Double.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testDoubles", testDoubles);

        // create BeanWithJavaLangFloatListConstructor bean1
        BeanWithJavaLangDoubleListConstructor bean1 = objectFactory.instance(BeanWithJavaLangDoubleListConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDoubles());
        assertEquals(Arrays.asList(testDoubles), bean1.getTestDoubles());
        assertFalse(testDoubles == bean1.getTestDoubles().toArray());

        // create BeanWithJavaLangIntegerListConstructor bean1
        BeanWithJavaLangDoubleListConstructor bean2 = objectFactory.instance(BeanWithJavaLangDoubleListConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDoubles());
        assertEquals(Arrays.asList(testDoubles), bean2.getTestDoubles());
        assertFalse(testDoubles == bean2.getTestDoubles().toArray());

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestDoubles(), bean2.getTestDoubles());
        assertTrue(bean1.getTestDoubles() == bean2.getTestDoubles());
    }
}
