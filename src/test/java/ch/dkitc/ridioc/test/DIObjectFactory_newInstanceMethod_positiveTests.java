package ch.dkitc.ridioc.test;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_newInstanceMethod_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_newInstanceMethod_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_newInstanceMethod_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    // arrays
    // checkJavaLangStringArray();
    // checkTypeArray();

    // lists
    // checkJavaLangStringList();
    // heckTypeList();
    // checkTypeListInjectedIntoDifferentTypes();

    // literals
    // checkJavaLangCharacter();
    // checkJavaLangBoolean();
    // checkJavaLangDate();

    // types
    // checkSingleType();
    // checkSingleTypeWithDependencies();
    // checkMultipleTypes();
    // checkMultipleTypesWithDependencies();

    // factories
    // checkTypeFactory();

    @Test
    public void checkDefaultConstructor() {
        BeanWithDefaultConstructor bean1 = objectFactory.newInstance(BeanWithDefaultConstructor.class);
        assertNotNull(bean1);
        BeanWithDefaultConstructor bean2 = objectFactory.newInstance(BeanWithDefaultConstructor.class);
        assertNotNull(bean2);
        assertTrue(bean1 != bean2);
        assertNotEquals(bean1, bean2);
    }

    @Test
    public void checkPrimitiveByte() {
        BeanWithPrimitiveByteConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveByteConstructor.class, Byte.MIN_VALUE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestByte(), Byte.MIN_VALUE);
        BeanWithPrimitiveByteConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveByteConstructor.class, Byte.MIN_VALUE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestByte(), Byte.MIN_VALUE);
        assertTrue(bean1 != bean2);
        assertNotEquals(bean1, bean2);
    }

    @Test
    public void checkJavaLangByte() {
        Byte testByte = Byte.MIN_VALUE;

        // BeanWithByteConstructor bean1
        BeanWithJavaLangByteConstructor bean1 = objectFactory.newInstance(BeanWithJavaLangByteConstructor.class, testByte);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestByte());
        assertEquals(bean1.getTestByte(), testByte);

        // BeanWithByteConstructor bean2
        BeanWithJavaLangByteConstructor bean2 = objectFactory.newInstance(BeanWithJavaLangByteConstructor.class, testByte);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestByte());
        assertEquals(bean2.getTestByte(), testByte);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
    }

    @Test
    public void checkPrimitiveShort() {
        short testShort = Short.MIN_VALUE;

        // BeanWithPrimitiveShortConstructor bean1
        BeanWithPrimitiveShortConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveShortConstructor.class, Short.MIN_VALUE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestShort(), testShort);

        // BeanWithPrimitiveShortConstructor bean2
        BeanWithPrimitiveShortConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveShortConstructor.class, Short.MIN_VALUE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestShort(), testShort);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestShort(), bean2.getTestShort());
    }

    @Test
    public void checkJavaLangShort() {
        Short testShort = Short.MAX_VALUE;

        // BeanWithShortConstructor bean1
        BeanWithJavaLangShortConstructor bean1 = objectFactory.newInstance(BeanWithJavaLangShortConstructor.class, testShort);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShort());
        assertEquals(bean1.getTestShort(), testShort);

        // BeanWithShortConstructor bean2
        BeanWithJavaLangShortConstructor bean2 = objectFactory.newInstance(BeanWithJavaLangShortConstructor.class, testShort);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShort());
        assertEquals(bean2.getTestShort(), testShort);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestShort(), bean2.getTestShort());
    }

    @Test
    public void checkPrimitiveInteger() {
        int testInt = 1234;

        // BeanWithPrimitiveIntegerConstructor bean1
        BeanWithPrimitiveIntegerConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveIntegerConstructor.class, testInt);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInt(), testInt);

        // BeanWithPrimitiveIntegerConstructor bean2
        BeanWithPrimitiveIntegerConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveIntegerConstructor.class, testInt);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInt(), testInt);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestInt(), bean2.getTestInt());
    }

    @Test
    public void checkJavaLangInteger() {
        Integer testInt = 1234;

        // BeanWithIntegerConstructor bean2
        BeanWithJavaLangIntegerConstructor bean1 = objectFactory.newInstance(BeanWithJavaLangIntegerConstructor.class, testInt);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInteger(), testInt);

        // BeanWithIntegerConstructor bean2
        BeanWithJavaLangIntegerConstructor bean2 = objectFactory.newInstance(BeanWithJavaLangIntegerConstructor.class, testInt);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInteger(), testInt);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestInteger(), bean2.getTestInteger());
    }

    @Test
    public void checkPrimitiveLong() {
        long testLong = Long.MIN_VALUE;

        // BeanWithPrimitiveLongConstructor bean1
        BeanWithPrimitiveLongConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveLongConstructor.class, testLong);
        assertNotNull(bean1);
        assertEquals(bean1.getTestLong(), testLong);

        // BeanWithPrimitiveLongConstructor bean2
        BeanWithPrimitiveLongConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveLongConstructor.class, testLong);
        assertNotNull(bean2);
        assertEquals(bean2.getTestLong(), testLong);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestLong(), bean2.getTestLong());
    }

    @Test
    public void checkJavaLangLong() {
        Long testLong = Long.MAX_VALUE;

        // BeanWithLongConstructor bean1
        BeanWithLongConstructor bean1 = objectFactory.newInstance(BeanWithLongConstructor.class, testLong);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestLong());
        assertEquals(bean1.getTestLong(), testLong);

        // BeanWithLongConstructor bean2
        BeanWithLongConstructor bean2 = objectFactory.newInstance(BeanWithLongConstructor.class, testLong);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestLong());
        assertEquals(bean2.getTestLong(), testLong);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestLong(), bean2.getTestLong());
    }

    @Test
    public void checkPrimitiveFloat() {
        float testFloat = Float.MIN_VALUE;

        // BeanWithPrimitiveFloatConstructor bean1
        BeanWithPrimitiveFloatConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveFloatConstructor.class, testFloat);
        assertNotNull(bean1);
        assertEquals(bean1.getTestFloat(), testFloat, 0.0);

        // BeanWithPrimitiveFloatConstructor bean1
        BeanWithPrimitiveFloatConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveFloatConstructor.class, testFloat);
        assertNotNull(bean2);
        assertEquals(bean2.getTestFloat(), testFloat, 0.0);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestFloat(), bean2.getTestFloat(), 0.0);
    }

    @Test
    public void checkJavaLangFloat() {
        Float testFloat = Float.MAX_VALUE;

        // BeanWithFloatConstructor bean1
        BeanWithJavaLangFloatConstructor bean1 = objectFactory.newInstance(BeanWithJavaLangFloatConstructor.class, testFloat);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestFloat());
        assertEquals(bean1.getTestFloat(), testFloat, 0.0);

        // BeanWithFloatConstructor bean2
        BeanWithJavaLangFloatConstructor bean2 = objectFactory.newInstance(BeanWithJavaLangFloatConstructor.class, testFloat);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestFloat());
        assertEquals(bean2.getTestFloat(), testFloat, 0.0);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestFloat(), bean2.getTestFloat(), 0.0);
    }

    @Test
    public void checkPrimitiveDouble() {
        double testDouble = Double.MIN_VALUE;

        // BeanWithPrimitiveDoubleConstructor bean1
        BeanWithPrimitiveDoubleConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveDoubleConstructor.class, testDouble);
        assertNotNull(bean1);
        assertEquals(bean1.getTestDouble(), testDouble, 0.0);

        // BeanWithPrimitiveDoubleConstructor bean2
        BeanWithPrimitiveDoubleConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveDoubleConstructor.class, testDouble);
        assertNotNull(bean2);
        assertEquals(bean2.getTestDouble(), testDouble, 0.0);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestDouble(), bean2.getTestDouble(), 0.0);
    }

    @Test
    public void checkJavaLangDouble() {
        Double testDouble = Double.MAX_VALUE;

        // BeanWithDoubleConstructor bean1
        BeanWithJavaLangDoubleConstructor bean1 = objectFactory.newInstance(BeanWithJavaLangDoubleConstructor.class, testDouble);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDouble());
        assertEquals(bean1.getTestDouble(), testDouble, 0.0);

        // BeanWithDoubleConstructor bean2
        BeanWithJavaLangDoubleConstructor bean2 = objectFactory.newInstance(BeanWithJavaLangDoubleConstructor.class, testDouble);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDouble());
        assertEquals(bean2.getTestDouble(), testDouble, 0.0);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestDouble(), bean2.getTestDouble(), 0.0);
    }

    @Test
    public void checkPrimitiveCharacter() {
        // see http://www.fileformat.info/info/unicode/char/48
        char testChar = 'H';

        // BeanWithPrimitiveCharConstructor bean1
        BeanWithPrimitiveCharConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveCharConstructor.class, testChar);
        assertNotNull(bean1);
        assertEquals(bean1.getTestChar(), testChar);

        // BeanWithPrimitiveCharConstructor bean2
        BeanWithPrimitiveCharConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveCharConstructor.class, testChar);
        assertNotNull(bean2);
        assertEquals(bean2.getTestChar(), testChar);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestChar(), bean2.getTestChar());
    }

    @Test
    public void checkPrimitiveBoolean() {
        boolean testBoolean = true;

        // BeanWithPrimitiveBooleanConstructor bean1
        BeanWithPrimitiveBooleanConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveBooleanConstructor.class, testBoolean);
        assertNotNull(bean1);
        assertEquals(bean1.getTestBoolean(), testBoolean);

        // BeanWithPrimitiveBooleanConstructor bean2
        BeanWithPrimitiveBooleanConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveBooleanConstructor.class, testBoolean);
        assertNotNull(bean2);
        assertEquals(bean2.getTestBoolean(), testBoolean);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestBoolean(), bean2.getTestBoolean());
    }

    @Test
    public void checkJavaMathBigDecimal() {
        BigDecimal testBigDecimal = new BigDecimal("1972.03");

        // BeanWithBigDecimalConstructor bean1
        BeanWithBigDecimalConstructor bean1 = objectFactory.newInstance(BeanWithBigDecimalConstructor.class, testBigDecimal);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigDecimal());
        assertEquals(bean1.getTestBigDecimal(), testBigDecimal);

        // BeanWithBigDecimalConstructor bean2
        BeanWithBigDecimalConstructor bean2 = objectFactory.newInstance(BeanWithBigDecimalConstructor.class, testBigDecimal);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigDecimal());
        assertEquals(bean2.getTestBigDecimal(), testBigDecimal);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestBigDecimal(), bean2.getTestBigDecimal());
    }

    @Test
    public void newInstancePositiveTests_checkJavaMathBigInteger() {
        BigInteger testBigInteger = new BigInteger("12345678901234567890");

        // BeanWithBigIntegerConstructor bean1
        BeanWithBigIntegerConstructor bean1 = objectFactory.newInstance(BeanWithBigIntegerConstructor.class, testBigInteger);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigInteger());
        assertEquals(bean1.getTestBigInteger(), testBigInteger);

        // BeanWithBigIntegerConstructor bean2
        BeanWithBigIntegerConstructor bean2 = objectFactory.newInstance(BeanWithBigIntegerConstructor.class, testBigInteger);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigInteger());
        assertEquals(bean2.getTestBigInteger(), testBigInteger);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestBigInteger(), bean2.getTestBigInteger());
    }

    @Test
    public void checkJavaLangString() {
        String testString = "myTestString";

        // BeanWithStringConstructor bean1
        BeanWithStringConstructor bean1 = objectFactory.newInstance(BeanWithStringConstructor.class, testString);
        assertNotNull(bean1);
        assertEquals(bean1.getTestString(), testString);

        // BeanWithStringConstructor bean2
        BeanWithStringConstructor bean2 = objectFactory.newInstance(BeanWithStringConstructor.class, testString);
        assertNotNull(bean2);
        assertEquals(bean2.getTestString(), testString);

        // preform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestString(), bean2.getTestString());
    }

    @Test
    public void checkJavaLangEnum() {
        MyEnum testEnum = MyEnum.ONE;

        // BeanWithEnumConstructor bean1
        BeanWithEnumConstructor bean1 = objectFactory.newInstance(BeanWithEnumConstructor.class, testEnum);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestEnum());
        assertEquals(bean1.getTestEnum(), testEnum);

        // BeanWithEnumConstructor bean2
        BeanWithEnumConstructor bean2 = objectFactory.newInstance(BeanWithEnumConstructor.class, testEnum);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestEnum());
        assertEquals(bean2.getTestEnum(), testEnum);

        // perform cross-checks
        assertNotEquals(bean1, bean2);
        assertEquals(bean1.getTestEnum(), bean2.getTestEnum());
    }
}
