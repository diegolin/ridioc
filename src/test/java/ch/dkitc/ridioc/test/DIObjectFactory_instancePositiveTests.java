package ch.dkitc.ridioc.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.DIUtils;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;
import ch.dkitc.ridioc.test.impl.BeanWithMultipleImplsImpl1;

public class DIObjectFactory_instancePositiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instancePositiveTests.class.getPackage().getName();

    private static final int NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES = 3;

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instancePositiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    //instancePositiveTests_checkTypeListInjectedIntoDifferentTypes();
    //instancePositiveTests_checkJavaLangStringArrayAndList();
    //instancePositiveTests_checkTypeFactory();

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
    public void checkPrimitiveByteArray() {
        // register string literal array for bytes
        Byte[] testBytes = new Byte[]{Byte.MAX_VALUE, -1, 0, 1, Byte.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testBytes", testBytes);

        // create BeanWithPrimitiveByteArrayConstructor bean1
        BeanWithPrimitiveByteArrayConstructor bean1 = objectFactory.instance(BeanWithPrimitiveByteArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBytes());
        assertArrayEquals((byte[]) DIUtils.unboxToPrimitiveByteArray(testBytes), bean1.getTestBytes());

        // create BeanWithPrimitiveByteArrayConstructor bean2
        BeanWithPrimitiveByteArrayConstructor bean2 = objectFactory.instance(BeanWithPrimitiveByteArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBytes());
        assertArrayEquals((byte[]) DIUtils.unboxToPrimitiveByteArray(testBytes), bean2.getTestBytes());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestBytes(), bean2.getTestBytes());

    }

    @Test
    public void checkJavaLangByteArray() {
        // register string literal array for bytes
        Byte[] testBytes = new Byte[]{Byte.MAX_VALUE, -1, 0, 1, Byte.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testBytes", testBytes);

        // create BeanWithJavaLangByteArrayConstructor bean1
        BeanWithJavaLangByteArrayConstructor bean1 = objectFactory.instance(BeanWithJavaLangByteArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBytes());
        assertArrayEquals(testBytes, bean1.getTestBytes());

        // create BeanWithJavaLangByteArrayConstructor bean2
        BeanWithJavaLangByteArrayConstructor bean2 = objectFactory.instance(BeanWithJavaLangByteArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestBytes());
        assertArrayEquals(testBytes, bean2.getTestBytes());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestBytes(), bean2.getTestBytes());
    }

    @Test
    public void checkJavaLangStringArray() {
        // register string literal array
        List<String> testStrings = new ArrayList<String>();
        testStrings.add("one");
        testStrings.add("two");
        testStrings.add("three");
        objectFactory.registerStringLiteralArray("testStrings", testStrings.toArray());

        // BeanWithStringArrayConstructor bean1
        BeanWithStringArrayConstructor bean1 = objectFactory.instance(BeanWithStringArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestStrings());
        assertArrayEquals(bean1.getTestStrings(), testStrings.toArray());

        // BeanWithStringArrayConstructor bean2
        BeanWithStringArrayConstructor bean2 = objectFactory.instance(BeanWithStringArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestStrings());
        assertArrayEquals(bean2.getTestStrings(), testStrings.toArray());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestStrings(), bean2.getTestStrings());
    }

    @Test
    public void checkTypeArray() {
        // BeanWithTypeArrayConstructor bean1
        BeanWithMultipleImplsArrayConstructor bean1 = objectFactory.instance(BeanWithMultipleImplsArrayConstructor.class);
        assertNotNull(bean1);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray1 = bean1.getMultipleImplsArray();
        assertNotNull(beanWithMultipleImplsArray1);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray1.length);

        // BeanWithTypeArrayConstructor bean2
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

    @Test
    public void checkTypeArrayInjectedIntoDifferentTypes() {
        // BeanWithMultipleImplsArrayConstructor bean
        BeanWithMultipleImplsArrayConstructor bean = objectFactory.instance(BeanWithMultipleImplsArrayConstructor.class);
        assertNotNull(bean);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray1 = bean.getMultipleImplsArray();
        assertNotNull(beanWithMultipleImplsArray1);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray1.length);

        // AnotherBeanWithTypeArrayConstructor anotherBean
        AnotherBeanWithTypeArrayConstructor anotherBean = objectFactory.instance(AnotherBeanWithTypeArrayConstructor.class);
        assertNotNull(anotherBean);
        BeanWithMultipleImpls [] beanWithMultipleImplsArray2 = anotherBean.getMultipleImps();
        assertNotNull(beanWithMultipleImplsArray2);
        assertEquals(NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES, beanWithMultipleImplsArray2.length);

        // perform cross-checks
        assertArrayEquals(beanWithMultipleImplsArray1, beanWithMultipleImplsArray2);
        assertTrue(beanWithMultipleImplsArray1 == beanWithMultipleImplsArray2);
    }

    @Test
    public void checkJavaLangByteList() {
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

    @Test
    public void checkJavaLangStringList() {
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

    @Test
    public void checkTypeList() {
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

    @Test
    public void checkTypeArrayAndList() {
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

    @Test
    public void checkPrimitiveByte() {
        byte testByte = Byte.MAX_VALUE;
        objectFactory.registerStringLiteral("testByte", Byte.valueOf(testByte));

        // BeanWithPrimitiveByteConstructor bean1
        BeanWithPrimitiveByteConstructor bean1 = objectFactory.instance(BeanWithPrimitiveByteConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestByte(), testByte);

        // BeanWithPrimitiveByteConstructor bean2s
        BeanWithPrimitiveByteConstructor bean2 = objectFactory.instance(BeanWithPrimitiveByteConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestByte(), testByte);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertEquals(bean1.getTestByte(), bean2.getTestByte());
    }

    @Test
    public void checkJavaLangByte() {
        Byte testByte = Byte.MAX_VALUE;
        objectFactory.registerStringLiteral("testByte", testByte);

        // BeanWithByteConstructor bean1
        BeanWithByteConstructor bean1 = objectFactory.instance(BeanWithByteConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestByte());
        assertEquals(bean1.getTestByte(), testByte);

        // BeanWithByteConstructor bean2
        BeanWithByteConstructor bean2 = objectFactory.instance(BeanWithByteConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestByte());
        assertEquals(bean2.getTestByte(), testByte);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestByte(), bean2.getTestByte());
        assertTrue(bean1.getTestByte() == bean2.getTestByte());
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
    public void checkJavaLangShort() {
        Short testShort = Short.MAX_VALUE;
        objectFactory.registerStringLiteral("testShort", testShort.toString());

        // BeanWithShortConstructor bean1
        BeanWithShortConstructor bean1 = objectFactory.instance(BeanWithShortConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestShort());
        assertEquals(bean1.getTestShort(), testShort);

        // BeanWithShortConstructor bean2
        BeanWithShortConstructor bean2 = objectFactory.instance(BeanWithShortConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestShort());
        assertEquals(bean2.getTestShort(), testShort);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestShort(), bean2.getTestShort());
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
    public void checkJavaLangInteger() {
        Integer testInteger = Integer.MAX_VALUE;
        objectFactory.registerInstance(Integer.class, testInteger);

        // BeanWithIntegerConstructor bean1
        BeanWithIntegerConstructor bean1 = objectFactory.instance(BeanWithIntegerConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInteger(), testInteger);

        // BeanWithIntegerConstructor bean2
        BeanWithIntegerConstructor bean2 = objectFactory.instance(BeanWithIntegerConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInteger(), testInteger);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestInteger(), bean2.getTestInteger());
        assertTrue(bean1.getTestInteger() == bean2.getTestInteger());
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
    public void checkPrimitiveFloat() {
        float testFloat = Float.MAX_VALUE;
        objectFactory.registerStringLiteral("testFloat", Float.toString(Float.MAX_VALUE));

        // BeanWithPrimitiveFloatConstructor bean1
        BeanWithPrimitiveFloatConstructor bean1 = objectFactory.instance(BeanWithPrimitiveFloatConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestFloat(), testFloat, 0.0);

        // BeanWithPrimitiveFloatConstructor bean2
        BeanWithPrimitiveFloatConstructor bean2 = objectFactory.instance(BeanWithPrimitiveFloatConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestFloat(), testFloat, 0.0);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestFloat(), bean2.getTestFloat(), 0.0);
    }

    @Test
    public void checkJavaLangFloat() {
        Float testFloat = Float.MAX_VALUE;
        objectFactory.registerStringLiteral("testFloat", testFloat.toString());

        // BeanWithFloatConstructor bean1
        BeanWithFloatConstructor bean1 = objectFactory.instance(BeanWithFloatConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestFloat());
        assertEquals(bean1.getTestFloat(), testFloat, 0.0);

        // BeanWithFloatConstructor bean2
        BeanWithFloatConstructor bean2 = objectFactory.instance(BeanWithFloatConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestFloat());
        assertEquals(bean2.getTestFloat(), testFloat, 0.0);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestFloat(), bean2.getTestFloat(), 0.0);
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
    public void checkJavaLangDouble() {
        Double testDouble = Double.MAX_VALUE;
        objectFactory.registerStringLiteral("testDouble", testDouble.toString());

        // BeanWithDoubleConstructor bean1
        BeanWithDoubleConstructor bean1 = objectFactory.instance(BeanWithDoubleConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestDouble());
        assertEquals(bean1.getTestDouble(), testDouble, 0.0);

        // BeanWithDoubleConstructor bean2
        BeanWithDoubleConstructor bean2 = objectFactory.instance(BeanWithDoubleConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestDouble());
        assertEquals(bean2.getTestDouble(), testDouble, 0.0);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestDouble(), bean2.getTestDouble(), 0.0);
    }

    @Test
    public void checkPrimitiveCharacter() {
        // see http://www.fileformat.info/info/unicode/char/48
        objectFactory.registerStringLiteral("testChar", "\\u0048");

        // BeanWithDoubleConstructor bean1
        BeanWithPrimitiveCharConstructor bean1 = objectFactory.instance(BeanWithPrimitiveCharConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestChar(), 'H');

        // BeanWithDoubleConstructor bean2
        BeanWithPrimitiveCharConstructor bean2 = objectFactory.instance(BeanWithPrimitiveCharConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestChar(), 'H');

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestChar(), bean2.getTestChar());
    }

    @Test
    public void checkJavaLangCharacter() {
        // see http://www.fileformat.info/info/unicode/char/57
        objectFactory.registerStringLiteral("testCharacter", "\\u0057");

        // BeanWithDoubleConstructor bean1
        BeanWithCharacterConstructor bean1 = objectFactory.instance(BeanWithCharacterConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestCharacter());
        assertEquals(bean1.getTestCharacter(), Character.valueOf('W'));

        // BeanWithDoubleConstructor bean2
        BeanWithCharacterConstructor bean2 = objectFactory.instance(BeanWithCharacterConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestCharacter());
        assertEquals(bean2.getTestCharacter(), Character.valueOf('W'));

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestCharacter(), bean2.getTestCharacter());
        assertTrue(bean1.getTestCharacter() == bean2.getTestCharacter());
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
        BeanWithBooleanConstructor bean1 = objectFactory.instance(BeanWithBooleanConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestBoolean());
        assertEquals(bean1.getTestBoolean(), true);

        // BeanWithBooleanConstructor bean2
        BeanWithBooleanConstructor bean2 = objectFactory.instance(BeanWithBooleanConstructor.class);
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
