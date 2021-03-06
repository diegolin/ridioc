package ch.dkitc.ridioc;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.paranamer.AdaptiveParanamer;

public final class DI {

    public static DIObjectFactory createObjectFactory(String packagePrefix) {
        return createObjectFactory(DIObjectFactory.class, packagePrefix);
    }

    public static <FACTORY> FACTORY createObjectFactory(Class<FACTORY> factoryType, String packagePrefix) {
        String realPackagePrefix = checkAndMassagePackagePrefix(packagePrefix);
        Class<FACTORY> checkedFactoryType = checkFactoryType(factoryType);
        DIObjectFactoryInvocationHandler factoryHandler = new DIObjectFactoryInvocationHandler(realPackagePrefix, new AdaptiveParanamer());
        return (FACTORY) Proxy.newProxyInstance(factoryType.getClassLoader(), new Class[]{checkedFactoryType}, factoryHandler);
    }

    public static <FACTORY> Class<FACTORY> checkFactoryType(Class<FACTORY> factoryType) {
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
        methods.mustContainExactlyOneMethod("newInstance").mustHaveParametersOfType(Class.class, Object[].class).mustHaveReturnType(Object.class);
        methods.mustContainExactlyOneMethod("instance").mustHaveParametersOfType(Class.class).mustHaveReturnType(Object.class);
        methods.mustContainExactlyOneMethod("registerInstance").mustHaveParametersOfType(Class.class, Object.class).mustHaveReturnType(Object.class);
        methods.mustContainExactlyOneMethod("registerStringLiteral").mustHaveParametersOfType(String.class, Object.class).mustHaveReturnType(Object.class);
        methods.mustContainExactlyOneMethod("registerStringLiteralArray").mustHaveParametersOfType(String.class, Object[].class).mustHaveReturnType(Object[].class);
        return factoryType;
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
}
