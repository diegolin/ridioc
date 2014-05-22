package ch.dkitc.ridioc.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.dkitc.ridioc.DI;
import ch.dkitc.ridioc.DIConstructor;
import ch.dkitc.ridioc.test.api.AnotherBeanWithDefaultConstructor;
import ch.dkitc.ridioc.test.api.BeanWithDefaultConstructor;
import ch.dkitc.ridioc.test.api.BeanWithMultipleImpls;
import ch.dkitc.ridioc.test.api.MyEnum;
import ch.dkitc.ridioc.test.impl.*;

public class DIConstructorTest {

    private Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap;

    public DIConstructorTest() {
    }

    @Before
    public void setUp() {
        wrappedPrimitiveTypeMap = DI.createWrappedPrimitiveTypeMap();
    }

    @Test
    public void constructorPositiveTests() {
        assertTrue(BeanWithDefaultConstructorImpl.class.getConstructors().length >=1 );
        new DIConstructor(BeanWithDefaultConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);

        assertTrue(AnotherBeanWithDefaultConstructorImpl.class.getConstructors().length >= 1);
        new DIConstructor(AnotherBeanWithDefaultConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
    }

    @Test
    public void constructorNegativeTests() {
        try {
            new DIConstructor(null, wrappedPrimitiveTypeMap);
            fail("should throw 'java.lang.IllegalArgumentExeption'");
        }
        catch (IllegalArgumentException ex) {
            // o.k.
        }

        // this is to make sure that we got at least ONE constructor
        assertTrue(AnotherBeanWithDefaultConstructorImpl.class.getConstructors().length >= 1);
        try {
            new DIConstructor(AnotherBeanWithDefaultConstructorImpl.class.getConstructors()[0], null);
            fail("should throw 'java.lang.IllegalArgumentExeption'");
        }
        catch (IllegalArgumentException ex) {
            // o.k.
        }
    }

    @Test
    public void getParamXyzPositiveTests() {
        // arrays
        getParamXyzPositiveTests_checkJavaLangStringArray();
        getParamXyzPositiveTests_checkTypeArray();

        // lists
        getParamXyzPositiveTests_checkJavaLangStringList();
        //getParamXyzPositiveTests_checkTypeList();

        // literals
        getParamXyzPositiveTests_checkPrimitiveByte();
        getParamXyzPositiveTests_checkJavaLangByte();
        getParamXyzPositiveTests_checkPrimitiveShort();
        getParamXyzPositiveTests_checkJavaLangShort();
        getParamXyzPositiveTests_checkPrimitiveInteger();
        getParamXyzPositiveTests_checkJavaLangInteger();
        getParamXyzPositiveTests_checkPrimitiveLong();
        getParamXyzPositiveTests_checkJavaLangLong();
        getParamXyzPositiveTests_checkPrimitiveFloat();
        getParamXyzPositiveTests_checkJavaLangFloat();
        getParamXyzPositiveTests_checkPrimitiveDouble();
        getParamXyzPositiveTests_checkJavaLangDouble();
        getParamXyzPositiveTests_checkPrimitiveCharacter();
        getParamXyzPositiveTests_checkJavaLangCharacter();
        getParamXyzPositiveTests_checkPrimitiveBoolean();
        getParamXyzPositiveTests_checkJavaLangBoolean();
        getParamXyzPositiveTests_checkJavaMathBigDecimal();
        getParamXyzPositiveTests_checkJavaMathBigInteger();
        getParamXyzPositiveTests_checkJavaLangString();
        getParamXyzPositiveTests_checkJavaLangEnum();
        getParamXyzPositiveTests_checkJavaLangDate();

        // types
        getParamXyzPositiveTests_checkSingleType();
        getParamXyzPositiveTests_checkMultipleTypes();
    }

    private void getParamXyzPositiveTests_checkJavaLangStringArray() {
        assertTrue(BeanWithStringArrayConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithStringArrayConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testStrings", diConstructor.getParamName(0));
        assertEquals(String[].class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkTypeArray() {
        assertTrue(BeanWithTypeArrayConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithTypeArrayConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("beanWithMultipleImplsArray", diConstructor.getParamName(0));
        assertEquals(BeanWithMultipleImpls[].class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangStringList() {
        assertTrue(BeanWithStringListConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithStringListConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testStrings", diConstructor.getParamName(0));
        assertEquals(List.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(1, diConstructor.getParamGenericTypes(0).size());
        assertEquals(String.class, diConstructor.getParamGenericTypes(0).get(0));
    }

    private void getParamXyzPositiveTests_checkPrimitiveByte() {
        assertTrue(BeanWithPrimitiveByteConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveByteConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testByte", diConstructor.getParamName(0));
        assertEquals(byte.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangByte() {
        assertTrue(BeanWithByteConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithByteConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testByte", diConstructor.getParamName(0));
        assertEquals(Byte.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkPrimitiveShort() {
        assertTrue(BeanWithPrimitiveShortConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveShortConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testShort", diConstructor.getParamName(0));
        assertEquals(short.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangShort() {
        assertTrue(BeanWithShortConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithShortConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testShort", diConstructor.getParamName(0));
        assertEquals(Short.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkPrimitiveInteger() {
        assertTrue(BeanWithPrimitiveIntegerConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveIntegerConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testInt", diConstructor.getParamName(0));
        assertEquals(int.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangInteger() {
        assertTrue(BeanWithIntegerConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithIntegerConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testInteger", diConstructor.getParamName(0));
        assertEquals(Integer.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkPrimitiveLong() {
        assertTrue(BeanWithPrimitiveLongConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveLongConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testLong", diConstructor.getParamName(0));
        assertEquals(long.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangLong() {
        assertTrue(BeanWithLongConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithLongConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testLong", diConstructor.getParamName(0));
        assertEquals(Long.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkPrimitiveFloat() {
        assertTrue(BeanWithPrimitiveFloatConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveFloatConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testFloat", diConstructor.getParamName(0));
        assertEquals(float.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangFloat() {
        assertTrue(BeanWithFloatConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithFloatConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testFloat", diConstructor.getParamName(0));
        assertEquals(Float.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkPrimitiveDouble() {
        assertTrue(BeanWithPrimitiveDoubleConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveDoubleConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testDouble", diConstructor.getParamName(0));
        assertEquals(double.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangDouble() {
        assertTrue(BeanWithDoubleConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithDoubleConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testDouble", diConstructor.getParamName(0));
        assertEquals(Double.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkPrimitiveCharacter() {
        assertTrue(BeanWithPrimitiveCharConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveCharConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testChar", diConstructor.getParamName(0));
        assertEquals(char.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangCharacter() {
        assertTrue(BeanWithCharacterConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithCharacterConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testCharacter", diConstructor.getParamName(0));
        assertEquals(Character.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkPrimitiveBoolean() {
        assertTrue(BeanWithPrimitiveBooleanConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithPrimitiveBooleanConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testBoolean", diConstructor.getParamName(0));
        assertEquals(boolean.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangBoolean() {
        assertTrue(BeanWithBooleanConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithBooleanConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testBoolean", diConstructor.getParamName(0));
        assertEquals(Boolean.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaMathBigDecimal() {
        assertTrue(BeanWithBigDecimalConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithBigDecimalConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testBigDecimal", diConstructor.getParamName(0));
        assertEquals(BigDecimal.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaMathBigInteger() {
        assertTrue(BeanWithBigIntegerConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithBigIntegerConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testBigInteger", diConstructor.getParamName(0));
        assertEquals(BigInteger.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangString() {
        assertTrue(BeanWithStringConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithStringConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testString", diConstructor.getParamName(0));
        assertEquals(String.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangEnum() {
        assertTrue(BeanWithEnumConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithEnumConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testEnum", diConstructor.getParamName(0));
        assertEquals(MyEnum.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkJavaLangDate() {
        assertTrue(BeanWithDateConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithDateConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("testDate", diConstructor.getParamName(0));
        assertEquals(Date.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkSingleType() {
        assertTrue(BeanWithSingleTypeConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithSingleTypeConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(1, diConstructor.getParamCount());
        assertEquals("beanWithDefaultConstructor", diConstructor.getParamName(0));
        assertEquals(BeanWithDefaultConstructor.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
    }

    private void getParamXyzPositiveTests_checkMultipleTypes() {
        assertTrue(BeanWithMultipleTypesConstructorImpl.class.getConstructors().length >= 1);
        DIConstructor diConstructor = new DIConstructor(BeanWithMultipleTypesConstructorImpl.class.getConstructors()[0], wrappedPrimitiveTypeMap);
        assertEquals(2, diConstructor.getParamCount());
        assertEquals("beanWithDefaultConstructor", diConstructor.getParamName(0));
        assertEquals(BeanWithDefaultConstructor.class, diConstructor.getParamType(0));
        assertEquals(0, diConstructor.getParamAnnotations(0).size());
        assertEquals(0, diConstructor.getParamGenericTypes(0).size());
        assertEquals("anotherBeanWithDefaultConstructor", diConstructor.getParamName(1));
        assertEquals(AnotherBeanWithDefaultConstructor.class, diConstructor.getParamType(1));
        assertEquals(0, diConstructor.getParamAnnotations(1).size());
        assertEquals(0, diConstructor.getParamGenericTypes(1).size());
    }

}
