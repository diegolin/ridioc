package ch.dkitc.ridioc.test;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.*;

public class DIObjectFactoryTest extends DIBaseTest {

    private DIObjectFactory objectFactory;

    public DIObjectFactoryTest() {
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void factoryRegisterInstancePositiveTests() {
        factoryRegisterInstancePositiveTests_checkDefaultConstructor();
        factoryRegisterInstancePositiveTests_checkJavaLangString();
        factoryRegisterInstancePositiveTests_checkPrimitiveInteger();
        factoryRegisterInstancePositiveTests_checkJavaLangInteger();
        factoryRegisterInstancePositiveTests_checkJavaLangEnum();
    }

    @Test
    public void factoryNewInstancePositiveTests() {
        factoryNewInstancePositiveTests_checkDefaultConstructor();
        factoryNewInstancePositiveTests_checkJavaLangString();
        factoryNewInstancePositiveTests_checkPrimitiveInteger();
        factoryNewInstancePositiveTests_checkJavaLangInteger();
        factoryNewInstancePositiveTests_checkJavaLangEnum();
    }

    @Test
    public void factoryRegisterArrayLiteralPositiveTests() {
        // java.lang.String[]
        {
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
    }

    @Test
    public void factoryRegisterStringLiteralPositiveTests() {
        // java.lang.String
        {
            objectFactory.registerStringLiteral("testString", "myTestString");
            BeanWithStringConstructor bean = objectFactory.instance(BeanWithStringConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestString());
            assertEquals(bean.getTestString(), "myTestString");
        }

        // java.util.Date
        {
            objectFactory.registerStringLiteral("testDate", "2010-01-01T12:00:00Z");
            BeanWithDateConstructor bean = objectFactory.instance(BeanWithDateConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestDate());
            assertEquals(bean.getTestDate(), DatatypeConverter.parseDateTime("2010-01-01T12:00:00Z").getTime());
        }

        // byte (primitive)
        {
            objectFactory.registerStringLiteral("testByte", Byte.toString(Byte.MAX_VALUE));
            BeanWithPrimitiveByteConstructor bean = objectFactory.instance(BeanWithPrimitiveByteConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestByte(), Byte.MAX_VALUE);
        }

        // java.lang.Byte
        {
            objectFactory.registerStringLiteral("testByte", Byte.toString(Byte.MAX_VALUE));
            BeanWithByteConstructor bean = objectFactory.instance(BeanWithByteConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestByte());
            assertEquals(bean.getTestByte(), new Byte(Byte.MAX_VALUE));
        }

        // short (primitive)
        {
            objectFactory.registerStringLiteral("testShort", Short.toString(Short.MAX_VALUE));
            BeanWithPrimitiveShortConstructor bean = objectFactory.instance(BeanWithPrimitiveShortConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestShort(), Short.MAX_VALUE);
        }

        // java.lang.Short
        {
            objectFactory.registerStringLiteral("testShort", Short.toString(Short.MAX_VALUE));
            BeanWithShortConstructor bean = objectFactory.instance(BeanWithShortConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestShort());
            assertEquals(bean.getTestShort(), new Short(Short.MAX_VALUE));
        }

        // int (primitive)
        {
            objectFactory.registerStringLiteral("testInt", Integer.toString(Integer.MAX_VALUE));
            BeanWithPrimitiveIntegerConstructor bean = objectFactory.instance(BeanWithPrimitiveIntegerConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestInt(), Integer.MAX_VALUE);
        }

        // java.lang.Integer
        {
            objectFactory.registerStringLiteral("testInteger", Integer.toString(Integer.MAX_VALUE));
            BeanWithIntegerConstructor bean = objectFactory.instance(BeanWithIntegerConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestInteger());
            assertEquals(bean.getTestInteger(), new Integer(Integer.MAX_VALUE));
        }

        // long (primitive)
        {
            objectFactory.registerStringLiteral("testLong", Long.toString(Long.MAX_VALUE));
            BeanWithPrimitiveLongConstructor bean = objectFactory.instance(BeanWithPrimitiveLongConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestLong(), Long.MAX_VALUE);
        }

        // java.lang.Long
        {
            objectFactory.registerStringLiteral("testLong", Long.toString(Long.MAX_VALUE));
            BeanWithLongConstructor bean = objectFactory.instance(BeanWithLongConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestLong());
            assertEquals(bean.getTestLong(), new Long(Long.MAX_VALUE));
        }

        // float (primitive)
        {
            objectFactory.registerStringLiteral("testFloat", Float.toString(Float.MAX_VALUE));
            BeanWithPrimitiveFloatConstructor bean = objectFactory.instance(BeanWithPrimitiveFloatConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestFloat(), Float.MAX_VALUE, 0.0);
        }

        // java.lang.Float
        {
            objectFactory.registerStringLiteral("testFloat", Float.toString(Float.MAX_VALUE));
            BeanWithFloatConstructor bean = objectFactory.instance(BeanWithFloatConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestFloat());
            assertEquals(bean.getTestFloat(), Float.MAX_VALUE, 0.0);
        }

        // double (primitive)
        {
            objectFactory.registerStringLiteral("testDouble", Double.toString(Double.MAX_VALUE));
            BeanWithPrimitiveDoubleConstructor bean = objectFactory.instance(BeanWithPrimitiveDoubleConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestDouble(), Double.MAX_VALUE, 0.0);
        }

        // java.lang.Double
        {
            objectFactory.registerStringLiteral("testDouble", Double.toString(Double.MAX_VALUE));
            BeanWithDoubleConstructor bean = objectFactory.instance(BeanWithDoubleConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestDouble());
            assertEquals(bean.getTestDouble(), Double.MAX_VALUE, 0.0);
        }

        // char (primitive)
        {
            // see http://www.fileformat.info/info/unicode/char/48
            objectFactory.registerStringLiteral("testChar", "\\u0048");
            BeanWithPrimitiveCharConstructor bean = objectFactory.instance(BeanWithPrimitiveCharConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestChar(), 'H');
        }

        // java.lang.Character
        {
            // see http://www.fileformat.info/info/unicode/char/57
            objectFactory.registerStringLiteral("testCharacter", "\\u0057");
            BeanWithCharacterConstructor bean = objectFactory.instance(BeanWithCharacterConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestCharacter());
            assertEquals(bean.getTestCharacter(), Character.valueOf('W'));
        }

        // boolean (primitive)
        {
            objectFactory.registerStringLiteral("testBoolean", Boolean.toString(true));
            BeanWithPrimitiveBooleanConstructor bean = objectFactory.instance(BeanWithPrimitiveBooleanConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestBoolean(), true);
        }

        // java.lang.Boolean
        {
            objectFactory.registerStringLiteral("testBoolean", Boolean.toString(true));
            BeanWithBooleanConstructor bean = objectFactory.instance(BeanWithBooleanConstructor.class);
            assertNotNull(bean);
            assertNotNull(bean.getTestBoolean());
            assertEquals(bean.getTestBoolean(), true);
        }

        // java.lang.Enum
        {
            objectFactory.registerStringLiteral("testEnum", MyEnum.ONE.toString());
            BeanWithEnumConstructor bean = objectFactory.instance(BeanWithEnumConstructor.class);
            assertNotNull(bean);
            assertEquals(bean.getTestEnum(), MyEnum.ONE);
        }
    }

    @Test
    public void factoryInstanceNegativeTests() {
        try {
            objectFactory.instance(BeanButNoImpl.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }

        try {
            objectFactory.instance(BeanPrivateDefaultConstructor.class);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // good!
        }
    }

    private void factoryRegisterInstancePositiveTests_checkJavaLangEnum() {
        objectFactory.registerInstance(MyEnum.class, MyEnum.ONE);
        BeanWithEnumConstructor bean1 = objectFactory.instance(BeanWithEnumConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestEnum(), MyEnum.ONE);
        BeanWithEnumConstructor bean2 = objectFactory.instance(BeanWithEnumConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestEnum(), MyEnum.ONE);
        assertEquals(bean1, bean2);
    }

    private void factoryRegisterInstancePositiveTests_checkPrimitiveInteger() {
        objectFactory.registerInstance(int.class, Integer.MAX_VALUE);
        BeanWithPrimitiveIntegerConstructor bean1 = objectFactory.instance(BeanWithPrimitiveIntegerConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInt(), Integer.MAX_VALUE);
        BeanWithPrimitiveIntegerConstructor bean2 = objectFactory.instance(BeanWithPrimitiveIntegerConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInt(), Integer.MAX_VALUE);
        assertEquals(bean1, bean2);
    }

    private void factoryRegisterInstancePositiveTests_checkDefaultConstructor() {
        BeanWithDefaultConstructor testBean1 = objectFactory.instance(BeanWithDefaultConstructor.class);
        assertNotNull(testBean1);
        BeanWithDefaultConstructor testBean2 = objectFactory.instance(BeanWithDefaultConstructor.class);
        assertNotNull(testBean2);
        assertEquals(testBean1, testBean2);
    }

    private void factoryRegisterInstancePositiveTests_checkJavaLangString() {
        objectFactory.registerInstance(String.class, "myTestString");
        BeanWithStringConstructor bean1 = objectFactory.instance(BeanWithStringConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestString(), "myTestString");
        BeanWithStringConstructor bean2 = objectFactory.instance(BeanWithStringConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestString(), "myTestString");
        assertEquals(bean1, bean2);
    }

    private void factoryRegisterInstancePositiveTests_checkJavaLangInteger() {
        objectFactory.registerInstance(Integer.class, Integer.MAX_VALUE);
        BeanWithIntegerConstructor bean1 = objectFactory.instance(BeanWithIntegerConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInteger(), new Integer(Integer.MAX_VALUE));
        BeanWithIntegerConstructor bean2 = objectFactory.instance(BeanWithIntegerConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInteger(), new Integer(Integer.MAX_VALUE));
        assertEquals(bean1, bean2);
    }

    private void factoryNewInstancePositiveTests_checkJavaLangInteger() {
        BeanWithIntegerConstructor bean1 = objectFactory.newInstance(BeanWithIntegerConstructor.class, 1234);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInteger(), new Integer(1234));
        BeanWithIntegerConstructor bean2 = objectFactory.newInstance(BeanWithIntegerConstructor.class, 1234);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInteger(), new Integer(1234));
        assertNotEquals(bean1, bean2);
    }

    private void factoryNewInstancePositiveTests_checkJavaLangString() {
        BeanWithStringConstructor bean1 = objectFactory.newInstance(BeanWithStringConstructor.class, "myTestString");
        assertNotNull(bean1);
        assertEquals(bean1.getTestString(), "myTestString");
        BeanWithStringConstructor bean2 = objectFactory.newInstance(BeanWithStringConstructor.class, "myTestString");
        assertNotNull(bean2);
        assertEquals(bean2.getTestString(), "myTestString");
        assertNotEquals(bean1, bean2);
    }

    private void factoryNewInstancePositiveTests_checkDefaultConstructor() {
        BeanWithDefaultConstructor bean1 = objectFactory.newInstance(BeanWithDefaultConstructor.class);
        assertNotNull(bean1);
        BeanWithDefaultConstructor bean2 = objectFactory.newInstance(BeanWithDefaultConstructor.class);
        assertNotNull(bean2);
        assertNotEquals(bean1, bean2);
    }

    private void factoryNewInstancePositiveTests_checkPrimitiveInteger() {
        BeanWithPrimitiveIntegerConstructor bean1 = objectFactory.newInstance(BeanWithPrimitiveIntegerConstructor.class, 1234);
        assertNotNull(bean1);
        assertEquals(bean1.getTestInt(), 1234);
        BeanWithPrimitiveIntegerConstructor bean2 = objectFactory.newInstance(BeanWithPrimitiveIntegerConstructor.class, 1234);
        assertNotNull(bean2);
        assertEquals(bean2.getTestInt(), 1234);
        assertNotEquals(bean1, bean2);
    }

    private void factoryNewInstancePositiveTests_checkJavaLangEnum() {
        BeanWithEnumConstructor bean1 = objectFactory.newInstance(BeanWithEnumConstructor.class, MyEnum.ONE);
        assertNotNull(bean1);
        assertEquals(bean1.getTestEnum(), MyEnum.ONE);
        BeanWithEnumConstructor bean2 = objectFactory.newInstance(BeanWithEnumConstructor.class, MyEnum.ONE);
        assertNotNull(bean2);
        assertEquals(bean2.getTestEnum(), MyEnum.ONE);
        assertNotEquals(bean1, bean2);
    }
}
