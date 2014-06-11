package ch.dkitc.ridioc.test;

import javax.xml.bind.DatatypeConverter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.test.api.BeanWithDateConstructor;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_instanceMethod_date_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_date_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_date_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
    }

    @Test
    public void checkJavaLangDate() {
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

}
