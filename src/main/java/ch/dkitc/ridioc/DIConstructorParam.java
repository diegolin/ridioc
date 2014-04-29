package ch.dkitc.ridioc;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

public class DIConstructorParam {

    private final String name;
    private final Class<?> type;
    private final List<Annotation> annotations;

    public DIConstructorParam(String name, Class<?> type, List<Annotation> annotations) {
        this.name = name;
        this.type = type;
        this.annotations = annotations;
    }


    @Override
    public String toString() {
        return "ConstructorParam{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", annotations=" + annotations +
                '}';
    }

    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isArray() {
        return type.isArray();
    }

    public boolean isArrayOfPrimitives() {
        mustBeAnArray();
        return type.getComponentType().isPrimitive();
    }

    public boolean isArrayOfArrays() {
        mustBeAnArray();
        return type.getComponentType().isArray();
    }

    public boolean isArrayOfEnums() {
        mustBeAnArray();
        return type.getComponentType().isEnum();
    }

    public boolean isArrayOfStrings() {
        mustBeAnArray();
        return String.class.isAssignableFrom(type.getComponentType());
    }

    public boolean isArrayOfNumbers() {
        mustBeAnArray();
        return Number.class.isAssignableFrom(type.getComponentType());
    }

    public boolean isArrayOfDates() {
        mustBeAnArray();
        return Date.class.isAssignableFrom(type.getComponentType());
    }

    public boolean isNumber() {
        mustNotBeAnArray();
        return Number.class.isAssignableFrom(type);
    }

    public boolean isPrimitive() {
        mustNotBeAnArray();
        return type.isPrimitive();
    }

    public boolean isEnum() {
        mustNotBeAnArray();
        return type.isEnum();
    }

    public boolean isString() {
        mustNotBeAnArray();
        return String.class.isAssignableFrom(type);
    }

    public boolean isDate() {
        mustNotBeAnArray();
        return Date.class.isAssignableFrom(type);
    }

    public boolean isCharacter() {
        mustNotBeAnArray();
        return Character.class.isAssignableFrom(type);
    }

    public boolean isBoolean() {
        mustNotBeAnArray();
        return Boolean.class.isAssignableFrom(type);
    }

    private void mustBeAnArray() {
        if (!type.isArray()) {
            throw new IllegalStateException(type + " is not an array");
        }
    }

    private void mustNotBeAnArray() {
        if (type.isArray()) {
            throw new IllegalStateException(type + " is an array");
        }
    }

}
