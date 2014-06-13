package ch.dkitc.ridioc.test;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_instanceMethod_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    // checkTypeFactory();

    @Test
    public void checkDefaultConstructor() {
        // BeanWithDefaultConstructor bean1
        BeanWithDefaultConstructor bean1 = objectFactory.instance(BeanWithDefaultConstructor.class);
        assertNotNull(bean1);

        // BeanWithDefaultConstructor testBean1
        BeanWithDefaultConstructor bean2 = objectFactory.instance(BeanWithDefaultConstructor.class);
        assertNotNull(bean2);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
    }

    @Test
    public void checkPrimitiveBoolean() {
        boolean testBoolean = true;
        objectFactory.registerStringLiteral("testBoolean", Boolean.toString(testBoolean));

        // BeanWithDoubleConstructor bean1
        BeanWithPrimitiveBooleanConstructor bean1 = objectFactory.instance(BeanWithPrimitiveBooleanConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestBoolean(), testBoolean);

        // BeanWithDoubleConstructor bean2
        BeanWithPrimitiveBooleanConstructor bean2 = objectFactory.instance(BeanWithPrimitiveBooleanConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestBoolean(), testBoolean);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestBoolean(), bean2.getTestBoolean());
    }

    @Test
    public void checkJavaLangBoolean() {
        boolean testBoolean = true;
        objectFactory.registerStringLiteral("testBoolean", Boolean.toString(testBoolean));

        // BeanWithBooleanConstructor bean1
        BeanWithJavaLangBooleanConstructor bean1 = objectFactory.instance(BeanWithJavaLangBooleanConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBoolean());
        assertEquals(bean1.getTestBoolean(), true);

        // BeanWithBooleanConstructor bean2
        BeanWithJavaLangBooleanConstructor bean2 = objectFactory.instance(BeanWithJavaLangBooleanConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBoolean());
        assertEquals(bean2.getTestBoolean(), true);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestBoolean(), bean2.getTestBoolean());
        assertTrue(bean1.getTestBoolean() == bean2.getTestBoolean());
    }

    @Test
    public void checkJavaMathBigDecimal() {
        BigDecimal testBigDecimal = new BigDecimal("1972.03");
        objectFactory.registerStringLiteral("testBigDecimal", testBigDecimal.toString());

        // BeanWithBigDecimalConstructor bean1
        BeanWithBigDecimalConstructor bean1 = objectFactory.instance(BeanWithBigDecimalConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigDecimal());
        assertEquals(bean1.getTestBigDecimal(), testBigDecimal);

        // BeanWithBigDecimalConstructor bean2
        BeanWithBigDecimalConstructor bean2 = objectFactory.instance(BeanWithBigDecimalConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigDecimal());
        assertEquals(bean2.getTestBigDecimal(), testBigDecimal);

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestBigDecimal(), bean2.getTestBigDecimal());
        assertTrue(bean1.getTestBigDecimal() == bean2.getTestBigDecimal());
    }

    @Test
    public void checkJavaMathBigInteger() {
        BigInteger testBigInteger = new BigInteger("12345678901234567890");
        objectFactory.registerStringLiteral("testBigInteger", testBigInteger.toString());

        // BeanWithBigDecimalConstructor bean1
        BeanWithBigIntegerConstructor bean1 = objectFactory.instance(BeanWithBigIntegerConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigInteger());
        assertEquals(bean1.getTestBigInteger(), testBigInteger);

        // BeanWithBigDecimalConstructor bean1
        BeanWithBigIntegerConstructor bean2 = objectFactory.instance(BeanWithBigIntegerConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigInteger());
        assertEquals(bean2.getTestBigInteger(), testBigInteger);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestBigInteger(), bean2.getTestBigInteger());
        assertTrue(bean1.getTestBigInteger() == bean2.getTestBigInteger());
    }
}
