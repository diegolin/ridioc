package ch.dkitc.ridioc;

import ch.dkitc.ridioc.test.api.*;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DITest {

    public static final String PACKAGE_PREFIX = DITest.class.getPackage().getName();

    @Test
    public void factoryRegisterInstancePositiveTests() {
        TestObjectFactory objectFactory = DI.createObjectFactory(TestObjectFactory.class, PACKAGE_PREFIX);
        assertNotNull(objectFactory);

        // (default constructor)
        {
            TestBeanWithDefaultConstructor testBean1 = objectFactory.instance(TestBeanWithDefaultConstructor.class);
            assertNotNull(testBean1);
            TestBeanWithDefaultConstructor testBean2 = objectFactory.instance(TestBeanWithDefaultConstructor.class);
            assertNotNull(testBean2);
            assertEquals(testBean1, testBean2);
        }

        // java.lang.String
        {
            objectFactory.registerInstance(String.class, "myTestString");
            TestBeanWithStringConstructor bean1 = objectFactory.instance(TestBeanWithStringConstructor.class);
            assertNotNull(bean1);
            assertEquals(bean1.getTestString(), "myTestString");
            TestBeanWithStringConstructor bean2 = objectFactory.instance(TestBeanWithStringConstructor.class);
            assertNotNull(bean2);
            assertEquals(bean2.getTestString(), "myTestString");
            assertEquals(bean1, bean2);
        }

        // int (primitive)
        {
            objectFactory.registerInstance(int.class, Integer.MAX_VALUE);
            TestBeanWithPrimitiveIntegerConstructor bean1 = objectFactory.instance(TestBeanWithPrimitiveIntegerConstructor.class);
            assertNotNull(bean1);
            assertEquals(bean1.getTestInt(), Integer.MAX_VALUE);
            TestBeanWithPrimitiveIntegerConstructor bean2 = objectFactory.instance(TestBeanWithPrimitiveIntegerConstructor.class);
            assertNotNull(bean2);
            assertEquals(bean2.getTestInt(), Integer.MAX_VALUE);
            assertEquals(bean1, bean2);
        }

        // java.lang.Integer
        {
            objectFactory.registerInstance(Integer.class, Integer.MAX_VALUE);
            TestBeanWithIntegerConstructor bean1 = objectFactory.instance(TestBeanWithIntegerConstructor.class);
            assertNotNull(bean1);
            assertEquals(bean1.getTestInteger(), new Integer(Integer.MAX_VALUE));
            TestBeanWithIntegerConstructor bean2 = objectFactory.instance(TestBeanWithIntegerConstructor.class);
            assertNotNull(bean2);
            assertEquals(bean2.getTestInteger(), new Integer(Integer.MAX_VALUE));
            assertEquals(bean1, bean2);
        }

        // java.lang.Enum
        {
            objectFactory.registerInstance(TestEnum.class, TestEnum.ONE);
            TestBeanWithEnumConstructor bean1 = objectFactory.instance(TestBeanWithEnumConstructor.class);
            assertNotNull(bean1);
            assertEquals(bean1.getTestEnum(), TestEnum.ONE);
            TestBeanWithEnumConstructor bean2 = objectFactory.instance(TestBeanWithEnumConstructor.class);
            assertNotNull(bean2);
            assertEquals(bean2.getTestEnum(), TestEnum.ONE);
            assertEquals(bean1, bean2);
        }
    }

    @Test
    public void factoryNewInstancePositiveTests() {
        TestObjectFactory objectFactory = DI.createObjectFactory(TestObjectFactory.class, PACKAGE_PREFIX);
        assertNotNull(objectFactory);

        // default constructor
        {
            TestBeanWithDefaultConstructor bean1 = objectFactory.newInstance(TestBeanWithDefaultConstructor.class);
            assertNotNull(bean1);
            TestBeanWithDefaultConstructor bean2 = objectFactory.newInstance(TestBeanWithDefaultConstructor.class);
            assertNotNull(bean2);
            assertNotEquals(bean1, bean2);
        }

        // java.lang.String
        {
            TestBeanWithStringConstructor bean1 = objectFactory.newInstance(TestBeanWithStringConstructor.class, "myTestString");
            assertNotNull(bean1);
            assertEquals(bean1.getTestString(), "myTestString");
            TestBeanWithStringConstructor bean2 = objectFactory.newInstance(TestBeanWithStringConstructor.class, "myTestString");
            assertNotNull(bean2);
            assertEquals(bean2.getTestString(), "myTestString");
            assertNotEquals(bean1, bean2);
        }

        // int (primitive)
        {
            TestBeanWithPrimitiveIntegerConstructor bean1 = objectFactory.newInstance(TestBeanWithPrimitiveIntegerConstructor.class, 1234);
            assertNotNull(bean1);
            assertEquals(bean1.getTestInt(), 1234);
            TestBeanWithPrimitiveIntegerConstructor bean2 = objectFactory.newInstance(TestBeanWithPrimitiveIntegerConstructor.class, 1234);
            assertNotNull(bean2);
            assertEquals(bean2.getTestInt(), 1234);
            assertNotEquals(bean1, bean2);
        }

        // java.lang.Integer
        {
            TestBeanWithIntegerConstructor bean1 = objectFactory.newInstance(TestBeanWithIntegerConstructor.class, 1234);
            assertNotNull(bean1);
            assertEquals(bean1.getTestInteger(), new Integer(1234));
            TestBeanWithIntegerConstructor bean2 = objectFactory.newInstance(TestBeanWithIntegerConstructor.class, 1234);
            assertNotNull(bean2);
            assertEquals(bean2.getTestInteger(), new Integer(1234));
            assertNotEquals(bean1, bean2);
        }

        // java.lang.Enum
        {
            TestBeanWithEnumConstructor bean1 = objectFactory.newInstance(TestBeanWithEnumConstructor.class, TestEnum.ONE);
            assertNotNull(bean1);
            assertEquals(bean1.getTestEnum(), TestEnum.ONE);
            TestBeanWithEnumConstructor bean2 = objectFactory.newInstance(TestBeanWithEnumConstructor.class, TestEnum.ONE);
            assertNotNull(bean2);
            assertEquals(bean2.getTestEnum(), TestEnum.ONE);
            assertNotEquals(bean1, bean2);
        }
    }

    @Test
    public void factoryRegisterArrayLiteralPositiveTets() {
        TestObjectFactory objectFactory = DI.createObjectFactory(TestObjectFactory.class, PACKAGE_PREFIX);
        assertNotNull(objectFactory);

        // java.lang.String[]
        {
            List<String> testStrings = new ArrayList<String>();
            testStrings.add("one");
            testStrings.add("two");
            testStrings.add("three");
            objectFactory.registerStringLiteralArray("testStrings", testStrings.toArray(new String[testStrings.size()]));
            TestBeanWithStringArrayConstructor bean = objectFactory.instance(TestBeanWithStringArrayConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestStrings());
            assertArrayEquals(bean.getTestStrings(), testStrings.toArray());
        }
    }

    @Test
    public void factoryRegisterStringLiteralPositiveTests() {
        TestObjectFactory objectFactory = DI.createObjectFactory(TestObjectFactory.class, PACKAGE_PREFIX);
        assertNotNull(objectFactory);

        // java.lang.String
        {
            objectFactory.registerStringLiteral("testString", "myTestString");
            TestBeanWithStringConstructor bean = objectFactory.instance(TestBeanWithStringConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestString());
            assertEquals(bean.getTestString(), "myTestString");
        }

        // java.util.Date
        {
            objectFactory.registerStringLiteral("testDate", "2010-01-01T12:00:00Z");
            TestBeanWithDateConstructor bean = objectFactory.instance(TestBeanWithDateConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestDate());
            assertEquals(bean.getTestDate(), DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z").getTime());
        }

        // byte (primitive)
        {
            objectFactory.registerStringLiteral("testByte", Byte.toString(Byte.MAX_VALUE));
            TestBeanWithPrimitiveByteConstructor bean = objectFactory.instance(TestBeanWithPrimitiveByteConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestByte(), Byte.MAX_VALUE);
        }

        // java.lang.Byte
        {
            objectFactory.registerStringLiteral("testByte", Byte.toString(Byte.MAX_VALUE));
            TestBeanWithByteConstructor bean = objectFactory.instance(TestBeanWithByteConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestByte());
            assertEquals(bean.getTestByte(), new Byte(Byte.MAX_VALUE));
        }

        // short (primitive)
        {
            objectFactory.registerStringLiteral("testShort", Short.toString(Short.MAX_VALUE));
            TestBeanWithPrimitiveShortConstructor bean = objectFactory.instance(TestBeanWithPrimitiveShortConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestShort(), Short.MAX_VALUE);
        }

        // java.lang.Short
        {
            objectFactory.registerStringLiteral("testShort", Short.toString(Short.MAX_VALUE));
            TestBeanWithShortConstructor bean = objectFactory.instance(TestBeanWithShortConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestShort());
            assertEquals(bean.getTestShort(), new Short(Short.MAX_VALUE));
        }

        // int (primitive)
        {
            objectFactory.registerStringLiteral("testInt", Integer.toString(Integer.MAX_VALUE));
            TestBeanWithPrimitiveIntegerConstructor bean = objectFactory.instance(TestBeanWithPrimitiveIntegerConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestInt(), Integer.MAX_VALUE);
        }

        // java.lang.Integer
        {
            objectFactory.registerStringLiteral("testInteger", Integer.toString(Integer.MAX_VALUE));
            TestBeanWithIntegerConstructor bean = objectFactory.instance(TestBeanWithIntegerConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestInteger());
            assertEquals(bean.getTestInteger(), new Integer(Integer.MAX_VALUE));
        }

        // long (primitive)
        {
            objectFactory.registerStringLiteral("testLong", Long.toString(Long.MAX_VALUE));
            TestBeanWithPrimitiveLongConstructor bean = objectFactory.instance(TestBeanWithPrimitiveLongConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestLong(), Long.MAX_VALUE);
        }

        // java.lang.Long
        {
            objectFactory.registerStringLiteral("testLong", Long.toString(Long.MAX_VALUE));
            TestBeanWithLongConstructor bean = objectFactory.instance(TestBeanWithLongConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestLong());
            assertEquals(bean.getTestLong(), new Long(Long.MAX_VALUE));
        }

        // float (primitive)
        {
            objectFactory.registerStringLiteral("testFloat", Float.toString(Float.MAX_VALUE));
            TestBeanWithPrimitiveFloatConstructor bean = objectFactory.instance(TestBeanWithPrimitiveFloatConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestFloat(), Float.MAX_VALUE, 0.0);
        }

        // java.lang.Float
        {
            objectFactory.registerStringLiteral("testFloat", Float.toString(Float.MAX_VALUE));
            TestBeanWithFloatConstructor bean = objectFactory.instance(TestBeanWithFloatConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestFloat());
            assertEquals(bean.getTestFloat(), Float.MAX_VALUE, 0.0);
        }

        // double (primitive)
        {
            objectFactory.registerStringLiteral("testDouble", Double.toString(Double.MAX_VALUE));
            TestBeanWithPrimitiveDoubleConstructor bean = objectFactory.instance(TestBeanWithPrimitiveDoubleConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestDouble(), Double.MAX_VALUE, 0.0);
        }

        // java.lang.Double
        {
            objectFactory.registerStringLiteral("testDouble", Double.toString(Double.MAX_VALUE));
            TestBeanWithDoubleConstructor bean = objectFactory.instance(TestBeanWithDoubleConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestDouble());
            assertEquals(bean.getTestDouble(), Double.MAX_VALUE, 0.0);
        }

        // char (primitive)
        {
            // see http://www.fileformat.info/info/unicode/char/48
            objectFactory.registerStringLiteral("testChar", "\\u0048");
            TestBeanWithPrimitiveCharConstructor bean = objectFactory.instance(TestBeanWithPrimitiveCharConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestChar(), 'H');
        }

        // java.lang.Character
        {
            // see http://www.fileformat.info/info/unicode/char/57
            objectFactory.registerStringLiteral("testCharacter", "\\u0057");
            TestBeanWithCharacterConstructor bean = objectFactory.instance(TestBeanWithCharacterConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestCharacter());
            assertEquals(bean.getTestCharacter(), Character.valueOf('W'));
        }

        // boolean (primitive)
        {
            objectFactory.registerStringLiteral("testBoolean", Boolean.toString(true));
            TestBeanWithPrimitiveBooleanConstructor bean = objectFactory.instance(TestBeanWithPrimitiveBooleanConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestBoolean(), true);
        }

        // java.lang.Boolean
        {
            objectFactory.registerStringLiteral("testBoolean", Boolean.toString(true));
            TestBeanWithBooleanConstructor bean = objectFactory.instance(TestBeanWithBooleanConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestBoolean());
            assertEquals(bean.getTestBoolean(), true);
        }

        // java.lang.Enum
        {
            objectFactory.registerStringLiteral("testEnum", TestEnum.ONE.toString());
            TestBeanWithEnumConstructor bean = objectFactory.instance(TestBeanWithEnumConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestEnum(), TestEnum.ONE);
        }
    }

    @Test
    public void factoryInstanceNegativeTests() {
        TestObjectFactory objectFactory = DI.createObjectFactory(TestObjectFactory.class, PACKAGE_PREFIX);
        assertNotNull(objectFactory);

        try {
            objectFactory.instance(TestBeanButNoImpl.class);
            fail("should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            objectFactory.instance(TestBeanPrivateDefaultConstructor.class);
            fail("should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException ex) {
            // good!
        }
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
            DI.createObjectFactory(TestObjectFactory.class, null);
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            DI.createObjectFactory(TestObjectFactory.class, "");
            fail("should throw an IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

}
