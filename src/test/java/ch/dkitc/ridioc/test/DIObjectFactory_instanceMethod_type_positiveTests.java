package ch.dkitc.ridioc.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.*;
import ch.dkitc.ridioc.test.base.DIBaseTest;
import ch.dkitc.ridioc.test.impl.BeanWithMultipleImplsImpl1;

public class DIObjectFactory_instanceMethod_type_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_type_positiveTests.class.getPackage().getName();

    private static final int NUMBER_OF_BEAN_WITH_MULTIPLE_IMPLS_TYPES = 3;

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_type_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    // checkTypeListInjectedIntoDifferentTypes();

    @Test
    public void checkSingleType() {
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

    @Test
    public void checkSingleTypeWithDependencies() {
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

    @Test
    public void checkMultipleTypes() {
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

    @Test
    public void instancePositiveTests_checkMultipleTypesWithDependencies() {
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

    @Test
    public void checkTypeArray() {
        // BeanWithTypeArrayConstructor bean1
        BeanWithMultipleImplsArrayConstructor bean1 = objectFactory.instance(BeanWithMultipleImplsArrayConstructor.class);
        assertNotNull(bean1);
        BeanWithMultipleImpls[] beanWithMultipleImplsArray1 = bean1.getMultipleImplsArray();
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

}
