package ch.dkitc.ridioc;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public final class DI {

    public static <FACTORY> FACTORY createObjectFactory(Class<FACTORY> factoryType, String packagePrefix) {
        String realPackagePrefix = checkAndMassagePackagePrefix(packagePrefix);
        Class<FACTORY> checkedFactoryType = checkFactoryType(factoryType);
        DIObjectFactoryInvocationHandler factoryHandler = new DIObjectFactoryInvocationHandler(realPackagePrefix, createWrappedPrimitiveTypeMap());
        return (FACTORY) Proxy.newProxyInstance(factoryType.getClassLoader(), new Class[]{checkedFactoryType}, factoryHandler);
    }

    private static String checkAndMassagePackagePrefix(String packagePrefix) {
        if (packagePrefix == null || packagePrefix.isEmpty()) {
            throw new IllegalArgumentException("'packagePrefix' must not be NULL or empty");
        }
        // cut off '.'
        String realPackagePrefix;
        if (packagePrefix.endsWith(".")) {
            realPackagePrefix = packagePrefix.substring(0, packagePrefix.length() - 1);
        } else {
            realPackagePrefix = packagePrefix;
        }
        return realPackagePrefix;
    }

    private static <FACTORY> Class<FACTORY> checkFactoryType(Class<FACTORY> factoryType) {
        /*
        <T> T newInstance(Class<T> type, Object... params);
        <T> T instance(Class<T> type);
        <T> T registerInstance(Class<T> type, T instance);
        String registerStringLiteral(String key, String value);
        String[] registerStringLiteralArray(String key, String[] valuesArray);
        */
        if (factoryType == null) {
            throw new IllegalArgumentException("'factoryType' must not be NULL");
        }
        DIMethods methods = new DIMethods(factoryType).mustNotBeAnInterface().mustHaveNumberOfMethods(5);
        methods.mustContainMethods("newInstance", "instance", "registerInstance", "registerStringLiteral", "registerStringLiteralArray");
        methods.method("newInstance").mustHaveParametersOfType(Class.class, Object[].class).mustHaveReturnType(Object.class);
        methods.method("instance").mustHaveParametersOfType(Class.class).mustHaveReturnType(Object.class);
        methods.method("registerInstance").mustHaveParametersOfType(Class.class, Object.class).mustHaveReturnType(Object.class);
        methods.method("registerStringLiteral").mustHaveParametersOfType(String.class, String.class).mustHaveReturnType(String.class);
        methods.method("registerStringLiteralArray").mustHaveParametersOfType(String.class, String[].class).mustHaveReturnType(String[].class);
        return factoryType;
    }

    private static Map<Class<?>, Class<?>> createWrappedPrimitiveTypeMap() {
        Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap = new HashMap<Class<?>, Class<?>>();
        wrappedPrimitiveTypeMap.put(byte.class, Byte.class);
        wrappedPrimitiveTypeMap.put(short.class, Short.class);
        wrappedPrimitiveTypeMap.put(int.class, Integer.class);
        wrappedPrimitiveTypeMap.put(long.class, Long.class);
        wrappedPrimitiveTypeMap.put(float.class, Float.class);
        wrappedPrimitiveTypeMap.put(double.class, Double.class);
        wrappedPrimitiveTypeMap.put(char.class, Character.class);
        wrappedPrimitiveTypeMap.put(boolean.class, Boolean.class);
        return wrappedPrimitiveTypeMap;
    }
}
