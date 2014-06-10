package ch.dkitc.ridioc.test;

import javax.xml.bind.DatatypeConverter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactoryTest extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactoryTest.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactoryTest() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void instancePositiveTests() {
        instancePositiveTests_checkJavaLangString();
        instancePositiveTests_checkJavaLangEnum();
        instancePositiveTests_checkJavaLangDate();

        // types
        instancePositiveTests_checkSingleType();
        instancePositiveTests_checkSingleTypeWithDependencies();
        instancePositiveTests_checkMultipleTypes();
        instancePositiveTests_checkMultipleTypesWithDependencies();
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

}