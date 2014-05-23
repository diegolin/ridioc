package ch.dkitc.ridioc.test;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;
import ch.dkitc.ridioc.test.impl.BeanWithMultipleImplsImpl1;

public class DIObjectFactoryTest extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactoryTest.class.getPackage().getName();

    private static final int NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES = 3;

    private DIObjectFactory objectFactory;

    public DIObjectFactoryTest() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void newInstancePositiveTests() {
        newInstancePositiveTests_checkDefaultConstructor();

        // arrays
        //newInstancePositiveTests_checkJavaLangStringArray();
        //newInstancePositiveTests_checkTypeArray();

        // lists
        //newInstancePositiveTests_checkJavaLangStringList();
        //newInstancePositiveTests_checkTypeList();
        //newInstancePositiveTests_checkTypeListInjectedIntoDifferentTypes();

        // literals
        newInstancePositiveTests_checkPrimitiveByte();
        newInstancePositiveTests_checkJavaLangByte();
        newInstancePositiveTests_checkPrimitiveShort();
        newInstancePositiveTests_checkJavaLangShort();
        newInstancePositiveTests_checkPrimitiveInteger();
        newInstancePositiveTests_checkJavaLangInteger();
        newInstancePositiveTests_checkPrimitiveLong();
        newInstancePositiveTests_checkJavaLangLong();
        newInstancePositiveTests_checkPrimitiveFloat();
        newInstancePositiveTests_checkJavaLangFloat();
        newInstancePositiveTests_checkPrimitiveDouble();
        newInstancePositiveTests_checkJavaLangDouble();
        newInstancePositiveTests_checkPrimitiveCharacter();
        //newInstancePositiveTests_checkJavaLangCharacter();
        newInstancePositiveTests_checkPrimitiveBoolean();
        //newInstancePositiveTests_checkJavaLangBoolean();
        newInstancePositiveTests_checkJavaMathBigDecimal();
        newInstancePositiveTests_checkJavaMathBigInteger();
        newInstancePositiveTests_checkJavaLangString();
        newInstancePositiveTests_checkJavaLangEnum();
        //newInstancePositiveTests_checkJavaLangDate();

        // types
        //newInstancePositiveTests_checkSingleType();
        //newInstancePositiveTests_checkSingleTypeWithDependencies();
        //newInstancePositiveTests_checkMultipleTypes();
        //newInstancePositiveTests_checkMultipleTypesWithDependencies();

        // factories
        //newInstancePositiveTests_checkTypeFactory();
    }

    @Test
    public void newInstanceNegativeTests() {
        newInstanceNegativeTests_checkBeanButNoImplementation();
        newInstanceNegativeTests_checkBeanWithPrivateDefaultConstructor();
        newInstanceNegativeTests_checkBeanWithMultipleImplementations();

        // primitives
        newInstanceNegativeTests_checkPrimitiveByte();
        newInstanceNegativeTests_checkPrimitiveShort();
    }

    @Test
    public void instancePositiveTests() {
        instancePositiveTests_checkDefaultConstructor();

        // arrays
        instancePositiveTests_checkJavaLangByteArray();
        instancePositiveTests_checkJavaLangStringArray();
        instancePositiveTests_checkTypeArray();
        instancePositiveTests_checkTypeArrayInjectedIntoDifferentTypes();

        // lists
        instancePositiveTests_checkJavaLangByteList();
        instancePositiveTests_checkJavaLangStringList();
        instancePositiveTests_checkTypeList();
        //instancePositiveTests_checkTypeListInjectedIntoDifferentTypes();

        // arrays & lists
        //instancePositiveTests_checkJavaLangStringArrayAndList();
        instancePositiveTests_checkTypeArrayAndList();

        // literals
        instancePositiveTests_checkPrimitiveByte();
        instancePositiveTests_checkJavaLangByte();
        instancePositiveTests_checkPrimitiveShort();
        instancePositiveTests_checkJavaLangShort();
        instancePositiveTests_checkPrimitiveInteger();
        instancePositiveTests_checkJavaLangInteger();
        instancePositiveTests_checkPrimitiveLong();
        instancePositiveTests_checkJavaLangLong();
        instancePositiveTests_checkPrimitiveFloat();
        instancePositiveTests_checkJavaLangFloat();
        instancePositiveTests_checkPrimitiveDouble();
        instancePositiveTests_checkJavaLangDouble();
        instancePositiveTests_checkPrimitiveCharacter();
        instancePositiveTests_checkJavaLangCharacter();
        instancePositiveTests_checkPrimitiveBoolean();
        instancePositiveTests_checkJavaLangBoolean();
        instancePositiveTests_checkJavaMathBigDecimal();
        instancePositiveTests_checkJavaMathBigInteger();
        instancePositiveTests_checkJavaLangString();
        instancePositiveTests_checkJavaLangEnum();
        instancePositiveTests_checkJavaLangDate();

        // types
        instancePositiveTests_checkSingleType();
        instancePositiveTests_checkSingleTypeWithDependencies();
        instancePositiveTests_checkMultipleTypes();
        instancePositiveTests_checkMultipleTypesWithDependencies();

        // factories
        //instancePositiveTests_checkTypeFactory();
    }

    @Test
    public void instanceNegativeTests() {
        instanceNegativeTests_checkBeanButNoImplementation();
        instanceNegativeTests_checkBeanWithPrivateDefaultConstructor();
        instanceNegativeTests_checkBeanWithMultipleImplementations();
    }

    private void instanceNegativeTests_checkBeanButNoImplementation() {
        try {
            objectFactory.instance(BeanButNoImpl.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    private void instanceNegativeTests_checkBeanWithMultipleImplementations() {
        try {
            objectFactory.instance(BeanWithMultipleImpls.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    private void instanceNegativeTests_checkBeanWithPrivateDefaultConstructor() {
        try {
            objectFactory.instance(BeanPrivateDefaultConstructor.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    private void instancePositiveTests_checkDefaultConstructor() {
        BeanWithDefaultConstructor testBean1 = objectFactory.instance(BeanWithDefaultConstructor.class);
        assertNotNull(testBean1);
        BeanWithDefaultConstructor testBean2 = objectFactory.instance(BeanWithDefaultConstructor.class);
        assertNotNull(testBean2);
        assertEquals(testBean1, testBean2);
    }

    private void instancePositiveTests_checkJavaLangBoolean() {
        objectFactory.registerStringLiteral("testBoolean", Boolean.toString(true));
        BeanWithBooleanConstructor bean1 = objectFactory.instance(BeanWithBooleanConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBoolean());
        assertEquals(bean1.getTestBoolean(), true);
        BeanWithBooleanConstructor bean2 = objectFactory.instance(BeanWithBooleanConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBoolean());
        assertEquals(bean2.getTestBoolean(), true);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangByte() {
        Byte testByte = Byte.MAX_VALUE;
        objectFactory.registerStringLiteral("testByte", testByte.toString());
        BeanWithByteConstructor bean1 = objectFactory.instance(BeanWithByteConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestByte());
        assertEquals(bean1.getTestByte(), testByte);
        BeanWithByteConstructor bean2 = objectFactory.instance(BeanWithByteConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestByte());
        assertEquals(bean2.getTestByte(), testByte);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangCharacter() {
        // see http://www.fileformat.info/info/unicode/char/57
        objectFactory.registerStringLiteral("testCharacter", "\\u0057");
        BeanWithCharacterConstructor bean1 = objectFactory.instance(BeanWithCharacterConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestCharacter());
        assertEquals(bean1.getTestCharacter(), Character.valueOf('W'));
        BeanWithCharacterConstructor bean2 = objectFactory.instance(BeanWithCharacterConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestCharacter());
        assertEquals(bean2.getTestCharacter(), Character.valueOf('W'));
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangDate() {
        objectFactory.registerStringLiteral("testDate", "2010-01-01T12:00:00Z");
        BeanWithDateConstructor bean1 = objectFactory.instance(BeanWithDateConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDate());
        assertEquals(bean1.getTestDate(), DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z").getTime());
        BeanWithDateConstructor bean2 = objectFactory.instance(BeanWithDateConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDate());
        assertEquals(bean2.getTestDate(), DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z").getTime());
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangDouble() {
        Double testDouble = Double.MAX_VALUE;
        objectFactory.registerStringLiteral("testDouble", testDouble.toString());
        BeanWithDoubleConstructor bean1 = objectFactory.instance(BeanWithDoubleConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDouble());
        assertEquals(bean1.getTestDouble(), testDouble, 0.0);
        BeanWithDoubleConstructor bean2 = objectFactory.instance(BeanWithDoubleConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDouble());
        assertEquals(bean2.getTestDouble(), testDouble, 0.0);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangEnum() {
        objectFactory.registerInstance(MyEnum.class, MyEnum.ONE);
        BeanWithEnumConstructor bean1 = objectFactory.instance(BeanWithEnumConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestEnum(), MyEnum.ONE);
        BeanWithEnumConstructor bean2 = objectFactory.instance(BeanWithEnumConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestEnum(), MyEnum.ONE);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangFloat() {
        Float testFloat = Float.MAX_VALUE;
        objectFactory.registerStringLiteral("testFloat", testFloat.toString());
        BeanWithFloatConstructor bean1 = objectFactory.instance(BeanWithFloatConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestFloat());
        assertEquals(bean1.getTestFloat(), testFloat, 0.0);
        BeanWithFloatConstructor bean2 = objectFactory.instance(BeanWithFloatConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestFloat());
        assertEquals(bean2.getTestFloat(), testFloat, 0.0);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangInteger() {
        objectFactory.registerInstance(Integer.class, Integer.MAX_VALUE);
        BeanWithIntegerConstructor bean1 = objectFactory.instance(BeanWithIntegerConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInteger(), new Integer(Integer.MAX_VALUE));
        BeanWithIntegerConstructor bean2 = objectFactory.instance(BeanWithIntegerConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInteger(), new Integer(Integer.MAX_VALUE));
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangLong() {
        Long testLong = Long.MAX_VALUE;
        objectFactory.registerStringLiteral("testLong", testLong.toString());
        BeanWithLongConstructor bean1 = objectFactory.instance(BeanWithLongConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestLong());
        assertEquals(bean1.getTestLong(), testLong);
        BeanWithLongConstructor bean2 = objectFactory.instance(BeanWithLongConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestLong());
        assertEquals(bean2.getTestLong(), testLong);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangShort() {
        Short testShort = Short.MAX_VALUE;
        objectFactory.registerStringLiteral("testShort", testShort.toString());
        BeanWithShortConstructor bean1 = objectFactory.instance(BeanWithShortConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShort());
        assertEquals(bean1.getTestShort(), testShort);
        BeanWithShortConstructor bean2 = objectFactory.instance(BeanWithShortConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShort());
        assertEquals(bean2.getTestShort(), testShort);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaLangString() {
        String testString = "myTestString";
        objectFactory.registerInstance(String.class, testString);
        BeanWithStringConstructor bean1 = objectFactory.instance(BeanWithStringConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestString(), testString);
        BeanWithStringConstructor bean2 = objectFactory.instance(BeanWithStringConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestString(), testString);
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
    }

    private void instancePositiveTests_checkJavaLangByteArray() {
        // register string literal array for bytes
        Byte[] testBytes = new Byte[]{Byte.MAX_VALUE, -1, 0, 1, Byte.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testBytes", testBytes);

        // create BeanWithJavaLangByteListConstructor bean1
        BeanWithJavaLangByteArrayConstructor bean1 = objectFactory.instance(BeanWithJavaLangByteArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBytes());
        assertArrayEquals(testBytes, bean1.getTestBytes());

        // create BeanWithJavaLangByteListConstructor bean2
        BeanWithJavaLangByteArrayConstructor bean2 = objectFactory.instance(BeanWithJavaLangByteArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBytes());
        assertArrayEquals(testBytes, bean2.getTestBytes());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestBytes(), bean2.getTestBytes());
    }

    private void instancePositiveTests_checkJavaLangStringArray() {
        List<String> testStrings = new ArrayList<String>();
        testStrings.add("one");
        testStrings.add("two");
        testStrings.add("three");
        objectFactory.registerStringLiteralArray("testStrings", testStrings.toArray(new String[testStrings.size()]));
        BeanWithStringArrayConstructor bean = objectFactory.instance(BeanWithStringArrayConstructor.class);
        assertNotNull(bean);
        assertNotNull(bean.getTestStrings());
        assertArrayEquals(bean.getTestStrings(), testStrings.toArray());
    }

    private void instancePositiveTests_checkJavaMathBigDecimal() {
        BigDecimal testBigDecimal = new BigDecimal("1972.03");
        objectFactory.registerStringLiteral("testBigDecimal", testBigDecimal.toString());
        BeanWithBigDecimalConstructor bean1 = objectFactory.instance(BeanWithBigDecimalConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigDecimal());
        assertEquals(bean1.getTestBigDecimal(), testBigDecimal);
        BeanWithBigDecimalConstructor bean2 = objectFactory.instance(BeanWithBigDecimalConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigDecimal());
        assertEquals(bean2.getTestBigDecimal(), testBigDecimal);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkJavaMathBigInteger() {
        BigDecimal testBigDecimal = new BigDecimal("12345678901234567890");
        BeanWithBigDecimalConstructor bean1 = objectFactory.newInstance(BeanWithBigDecimalConstructor.class, testBigDecimal);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigDecimal());
        assertEquals(bean1.getTestBigDecimal(), testBigDecimal);
        BeanWithBigDecimalConstructor bean2 = objectFactory.newInstance(BeanWithBigDecimalConstructor.class, testBigDecimal);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigDecimal());
        assertEquals(bean2.getTestBigDecimal(), testBigDecimal);
        assertNotEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkPrimitiveBoolean() {
        boolean testBoolean = true;
        objectFactory.registerStringLiteral("testBoolean", Boolean.toString(testBoolean));
        BeanWithPrimitiveBooleanConstructor bean1 = objectFactory.instance(BeanWithPrimitiveBooleanConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestBoolean(), testBoolean);
        BeanWithPrimitiveBooleanConstructor bean2 = objectFactory.instance(BeanWithPrimitiveBooleanConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestBoolean(), testBoolean);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkPrimitiveByte() {
        objectFactory.registerStringLiteral("testByte", Byte.toString(Byte.MAX_VALUE));
        BeanWithPrimitiveByteConstructor bean1 = objectFactory.instance(BeanWithPrimitiveByteConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestByte(), Byte.MAX_VALUE);
        BeanWithPrimitiveByteConstructor bean2 = objectFactory.instance(BeanWithPrimitiveByteConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestByte(), Byte.MAX_VALUE);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkPrimitiveCharacter() {
        // see http://www.fileformat.info/info/unicode/char/48
        objectFactory.registerStringLiteral("testChar", "\\u0048");
        BeanWithPrimitiveCharConstructor bean1 = objectFactory.instance(BeanWithPrimitiveCharConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestChar(), 'H');
        BeanWithPrimitiveCharConstructor bean2 = objectFactory.instance(BeanWithPrimitiveCharConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestChar(), 'H');
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkPrimitiveDouble() {
        objectFactory.registerStringLiteral("testDouble", Double.toString(Double.MAX_VALUE));
        BeanWithPrimitiveDoubleConstructor bean1 = objectFactory.instance(BeanWithPrimitiveDoubleConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestDouble(), Double.MAX_VALUE, 0.0);
        BeanWithPrimitiveDoubleConstructor bean2 = objectFactory.instance(BeanWithPrimitiveDoubleConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestDouble(), Double.MAX_VALUE, 0.0);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkPrimitiveFloat() {
        objectFactory.registerStringLiteral("testFloat", Float.toString(Float.MAX_VALUE));
        BeanWithPrimitiveFloatConstructor bean1 = objectFactory.instance(BeanWithPrimitiveFloatConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestFloat(), Float.MAX_VALUE, 0.0);
        BeanWithPrimitiveFloatConstructor bean2 = objectFactory.instance(BeanWithPrimitiveFloatConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestFloat(), Float.MAX_VALUE, 0.0);
    }

    private void instancePositiveTests_checkPrimitiveInteger() {
        objectFactory.registerInstance(int.class, Integer.MAX_VALUE);
        BeanWithPrimitiveIntegerConstructor bean1 = objectFactory.instance(BeanWithPrimitiveIntegerConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInt(), Integer.MAX_VALUE);
        BeanWithPrimitiveIntegerConstructor bean2 = objectFactory.instance(BeanWithPrimitiveIntegerConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInt(), Integer.MAX_VALUE);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkPrimitiveLong() {
        objectFactory.registerStringLiteral("testLong", Long.toString(Long.MAX_VALUE));
        BeanWithPrimitiveLongConstructor bean1 = objectFactory.instance(BeanWithPrimitiveLongConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestLong(), Long.MAX_VALUE);
        BeanWithPrimitiveLongConstructor bean2 = objectFactory.instance(BeanWithPrimitiveLongConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestLong(), Long.MAX_VALUE);
        assertEquals(bean1, bean2);
    }

    private void instancePositiveTests_checkPrimitiveShort() {
        objectFactory.registerStringLiteral("testShort", Short.toString(Short.MAX_VALUE));
        BeanWithPrimitiveShortConstructor bean1 = objectFactory.instance(BeanWithPrimitiveShortConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestShort(), Short.MAX_VALUE);
        BeanWithPrimitiveShortConstructor bean2 = objectFactory.instance(BeanWithPrimitiveShortConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestShort(), Short.MAX_VALUE);
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
    }

    private void instancePositiveTests_checkTypeArray() {
        // create BeanWithTypeArrayConstructor bean1
        BeanWithMultipleImplsArrayConstructor bean1 = objectFactory.instance(BeanWithMultipleImplsArrayConstructor.class);
        assertNotNull(bean1);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray1 = bean1.getMultipleImplsArray();
        assertNotNull(beanWithMultipleImplsArray1);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray1.length);

        // create BeanWithTypeArrayConstructor bean2
        BeanWithMultipleImplsArrayConstructor bean2 = objectFactory.instance(BeanWithMultipleImplsArrayConstructor.class);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray2 = bean1.getMultipleImplsArray();
        assertNotNull(beanWithMultipleImplsArray2);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray2.length);

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(beanWithMultipleImplsArray1, beanWithMultipleImplsArray2);
        assertTrue(beanWithMultipleImplsArray1 == beanWithMultipleImplsArray2);
    }

    private void instancePositiveTests_checkTypeArrayInjectedIntoDifferentTypes() {
        BeanWithMultipleImplsArrayConstructor bean = objectFactory.instance(BeanWithMultipleImplsArrayConstructor.class);
        assertNotNull(bean);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray1 = bean.getMultipleImplsArray();
        assertNotNull(beanWithMultipleImplsArray1);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray1.length);
        AnotherBeanWithTypeArrayConstructor anotherBean = objectFactory.instance(AnotherBeanWithTypeArrayConstructor.class);
        assertNotNull(anotherBean);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray2 = anotherBean.getMultipleImps();
        assertNotNull(beanWithMultipleImplsArray2);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray2.length);
        assertArrayEquals(beanWithMultipleImplsArray1, beanWithMultipleImplsArray2);
        assertTrue(beanWithMultipleImplsArray1 == beanWithMultipleImplsArray2);
    }

    private void instancePositiveTests_checkSingleType() {
        BeanWithSingleTypeConstructor bean1 = objectFactory.instance(BeanWithSingleTypeConstructor.class);
        assertNotNull(bean1);
        BeanWithDefaultConstructor beanWithDefaultConstructor1 = bean1.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor1);

        BeanWithSingleTypeConstructor bean2 = objectFactory.instance(BeanWithSingleTypeConstructor.class);
        assertNotNull(bean2);
        BeanWithDefaultConstructor beanWithDefaultConstructor2 = bean2.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor2);

        assertTrue(bean1 == bean2);
        assertTrue(beanWithDefaultConstructor1 == beanWithDefaultConstructor2);
    }

    private void instancePositiveTests_checkSingleTypeWithDependencies() {
        BeanWithSingleTypeConstructorWithDependencies bean1 = objectFactory.instance(BeanWithSingleTypeConstructorWithDependencies.class);
        assertNotNull(bean1);
        BeanWithSingleTypeDependency beanWithSingleTypeDependency1 = bean1.getBeanWithSingleTypeDependency();
        assertNotNull(beanWithSingleTypeDependency1);
        BeanWithDefaultConstructor beanWithDefaultConstructor1 = beanWithSingleTypeDependency1.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor1);
        BeanWithSingleTypeConstructorWithDependencies bean2 = objectFactory.instance(BeanWithSingleTypeConstructorWithDependencies.class);
        assertNotNull(bean2);
        BeanWithSingleTypeDependency beanWithSingleTypeDependency2 = bean2.getBeanWithSingleTypeDependency();
        assertNotNull(beanWithSingleTypeDependency2);
        BeanWithDefaultConstructor beanWithDefaultConstructor2 = beanWithSingleTypeDependency2.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor1);
        assertTrue(bean1 == bean2);
        assertTrue(beanWithSingleTypeDependency1 == beanWithSingleTypeDependency2);
        assertTrue(beanWithDefaultConstructor1 == beanWithDefaultConstructor2);
    }

    private void instancePositiveTests_checkMultipleTypes() {
        BeanWithMultipleTypesConstructor bean1 = objectFactory.instance(BeanWithMultipleTypesConstructor.class);
        assertNotNull(bean1);
        BeanWithDefaultConstructor beanWithDefaultConstructor1 = bean1.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor1);
        AnotherBeanWithDefaultConstructor anotherBeanWithDefaultConstructor1 = bean1.getAnotherBeanWithDefaultConstructor();
        assertNotNull(anotherBeanWithDefaultConstructor1);

        BeanWithMultipleTypesConstructor bean2 = objectFactory.instance(BeanWithMultipleTypesConstructor.class);
        assertNotNull(bean2);
        BeanWithDefaultConstructor beanWithDefaultConstructor2 = bean2.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor2);
        AnotherBeanWithDefaultConstructor anotherBeanWithDefaultConstructor2 = bean1.getAnotherBeanWithDefaultConstructor();

        assertNotNull(anotherBeanWithDefaultConstructor2);
        assertTrue(bean1 == bean2);
        assertTrue(beanWithDefaultConstructor1 == beanWithDefaultConstructor2);
        assertTrue(anotherBeanWithDefaultConstructor1 == anotherBeanWithDefaultConstructor2);
    }

    private void instancePositiveTests_checkMultipleTypesWithDependencies() {
        BeanWithMultipleTypesConstructorWithDependencies bean1 = objectFactory.instance(BeanWithMultipleTypesConstructorWithDependencies.class);
        assertNotNull(bean1);
        BeanWithSingleTypeDependency beanWithSingleTypeDependency1 = bean1.getBeanWithSingleTypeDependency();
        assertNotNull(beanWithSingleTypeDependency1);
        BeanWithDefaultConstructor beanWithDefaultConstructor11 = beanWithSingleTypeDependency1.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor11);
        AnotherBeanWithSingleTypeDependency anotherBeanWithSingleTypeDependency1 = bean1.getAnotherBeanWithSingleTypeDependency();
        assertNotNull(anotherBeanWithSingleTypeDependency1);
        BeanWithDefaultConstructor beanWithDefaultConstructor12 = anotherBeanWithSingleTypeDependency1.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor12);

        BeanWithMultipleTypesConstructorWithDependencies bean2 = objectFactory.instance(BeanWithMultipleTypesConstructorWithDependencies.class);
        assertNotNull(bean2);
        BeanWithSingleTypeDependency beanWithSingleTypeDependency2 = bean2.getBeanWithSingleTypeDependency();
        assertNotNull(beanWithSingleTypeDependency2);
        BeanWithDefaultConstructor beanWithDefaultConstructor21 = beanWithSingleTypeDependency1.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor21);
        AnotherBeanWithSingleTypeDependency anotherBeanWithSingleTypeDependency2 = bean2.getAnotherBeanWithSingleTypeDependency();
        assertNotNull(anotherBeanWithSingleTypeDependency2);
        BeanWithDefaultConstructor beanWithDefaultConstructor22 = anotherBeanWithSingleTypeDependency1.getBeanWithDefaultConstructor();
        assertNotNull(beanWithDefaultConstructor22);

        assertTrue(bean1 == bean2);
        assertTrue(beanWithSingleTypeDependency1 == beanWithSingleTypeDependency2);
        assertTrue(beanWithDefaultConstructor11 == beanWithDefaultConstructor12);
        assertTrue(beanWithDefaultConstructor12 == beanWithDefaultConstructor21);
        assertTrue(beanWithDefaultConstructor21 == beanWithDefaultConstructor22);
        assertTrue(anotherBeanWithSingleTypeDependency1 == anotherBeanWithSingleTypeDependency2);
    }

    private void instancePositiveTests_checkJavaLangByteList() {
        // register value List<Byte>
        Byte[] testBytes = new Byte[]{Byte.MAX_VALUE, -1, 0, 1, Byte.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testBytes", testBytes);

        // create BeanWithJavaLangByteListConstructor bean1
        BeanWithJavaLangByteListConstructor bean1 = objectFactory.instance(BeanWithJavaLangByteListConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBytes());
        assertEquals(Arrays.asList(testBytes), bean1.getTestBytes());
        assertFalse(testBytes == bean1.getTestBytes().toArray());

        // create BeanWithJavaLangByteListConstructor bean1
        BeanWithJavaLangByteListConstructor bean2 = objectFactory.instance(BeanWithJavaLangByteListConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBytes());
        assertEquals(Arrays.asList(testBytes), bean2.getTestBytes());
        assertFalse(testBytes == bean2.getTestBytes().toArray());

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestBytes(), bean2.getTestBytes());
        assertTrue(bean1.getTestBytes() == bean2.getTestBytes());
    }

    private void instancePositiveTests_checkJavaLangStringList() {
        // register List<String>
        List<String> testStrings = new ArrayList<String>();
        testStrings.add("A");
        testStrings.add("B");
        testStrings.add("C");
        objectFactory.registerStringLiteralArray("testStrings", testStrings.toArray(new String[testStrings.size()]));

        // BeanWithStringListConstructor bean1
        BeanWithStringListConstructor bean1 = objectFactory.instance(BeanWithStringListConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestStrings());
        assertEquals(testStrings, bean1.getTestStrings());
        assertFalse(testStrings == bean1.getTestStrings());

        BeanWithStringListConstructor bean2 = objectFactory.instance(BeanWithStringListConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestStrings());
        assertEquals(testStrings, bean2.getTestStrings());
        assertFalse(testStrings == bean2.getTestStrings());

        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestStrings(), bean2.getTestStrings());
        assertTrue(bean1.getTestStrings() == bean2.getTestStrings());

        // check whether the list is unmodifiable
        try {
            bean1.getTestStrings().clear();
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
        try {
            bean1.getTestStrings().add("D");
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
        try {
            bean1.getTestStrings().remove(0);
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
        try {
            bean1.getTestStrings().addAll(testStrings);
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
    }

    private void instancePositiveTests_checkTypeArrayAndList() {
        // create BeanWithMultipleImplsListConstructor bean1
        BeanWithMultipleImplsListConstructor bean1 = objectFactory.instance(BeanWithMultipleImplsListConstructor.class);
        assertNotNull(bean1);
        List<BeanWithMultipleImpls> beanWithMultipleImplsList1 = bean1.getMultipleImplsList();
        assertNotNull(beanWithMultipleImplsList1);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsList1.size());

        // create BeanWithTypeArrayConstructor bean2
        BeanWithMultipleImplsArrayConstructor bean2 = objectFactory.instance(BeanWithMultipleImplsArrayConstructor.class);
        assertNotNull(bean2);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray2 = bean2.getMultipleImplsArray();
        assertNotNull(beanWithMultipleImplsArray2);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray2.length);

        // perform cross-checks
        assertFalse(bean1 == bean2);
        assertArrayEquals(beanWithMultipleImplsList1.toArray(), beanWithMultipleImplsArray2);
    }

    private void instancePositiveTests_checkTypeList() {
        BeanWithMultipleImplsListConstructor bean1 = objectFactory.instance(BeanWithMultipleImplsListConstructor.class);
        assertNotNull(bean1);
        List<BeanWithMultipleImpls> beanWithMultipleImplsList1 = bean1.getMultipleImplsList();
        assertNotNull(beanWithMultipleImplsList1);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsList1.size());

        BeanWithMultipleImplsListConstructor bean2 = objectFactory.instance(BeanWithMultipleImplsListConstructor.class);
        assertNotNull(bean2);
        List<BeanWithMultipleImpls> beanWithMultipleImplsList2 = bean1.getMultipleImplsList();
        assertNotNull(beanWithMultipleImplsList2);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsList2.size());

        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(beanWithMultipleImplsList1, beanWithMultipleImplsList2);
        assertTrue(beanWithMultipleImplsList1 == beanWithMultipleImplsList2);

        // check whether the list is unmodifiable
        try {
            beanWithMultipleImplsList1.clear();
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
        try {
            beanWithMultipleImplsList1.add(new BeanWithMultipleImplsImpl1());
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
        try {
            beanWithMultipleImplsList1.remove(0);
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
        try {
            beanWithMultipleImplsList1.addAll(beanWithMultipleImplsList2);
            fail("should throw 'java.lang.UnsupportOperationExcepion'");
        }
        catch (UnsupportedOperationException ex) {
            // o.k.
        }
    }

    private void newInstanceNegativeTests_checkBeanButNoImplementation() {
        try {
            objectFactory.newInstance(BeanButNoImpl.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    private void newInstanceNegativeTests_checkBeanWithMultipleImplementations() {
        try {
            objectFactory.newInstance(BeanWithMultipleImpls.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    private void newInstanceNegativeTests_checkBeanWithPrivateDefaultConstructor() {
        try {
            objectFactory.newInstance(BeanPrivateDefaultConstructor.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    private void newInstanceNegativeTests_checkPrimitiveByte() {
        try {
            objectFactory.newInstance(BeanWithPrimitiveByteConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    private void newInstanceNegativeTests_checkPrimitiveShort() {
        try {
            objectFactory.newInstance(BeanWithPrimitiveShortConstructor.class);
            fail("should through IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // test passed
        }
    }

    private void newInstancePositiveTests_checkDefaultConstructor() {
        BeanWithDefaultConstructor bean1 = objectFactory.newInstance(BeanWithDefaultConstructor.class);
        assertNotNull(bean1);
        BeanWithDefaultConstructor bean2 = objectFactory.newInstance(BeanWithDefaultConstructor.class);
        assertNotNull(bean2);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangByte() {
        Byte testByte = Byte.MIN_VALUE;
        BeanWithByteConstructor bean1 = objectFactory.newInstance(BeanWithByteConstructor.class, testByte);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestByte());
        assertEquals(bean1.getTestByte(), testByte);
        BeanWithByteConstructor bean2 = objectFactory.newInstance(BeanWithByteConstructor.class, testByte);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestByte());
        assertEquals(bean2.getTestByte(), testByte);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangDouble() {
        Double testDouble = Double.MAX_VALUE;
        BeanWithDoubleConstructor bean1 = objectFactory.newInstance(BeanWithDoubleConstructor.class, testDouble);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDouble());
        assertEquals(bean1.getTestDouble(), testDouble, 0.0);
        BeanWithDoubleConstructor bean2 = objectFactory.newInstance(BeanWithDoubleConstructor.class, testDouble);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDouble());
        assertEquals(bean2.getTestDouble(), testDouble, 0.0);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangEnum() {
        BeanWithEnumConstructor bean1 = objectFactory.newInstance(BeanWithEnumConstructor.class, MyEnum.ONE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestEnum(), MyEnum.ONE);
        BeanWithEnumConstructor bean2 = objectFactory.newInstance(BeanWithEnumConstructor.class, MyEnum.ONE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestEnum(), MyEnum.ONE);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangFloat() {
        Float testFloat = Float.MAX_VALUE;
        BeanWithFloatConstructor bean1 = objectFactory.newInstance(BeanWithFloatConstructor.class, testFloat);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestFloat());
        assertEquals(bean1.getTestFloat(), testFloat, 0.0);
        BeanWithFloatConstructor bean2 = objectFactory.newInstance(BeanWithFloatConstructor.class, testFloat);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestFloat());
        assertEquals(bean2.getTestFloat(), testFloat, 0.0);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangInteger() {
        BeanWithIntegerConstructor bean1 = objectFactory.newInstance(BeanWithIntegerConstructor.class, 1234);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInteger(), new Integer(1234));
        BeanWithIntegerConstructor bean2 = objectFactory.newInstance(BeanWithIntegerConstructor.class, 1234);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInteger(), new Integer(1234));
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangLong() {
        Long testLong = Long.MAX_VALUE;
        BeanWithLongConstructor bean1 = objectFactory.newInstance(BeanWithLongConstructor.class, testLong);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestLong());
        assertEquals(bean1.getTestLong(), testLong);
        BeanWithLongConstructor bean2 = objectFactory.newInstance(BeanWithLongConstructor.class, testLong);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestLong());
        assertEquals(bean2.getTestLong(), testLong);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangShort() {
        Short testShort = Short.MAX_VALUE;
        BeanWithShortConstructor bean1 = objectFactory.newInstance(BeanWithShortConstructor.class, testShort);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShort());
        assertEquals(bean1.getTestShort(), testShort);
        BeanWithShortConstructor bean2 = objectFactory.newInstance(BeanWithShortConstructor.class, testShort);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShort());
        assertEquals(bean2.getTestShort(), testShort);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaLangString() {
        BeanWithStringConstructor bean1 = objectFactory.newInstance(BeanWithStringConstructor.class, "myTestString");
        assertNotNull(bean1);
        assertEquals(bean1.getTestString(), "myTestString");
        BeanWithStringConstructor bean2 = objectFactory.newInstance(BeanWithStringConstructor.class, "myTestString");
        assertNotNull(bean2);
        assertEquals(bean2.getTestString(), "myTestString");
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaMathBigDecimal() {
        BigDecimal testBigDecimal = new BigDecimal("1972.03");
        BeanWithBigDecimalConstructor bean1 = objectFactory.newInstance(BeanWithBigDecimalConstructor.class, testBigDecimal);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigDecimal());
        assertEquals(bean1.getTestBigDecimal(), testBigDecimal);
        BeanWithBigDecimalConstructor bean2 = objectFactory.newInstance(BeanWithBigDecimalConstructor.class, testBigDecimal);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigDecimal());
        assertEquals(bean2.getTestBigDecimal(), testBigDecimal);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkJavaMathBigInteger() {
        BigInteger testBigInteger = new BigInteger("12345678901234567890");
        BeanWithBigIntegerConstructor bean1 = objectFactory.newInstance(BeanWithBigIntegerConstructor.class, testBigInteger);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBigInteger());
        assertEquals(bean1.getTestBigInteger(), testBigInteger);
        BeanWithBigIntegerConstructor bean2 = objectFactory.newInstance(BeanWithBigIntegerConstructor.class, testBigInteger);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBigInteger());
        assertEquals(bean2.getTestBigInteger(), testBigInteger);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveBoolean() {
        BeanWithPrimitiveBooleanConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveBooleanConstructor.class, true);
        assertNotNull(bean1);
        assertEquals(bean1.getTestBoolean(), true);
        BeanWithPrimitiveBooleanConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveBooleanConstructor.class, true);
        assertNotNull(bean2);
        assertEquals(bean2.getTestBoolean(), true);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveByte() {
        BeanWithPrimitiveByteConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveByteConstructor.class, Byte.MIN_VALUE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestByte(), Byte.MIN_VALUE);
        BeanWithPrimitiveByteConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveByteConstructor.class, Byte.MIN_VALUE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestByte(), Byte.MIN_VALUE);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveCharacter() {
        // see http://www.fileformat.info/info/unicode/char/48
        BeanWithPrimitiveCharConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveCharConstructor.class, 'H');
        assertNotNull(bean1);
        assertEquals(bean1.getTestChar(), 'H');
        BeanWithPrimitiveCharConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveCharConstructor.class, 'H');
        assertNotNull(bean2);
        assertEquals(bean2.getTestChar(), 'H');
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveDouble() {
        BeanWithPrimitiveDoubleConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveDoubleConstructor.class, Double.MIN_VALUE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestDouble(), Double.MIN_VALUE, 0.0);
        BeanWithPrimitiveDoubleConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveDoubleConstructor.class, Double.MIN_VALUE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestDouble(), Double.MIN_VALUE, 0.0);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveFloat() {
        BeanWithPrimitiveFloatConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveFloatConstructor.class, Float.MIN_VALUE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestFloat(), Float.MIN_VALUE, 0.0);
        BeanWithPrimitiveFloatConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveFloatConstructor.class, Float.MIN_VALUE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestFloat(), Float.MIN_VALUE, 0.0);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveInteger() {
        BeanWithPrimitiveIntegerConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveIntegerConstructor.class, 1234);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInt(), 1234);
        BeanWithPrimitiveIntegerConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveIntegerConstructor.class, 1234);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInt(), 1234);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveLong() {
        BeanWithPrimitiveLongConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveLongConstructor.class, Long.MIN_VALUE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestLong(), Long.MIN_VALUE);
        BeanWithPrimitiveLongConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveLongConstructor.class, Long.MIN_VALUE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestLong(), Long.MIN_VALUE);
        assertNotEquals(bean1, bean2);
    }

    private void newInstancePositiveTests_checkPrimitiveShort() {
        BeanWithPrimitiveShortConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveShortConstructor.class, Short.MIN_VALUE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestShort(), Short.MIN_VALUE);
        BeanWithPrimitiveShortConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveShortConstructor.class, Short.MIN_VALUE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestShort(), Short.MIN_VALUE);
        assertNotEquals(bean1, bean2);
    }
}
