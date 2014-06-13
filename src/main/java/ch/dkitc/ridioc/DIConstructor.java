package ch.dkitc.ridioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.thoughtworks.paranamer.Paranamer;
import static ch.dkitc.ridioc.DIUtils.*;

public class DIConstructor implements Iterable<DIConstructorParam> {

    public static final Pattern EXTRACT_CONSTRUCTOR_PARAM_TYPES = Pattern.compile("\\((.*?)\\)");
    public static final Pattern EXTRACT_GENERIC_PARAM_TYPES = Pattern.compile("<(.*?)>");

    private final Constructor<?> constructor;
    private final Paranamer paranamer;
    private List<String> paramNames;
    private List<List<Class<?>>> genericParamTypesList;

    public DIConstructor(Constructor<?> constructor, Paranamer paranamer) {
        if (constructor == null) {
            throw new IllegalArgumentException("'constructor' must not be NULL");
        }
        if (paranamer == null) {
            throw new IllegalArgumentException("'paranemer' must not be NULL");
        }
        this.constructor = constructor;
        this.paranamer = paranamer;
    }

    public String getName() {
        return constructor.getDeclaringClass().getName();
    }

    @Override
    public String toString() {
        return "DIConstructor{" +
                "constructor=" + constructor +
                ", paranamer=" + paranamer +
                ", paramNames=" + paramNames +
                ", genericParamTypesList=" + genericParamTypesList +
                '}';
    }

    public boolean matchesParams(Object ... givenParams) {
        List<Class<?>> givenParamTypes = new ArrayList<Class<?>>();
        for (Object givenParam : givenParams) {
            if (givenParam ==  null) {
                throw new IllegalArgumentException("Given param must NOT be null");
            }
            givenParamTypes.add(givenParam.getClass());
        }
        return matchesParamTypes(givenParamTypes.toArray(new Class[givenParamTypes.size()]));
    }

    public boolean matchesParamTypes(Class<?>... givenParamTypes) {
        Class<?>[] constructorParamTypesArray = constructor.getParameterTypes();
        if (givenParamTypes.length != constructorParamTypesArray.length) {
            // leave early
            return false;
        }

        for (int i=0; i<constructorParamTypesArray.length; i++) {
            Class<?> paramType = constructorParamTypesArray[i];
            Class<?> wrappedParamType;
            if (paramType.isPrimitive()) {
                wrappedParamType = getWrappedPrimitiveType(paramType);
                if (wrappedParamType == null) {
                    throw new IllegalStateException("there is no wrapped type available for primitive type '" + paramType + '"');
                }
            } else {
                wrappedParamType = paramType;
            }
            if (!wrappedParamType.isAssignableFrom(givenParamTypes[i])) {
                return false;
            }
        }

        // if we're here, everything is alrite!
        return true;
    }

    public <T> T newInstance(List<Object> initArgsAsList) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return newInstance(initArgsAsList.toArray());
    }

    public <T> T newInstance(Object ... initArgs) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Object [] realInitArgs = new Object[initArgs.length];
        List<DIConstructorParam> constrParams = Lists.newArrayList(iterator());

        // sanity check!
        if (initArgs.length != constrParams.size()) {
            throw new IllegalArgumentException("The number of init-args (" + initArgs.length + ") is not equal to the number of the constructor parameters (" + constrParams.size() + ")");
        }

        for (int i=0; i<initArgs.length; i++) {
            Object initArg = initArgs[i];
            Class<?> initArgClass = initArg.getClass();
            DIConstructorParam constrParam = constrParams.get(i);
            if (constrParam.isArrayOfPrimitives() && initArgClass.isArray() && !initArgClass.getComponentType().isPrimitive()) {
                // we need to convert the corresponding init argument
                if (constrParam.isArrayOfPrimitiveBytes()) {
                    realInitArgs[i] = unboxToPrimitiveByteArray((Byte[]) initArg);
                } else if (constrParam.isArrayOfPrimitiveShorts()) {
                    realInitArgs[i] = unboxToPrimitiveShortArray((Short[]) initArg);
                } else if (constrParam.isArrayOfPrimitiveIntegers()) {
                    realInitArgs[i] = unboxToPrimitiveIntegerArray((Integer[]) initArg);
                } else if (constrParam.isArrayOfPrimitiveLongs()) {
                    realInitArgs[i] = unboxToPrimitiveLongArray((Long[]) initArg);
                } else if (constrParam.isArrayOfPrimitiveFloats()) {
                    realInitArgs[i] = unboxToPrimitiveFloatArray((Float[]) initArg);
                } else if (constrParam.isArrayOfPrimitiveDoubles()) {
                    realInitArgs[i] = unboxToPrimitiveDoubleArray((Double[]) initArg);
                } else if (constrParam.isArrayOfPrimitiveCharacters()) {
                    realInitArgs[i] = unboxToPrimitiveCharacterArray((Character[]) initArg);
                }
                else {
                    throw new IllegalArgumentException(constrParam + " is not (yet) supported");
                }
            } else {
                realInitArgs[i] = initArg;
            }
        }
        return (T) constructor.newInstance(realInitArgs);
    }

    public int getParamCount() {
        return constructor.getParameterTypes().length;
    }

    public String getParamName(int paramIndex) {
        checkParamIndex(paramIndex);
        if (paramIndex < getParamNames().size()) {
            return getParamNames().get(paramIndex);
        } else {
            return "(unknown)";
        }
    }

    public Class<?> getParamType(int paramIndex) {
        checkParamIndex(paramIndex);
        return constructor.getParameterTypes()[paramIndex];
    }

    public List<Annotation> getParamAnnotations(int paramIndex) {
        checkParamIndex(paramIndex);
        List<Annotation> annotations = new ArrayList<Annotation>();
        Collections.addAll(annotations, constructor.getParameterAnnotations()[paramIndex]);
        return annotations;
    }

    public List<Class<?>> getParamGenericTypes(int paramIndex) {
        checkParamIndex(paramIndex);

        if (genericParamTypesList == null) {
            genericParamTypesList = new ArrayList<List<Class<?>>>();
            // public MyBean(MyInjectedBean,java.util.List<MyElementBean>)
            Matcher constructorParamTypesMatcher = EXTRACT_CONSTRUCTOR_PARAM_TYPES.matcher(constructor.toGenericString());
            if (constructorParamTypesMatcher.find()) {
                // MyInjectedBean,java.util.List<MyElementBean>
                for (String paramTypeClassName : constructorParamTypesMatcher.group(1).split(",")) {
                    // try to find generic types, if any
                    Matcher genericParamTypesMatcher = EXTRACT_GENERIC_PARAM_TYPES.matcher(paramTypeClassName);
                    if (genericParamTypesMatcher.find()) {
                        List<Class<?>> types = new ArrayList<Class<?>>();
                        // o.k. we found something
                        for (String genericParamTypeClassName : genericParamTypesMatcher.group(1).split(",")) {
                            // is it a primitive type?
                            Class<?> primitiveType = findPrimitiveType(genericParamTypeClassName);
                            if (primitiveType != null) {
                                types.add(primitiveType);
                            } else {
                                try {
                                    types.add(Class.forName(genericParamTypeClassName));
                                } catch (ClassNotFoundException e) {
                                    throw new IllegalStateException(e);
                                }
                            }
                        }
                        genericParamTypesList.add(types);
                    } else {
                        // nothing was found
                        genericParamTypesList.add(new ArrayList<Class<?>>());
                    }
                }
            }
        }

        return genericParamTypesList.get(paramIndex);
    }

    @Override
    public Iterator<DIConstructorParam> iterator() {
        DIConstructorParams params = new DIConstructorParams();
        for (int i=0; i<getParamCount(); i++) {
            params.add(getParamName(i), getParamType(i), getParamAnnotations(i), getParamGenericTypes(i));
        }
        return params.iterator();
    }

    private void checkParamIndex(int paramIndex) {
        if (paramIndex < 0) {
            throw new IllegalArgumentException(constructor + ": Param index must be 0 or greater, but is " + paramIndex);
        }
        if (paramIndex >= constructor.getParameterTypes().length) {
            throw new IndexOutOfBoundsException(constructor + ": Invalid parameter index " + paramIndex + " - constructor only " + constructor.getParameterTypes().length + " parameters");
        }
    }

    private List<String> getParamNames() {
        if (paramNames == null) {
            paramNames = new ArrayList<String>();
            Collections.addAll(paramNames, paranamer.lookupParameterNames(constructor));
        }
        return paramNames;
    }

    private static Class<?> findPrimitiveType(String name) {
        if (name.equals("byte")) {
            return byte.class;
        }
        if (name.equals("short")) {
            return short.class;
        }
        if (name.equals("int")) {
            return int.class;
        }
        if (name.equals("long")) {
            return long.class;
        }
        if (name.equals("char")) {
            return char.class;
        }
        if (name.equals("float")) {
            return float.class;
        }
        if (name.equals("double")) {
            return double.class;
        }
        if (name.equals("boolean")) {
            return boolean.class;
        }
        if (name.equals("void")) {
            return void.class;
        }

        return null;
    }
}
