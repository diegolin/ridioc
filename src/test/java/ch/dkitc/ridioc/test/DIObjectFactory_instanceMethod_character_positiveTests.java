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

public class DIObjectFactory_instanceMethod_character_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_character_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_character_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
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
    public void checkPrimitiveCharacterArray() {
        // register string literal array for chars
        String[] testChars = new String[]{"\\u0048", "\\u0057"};
        objectFactory.registerStringLiteralArray("testChars", testChars);

        char[] primitiveTestChars = (char[]) DIUtils.unboxToPrimitiveCharacterArray(testChars);

        // create BeanWithPrimitiveCharacterArrayConstructor bean1
        BeanWithPrimitiveCharacterArrayConstructor bean1 = objectFactory.instance(BeanWithPrimitiveCharacterArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestChars());
        assertArrayEquals(primitiveTestChars, bean1.getTestChars());

        // create BeanWithPrimitiveCharacterArrayConstructor bean2
        BeanWithPrimitiveCharacterArrayConstructor bean2 = objectFactory.instance(BeanWithPrimitiveCharacterArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestChars());
        assertArrayEquals(primitiveTestChars, bean2.getTestChars());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestChars(), bean2.getTestChars());
    }

    @Test
    public void checkJavaLangCharacter() {
        // see http://www.fileformat.info/info/unicode/char/57
        objectFactory.registerStringLiteral("testCharacter", "\\u0057");

        // BeanWithDoubleConstructor bean1
        BeanWithJavaLangCharacterConstructor bean1 = objectFactory.instance(BeanWithJavaLangCharacterConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestCharacter());
        assertEquals(bean1.getTestCharacter(), Character.valueOf('W'));

        // BeanWithDoubleConstructor bean2
        BeanWithJavaLangCharacterConstructor bean2 = objectFactory.instance(BeanWithJavaLangCharacterConstructor.class);
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
    public void checkJavaLangCharacterArray() {
        // register string literal array for chars
        String[] unicodeCharacterArray = new String[]{"\\u0048", "\\u0057"};
        objectFactory.registerStringLiteralArray("testCharacters", unicodeCharacterArray);

        Character[] testCharacters = DIUtils.convertToJavaLangCharacterArray(unicodeCharacterArray);

        // create BeanWithJavaLangCharacterArrayConstructor bean1
        BeanWithJavaLangCharacterArrayConstructor bean1 = objectFactory.instance(BeanWithJavaLangCharacterArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestCharacters());
        assertArrayEquals(testCharacters, bean1.getTestCharacters());

        // create BeanWithJavaLangCharacterArrayConstructor bean2
        BeanWithJavaLangCharacterArrayConstructor bean2 = objectFactory.instance(BeanWithJavaLangCharacterArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestCharacters());
        assertArrayEquals(testCharacters, bean2.getTestCharacters());

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestCharacters(), bean2.getTestCharacters());
    }

    @Test
    public void checkJavaLangCharacterList() {
        // register string literal array for chars
        String[] unicodeCharacterArray = new String[]{"\\u0048", "\\u0057"};
        objectFactory.registerStringLiteralArray("testCharacters", unicodeCharacterArray);

        Character[] testCharacters = DIUtils.convertToJavaLangCharacterArray(unicodeCharacterArray);

        // create BeanWithJavaLangCharacterListConstructor bean1
        BeanWithJavaLangCharacterListConstructor bean1 = objectFactory.instance(BeanWithJavaLangCharacterListConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestCharacters());
        assertEquals(Arrays.asList(testCharacters), bean1.getTestCharacters());
        assertFalse(testCharacters == bean1.getTestCharacters().toArray());

        // create BeanWithJavaLangCharacterListConstructor bean1
        BeanWithJavaLangCharacterListConstructor bean2 = objectFactory.instance(BeanWithJavaLangCharacterListConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestCharacters());
        assertEquals(Arrays.asList(testCharacters), bean2.getTestCharacters());
        assertFalse(testCharacters == bean2.getTestCharacters().toArray());

        // perform cross checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertEquals(bean1.getTestCharacters(), bean2.getTestCharacters());
        assertTrue(bean1.getTestCharacters() == bean2.getTestCharacters());
    }

}

