package ch.dkitc.ridioc.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.DIUtils;
import ch.dkitc.ridioc.test.api.BeanWithFloatConstructor;
import ch.dkitc.ridioc.test.api.BeanWithPrimitiveFloatArrayConstructor;
import ch.dkitc.ridioc.test.api.BeanWithPrimitiveFloatConstructor;
import ch.dkitc.ridioc.test.base.DIBaseTest;

public class DIObjectFactory_instanceMethod_float_positiveTests extends DIBaseTest {

    private static final String PACKAGE_PREFIX = DIObjectFactory_instanceMethod_float_positiveTests.class.getPackage().getName();

    private DIObjectFactory objectFactory;

    public DIObjectFactory_instanceMethod_float_positiveTests() {
        super(PACKAGE_PREFIX);
    }

    @Before
    public void initialize() {
        objectFactory = createObjectFactory();
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
    public void checkPrimitiveFloatArray() {
        // register string literal array for floats
        Float[] testFloats = new Float[]{Float.MAX_VALUE, -200.0f, -20.0f, -10.0f, -1.0f, -0.001f, 0.0f, 0.001f, 1.0f, 2.0f, 10.0f, 20.0f, 200.0f, Float.MAX_VALUE};
        objectFactory.registerStringLiteralArray("testFloats", testFloats);

        // create BeanWithPrimitiveFloatArrayConstructor bean1
        BeanWithPrimitiveFloatArrayConstructor bean1 = objectFactory.instance(BeanWithPrimitiveFloatArrayConstructor.class);
        assertNotNull(bean1);
        assertNotNull(bean1.getTestFloats());
        assertArrayEquals((float[]) DIUtils.unboxToPrimitiveFloatArray(testFloats), bean1.getTestFloats(), /* delta */ 0.0f);

        // create BeanWithPrimitiveFloatArrayConstructor bean2
        BeanWithPrimitiveFloatArrayConstructor bean2 = objectFactory.instance(BeanWithPrimitiveFloatArrayConstructor.class);
        assertNotNull(bean2);
        assertNotNull(bean2.getTestFloats());
        assertArrayEquals((float[]) DIUtils.unboxToPrimitiveFloatArray(testFloats), bean2.getTestFloats(), /* delta */ 0.0f);

        // perform cross-checks
        assertEquals(bean1, bean2);
        assertTrue(bean1 == bean2);
        assertArrayEquals(bean1.getTestFloats(), bean2.getTestFloats(), /* delta */ 0.0f);
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

}
