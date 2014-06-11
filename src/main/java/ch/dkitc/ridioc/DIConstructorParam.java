package ch.dkitc.ridioc;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

import static ch.dkitc.ridioc.DIUtils.checkList;
import static ch.dkitc.ridioc.DIUtils.checkName;
import static ch.dkitc.ridioc.DIUtils.checkType;

public class DIConstructorParam {

    private final String name;
    private final Class<?> type;
    private final List<Annotation> annotations;
    private final List<Class<?>> genericTypes;

    public DIConstructorParam(String name, Class<?> type, List<Annotation> annotations, List<Class<?>> genericTypes) {
        this.name = checkName(name);
        this.type = checkType(type);
        this.annotations = checkList(annotations, "annotations");
        this.genericTypes = checkList(genericTypes, "genericType");
    }

    @Override
    public String toString() {
        return "ConstructorParam{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", annotations=" + annotations +
                ", generic types=" + genericTypes +
                '}';
    }

    public Class<?> getType() {
        return type;
    }

    public Class<?> getComponentType() {
        if (!isArray()) {
            throw new IllegalStateException("Only applicable to arrays: " + type + " is NOT an array");
        }
        return type.getComponentType();
    }

    public Class<?> getListType() {
        if (!isList()) {
            throw new IllegalStateException("Only applicable to lists: " + type + " is NOT an list");
        }
        switch (genericTypes.size()) {
            case 0:
                // generic type is not available
                // fallback to base class
                return Object.class;
            case 1:
                return genericTypes.get(0);
            default:
                // that does not make sense
                throw new IllegalStateException(type + ": is a list type which should habe zero or one generic types but has " + genericTypes.size() + " generic types: " + genericTypes);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isArray() {
        return type.isArray();
    }

    public boolean isArrayOfPrimitives() {
        return isArray() && getComponentType().isPrimitive();
    }

    public boolean isArrayOfPrimitiveBytes() {
        return isArrayOfPrimitives() && isComponentType(byte.class);
    }

    public boolean isArrayOfPrimitiveShorts() {
        return isArrayOfPrimitives() && isComponentType(short.class);
    }

    public boolean isArrayOfPrimitiveIntegers() {
        return isArrayOfPrimitives() && isComponentType(int.class);
    }

    public boolean isArrayOfPrimitiveLongs() {
        return isArrayOfPrimitives() && isComponentType(long.class);
    }

    public boolean isArrayOfPrimitiveFloats() {
        return isArrayOfPrimitives() && isComponentType(float.class);
    }

    public boolean isArrayOfArrays() {
        return isArray() && getComponentType().isArray();
    }

    public boolean isArrayOfEnums() {
        return isArray() && getComponentType().isEnum();
    }

    public boolean isArrayOfStrings() {
        return isArray() && isComponentTypeInstanceOf(String.class);
    }

    public boolean isArrayOfNumbers() {
        return isArray() && isComponentTypeInstanceOf(Number.class);
    }

    public boolean isArrayOfDates() {
        return isArray() && isComponentType(Date.class);
    }

    public boolean isArrayOfCharacters() {
        return isArray() && isComponentType(Character.class);
    }

    public boolean isArrayOfBooleans() {
        return isArray() && isComponentType(Boolean.class);
    }

    public boolean isNumber() {
        return !isArray() && isInstanceOf(Number.class);
    }

    public boolean isPrimitive() {
        return !isArray() && type.isPrimitive();
    }

    public boolean isEnum() {
        return !isArray() && type.isEnum();
    }

    public boolean isString() {
        return !isArray() && is(String.class);
    }

    public boolean isDate() {
        return !isArray() && is(Date.class);
    }

    public boolean isCharacter() {
        return !isArray() && is(Character.class);
    }

    public boolean isBoolean() {
        return !isArray() && is(Boolean.class);
    }

    public boolean isLiteral() {
        return isNumber() || isPrimitive() || isEnum() || isString() || isDate() || isCharacter() || isBoolean();
    }

    public boolean isArrayOfLiterals() {
        return isArrayOfNumbers() || isArrayOfPrimitives() || isArrayOfEnums() || isArrayOfStrings() || isArrayOfDates() || isArrayOfCharacters() || isArrayOfBooleans();
    }

    public boolean isList() {
        return !isArray() && is(List.class);
    }

    public boolean isListOfLiterals() {
        return isListOfNumbers() || isListOfEnums() || isListOfStrings() || isListOfDates() || isListOfCharacters() || isListOfBooleans();
    }

    public boolean isListOfNumbers() {
        return isList() && isListTypeInstanceOf(Number.class);
    }

    public boolean isListOfEnums() {
        return isList() && getListType().isEnum();
    }

    public boolean isListOfStrings() {
        return isList() && isListTypeInstanceOf(String.class);
    }

    public boolean isListOfDates() {
        return isList() && isListTypeInstanceOf(Date.class);
    }

    public boolean isListOfCharacters() {
        return isList() && isListTypeInstanceOf(Character.class);
    }

    public boolean isListOfBooleans() {
        return isList() && isListTypeInstanceOf(Boolean.class);
    }

    public boolean isListOfArrays() {
        return isList() && getListType().isArray();
    }

    public Class<?> getListOrArrayElementType() {
        if (isArray()) {
            return getComponentType();
        }
        if (isList()) {
            return getListType();
        }
        throw new IllegalStateException("Only applicable to arrays OR lists: " + type + " is NEITHER NOR");
    }

    public boolean isListOrArray() {
        return isList() || isArray();
    }

    public boolean isListOrArrayOfLiterals() {
        return isListOfLiterals() || isArrayOfLiterals();
    }

    private boolean is(Class<?> givenType) {
        return givenType == type;
    }

    private boolean isInstanceOf(Class<?> givenType) {
        return givenType.isAssignableFrom(type);
    }

    private boolean isComponentType(Class<?> givenType) {
        return givenType == type.getComponentType();
    }

    private boolean isComponentTypeInstanceOf(Class<?> givenType) {
        return givenType.isAssignableFrom(type.getComponentType());
    }

    private boolean isListTypeInstanceOf(Class<?> givenType) {
        return givenType.isAssignableFrom(getListType());
    }
}
