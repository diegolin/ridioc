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

public class DIObjectFactory_instanceMethod_byte_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_byte_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_byte_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
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
    public void checkJavaLangByte() {
        Byte testByte = Byte.MAX_VALUE;
        objectFactory.registerStringLiteral("testByte", testByte);

        // BeanWithByteConstructor bean1
        BeanWithJavaLangByteConstructor bean1 = objectFactory.instance(BeanWithJavaLangByteConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestByte());
        assertEquals(bean1.getTestByte(), testByte);

        // BeanWithByteConstructor bean2
        BeanWithJavaLangByteConstructor bean2 = objectFactory.instance(BeanWithJavaLangByteConstructor.class);
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

}
