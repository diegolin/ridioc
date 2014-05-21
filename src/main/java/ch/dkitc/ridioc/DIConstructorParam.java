package ch.dkitc.ridioc;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public Class<?> getComponentType() {
        return type.getComponentType();
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
        return isArray() && isComponentTypeInstanceOf(Date.class);
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
        return !isArray() && isInstanceOf(String.class);
    }

    public boolean isDate() {
        return !isArray() && isInstanceOf(Date.class);
    }

    public boolean isCharacter() {
        return !isArray() && isInstanceOf(Character.class);
    }

    public boolean isBoolean() {
        return !isArray() && isInstanceOf(Boolean.class);
    }

    public boolean isLiteral() {
        return isNumber() || isPrimitive() || isEnum() || isString() || isDate() || isCharacter() || isBoolean();
    }

    public boolean isIterable() {
        return !isArray() && isInstanceOf(Iterable.class);
    }

    public boolean isSet() {
        return !isArray() && isInstanceOf(Set.class);
    }

    public boolean isList() {
        return !isArray() && isInstanceOf(List.class);
    }

    public boolean isCollection() {
        return !isArray() && isInstanceOf(Collection.class);
    }

    public boolean isTypeFactory() {
        return !isArray() && isInstanceOf(DITypeFactory.class);
    }

    private boolean isInstanceOf(Class<?> givenType) {
        return givenType.isAssignableFrom(type);
    }

    private boolean isComponentTypeInstanceOf(Class<?> givenType) {
        return givenType.isAssignableFrom(getComponentType());
    }
}
