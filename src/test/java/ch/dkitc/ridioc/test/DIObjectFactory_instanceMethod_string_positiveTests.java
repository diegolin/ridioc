package ch.dkitc.ridioc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.BeanWithStringArrayConstructor;
import ch.dkitc.ridioc.test.api.BeanWithStringConstructor;
import ch.dkitc.ridioc.test.api.BeanWithStringListConstructor;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_instanceMethod_string_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_string_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_string_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    // checkJavaLangStringArrayAndList();

    @Test
    public void checkJavaLangString() {
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
}
