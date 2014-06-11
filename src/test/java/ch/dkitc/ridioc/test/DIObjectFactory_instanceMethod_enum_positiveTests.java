package ch.dkitc.ridioc.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.BeanWithEnumConstructor;
import ch.dkitc.ridioc.test.api.MyEnum;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_instanceMethod_enum_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_enum_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_enum_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void checkJavaLangEnum() {
        objectFactory.registerInstance(MyEnum.class, MyEnum.ONE);
        BeanWithEnumConstructor bean1 = objectFactory.instance(BeanWithEnumConstructor.class);
        assertNotNull(bean1);
        assertEquals(bean1.getTestEnum(), MyEnum.ONE);
        BeanWithEnumConstructor bean2 = objectFactory.instance(BeanWithEnumConstructor.class);
        assertNotNull(bean2);
        assertEquals(bean2.getTestEnum(), MyEnum.ONE);
        assertEquals(bean1, bean2);
    }
}
