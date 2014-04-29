package ch.dkitc.ridioc;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

public class DIObjectFactoryHelper {

    private final Map<Class<?>, Object> instanceCache = new HashMap<Class<?>, Object>();
    private final DIReflectionsCache reflectionsCache;
    private final Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap;
    private final DIStringLiteralStore stringLiteralStore;

    public DIObjectFactoryHelper(String packagePrefix, Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap) {
        this.reflectionsCache = new DIReflectionsCache(packagePrefix);
        this.wrappedPrimitiveTypeMap = wrappedPrimitiveTypeMap;
        this.stringLiteralStore = new DIStringLiteralStore(wrappedPrimitiveTypeMap);
    }

    public static boolean hasDefaultConstructor(Class<?> type) {
        try {
            type.getDeclaredConstructor();
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private static <T> void checkType(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Argument 'type' must NOT be null");
        }
    }

    private static void checkParams(Object[] params) {
        for (Object param : params) {
            if (param == null) {
                throw new IllegalArgumentException("Parameter within 'params' may NOT be null");
            }
        }
    }

    private static List<DIConstructor> findMatchingConstructor(Class<?> type, Object... params) {
        DIConstructors diConstructors = new DIConstructors(type).mustHaveAtLeastOnePublicConstructor();
        DIConstructor diConstructor = diConstructors.findMatchingConstructor(params);
        if (diConstructor != null) {
            // leave early!
            return DIUtils.toList(diConstructor);
        }

        // if we're here, no exact constructor was found for given params
        // return DEFAULT constructor
        DIConstructor diDefaultConstructor = diConstructors.findDefaultConstructor();
        if (diDefaultConstructor != null) {
            // leave early!
            return DIUtils.toList(diDefaultConstructor);
        }

        // o.k. let's just return ALL public constructors
        return diConstructors;
    }

    public <T> T newInstance(Class<T> type, Object... params) {
        checkType(type);
        checkParams(params);
        Class<? extends T> potentialImplType = getSinglePotentialImplType(type);
        return constructOneImplementation(type, potentialImplType, params);
    }

    public Object instance(Class<?> type) throws IllegalStateException {
        checkType(type);

        // already in cache?
        if (instanceCache.containsKey(type)) {
            // yes - return it
            return instanceCache.get(type);
        }

        Class<?> potentialImplType = getSinglePotentialImplType(type);
        Object instance = constructOneImplementation(type, potentialImplType);
        // cache it!
        instanceCache.put(type, instance);
        return instance;
    }

    public String registerStringLiteral(String key, String singleValue) {
        return stringLiteralStore.putSingleValue(key, singleValue);
    }

    public String[] registerStringLiteralArray(String key, String[] arrayValue) {
        return stringLiteralStore.putArrayValue(key, arrayValue);
    }

    public Object registerInstance(Class<?> type, Object instance) {
        Class<?> realType;
        if (type.isPrimitive()) {
            realType = getWrappedPrimitiveType(type);
        } else {
            realType = type;
        }
        return instanceCache.put(realType, instance);
    }

    private Class<?> getWrappedPrimitiveType(Class<?> type) {
        if (!type.isPrimitive()) {
            throw new IllegalArgumentException("Given type '" + type + " is NOT primitive");
        }
        if (!wrappedPrimitiveTypeMap.containsKey(type)) {
            throw new IllegalArgumentException("There is no wrapped type for primitive type '" + type + "' available");
        }
        return wrappedPrimitiveTypeMap.get(type);
    }

    private <T> T newInstance(DIConstructor diConstructor, DIConstructorParams constructorParams, DIInstanceMethodParams instanceMethodParams) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Object> initArgsAsList = new ArrayList<Object>();
        Map<Class<?>, DIMethodParamsIndex> methodParamsIndexMap = new HashMap<Class<?>, DIMethodParamsIndex>();
        int usedMethodParamCount = 0;
        for (DIConstructorParam constrParam : constructorParams) {
            Class<?> constrParamType = constrParam.getType();
            if (Collection.class.isAssignableFrom(constrParamType)) {
                // fail early
                throw new IllegalArgumentException("Colletions are NOT supported - I cannot determine the element type at runtime: http://stackoverflow.com/questions/10945993/using-java-reflections-to-find-collections-element-type");
            }

            if (constrParamType.isPrimitive()) {
                // override primitive type with wrapped type
                constrParamType = getWrappedPrimitiveType(constrParamType);
            }

            // try to find constructor-param-value BY TYPE among them method-params
            DIMethodParamsIndex methodParamsIndex = methodParamsIndexMap.get(constrParamType);
            if (methodParamsIndex == null) {
                DIInstanceMethodParams methodParamsOfType = instanceMethodParams.getParamsOfType(constrParamType);
                methodParamsIndex = new DIMethodParamsIndex(methodParamsOfType);
                methodParamsIndexMap.put(constrParamType, methodParamsIndex);
            }

            DIInstanceMethodParam instaceMethodParamOfType = methodParamsIndex.next();
            if (instaceMethodParamOfType != null) {
                // o.k. - a matching method param has been passed - use it
                initArgsAsList.add(instaceMethodParamOfType.getValue());
                usedMethodParamCount++;
                continue;
            }

            if (constrParam.isArray()) {
                if (constrParam.isArrayOfNumbers()) {
                    throw new IllegalArgumentException(constrParam + ": Array of primitives not (yet) supported");
                }
                if (constrParam.isArrayOfPrimitives()) {
                    throw new IllegalArgumentException(constrParam + ": Array of primitives not (yet) supported");
                }
                if (constrParam.isArrayOfEnums()) {
                    throw new IllegalArgumentException(constrParam + ": Array of enums are not (yet) supported");
                }
                if (constrParam.isArrayOfStrings()) {
                    initArgsAsList.add(stringLiteralStore.getArrayValue(constrParam.getName()));
                    continue;
                }
                if (constrParam.isArrayOfDates()) {
                    throw new IllegalArgumentException(constrParam + ": Array of dates are not (yet) supported");
                }
                if (constrParam.isArrayOfArrays()) {
                    throw new IllegalArgumentException(constrParam + ": Array of arrays not (yet) supported");
                }
                initArgsAsList.add(createArrayInitArg(constrParamType));
                continue;
            }

            if (constrParam.isNumber()
                    || constrParam.isPrimitive()
                    || constrParam.isEnum()
                    || constrParam.isString()
                    || constrParam.isDate()
                    || constrParam.isCharacter()
                    || constrParam.isBoolean()) {
                if (instanceCache.containsKey(constrParamType)) {
                    initArgsAsList.add(instanceCache.get(constrParamType));
                    continue;
                }
                if (stringLiteralStore.containsSingleValue(constrParam.getName())) {
                    initArgsAsList.add(stringLiteralStore.convertSingleValueTo(constrParam.getName(), constrParamType));
                    continue;
                }
                throw new IllegalArgumentException("Could not find " + constrParam.getName() + " of type " + constrParamType + " within instance cache or string literal store");
            }

            // if we're here, it is NOT an array, not a number / primitve / enum / string / date / character / boolean
            initArgsAsList.add(instance(constrParamType));
        }

        // sanity check 1: do we have all the required constructor parameters?
        if (initArgsAsList.size() != constructorParams.size()) {
            throw new IllegalArgumentException("There should be " + constructorParams.size() + " arguments, but there are " + initArgsAsList.size() + " arguments");
        }
        // sanity check 2: did we use all the given method parameters?
        if (usedMethodParamCount != instanceMethodParams.size()) {
            throw new IllegalArgumentException("Only " + usedMethodParamCount + " out of " + instanceMethodParams.size() + " params have been used");
        }

        return diConstructor.newInstance(initArgsAsList);
    }

    private <T> Class<? extends T> getSinglePotentialImplType(Class<T> type) {
        List<Class<? extends T>> potentialImplTypes = getPotentialImplTypes(type);
        switch (potentialImplTypes.size()) {
            case 0:
                // not good
                throw new IllegalArgumentException("no matching implementation of type " + type + "' found within '" + reflectionsCache.getUrls() + '"');
            case 1:
                // good
                return potentialImplTypes.get(0);
            default:
                // not good
                throw new IllegalArgumentException(potentialImplTypes.size() + " matching implementation types found within '" + reflectionsCache.getUrls() + '"');
        }
    }

    private <T> List<Class<? extends T>> getPotentialImplTypes(Class<T> type) {
        Set<Class<? extends T>> types = reflectionsCache.getSubTypesOf(type);
        List<Class<? extends T>> potentialImplTypes = new ArrayList<Class<? extends T>>();
        Iterator<Class<? extends T>> it = types.iterator();
        while (it.hasNext()) {
            Class<? extends T> potentialImplType = it.next();
            if (Modifier.isAbstract(potentialImplType.getModifiers())) {
                it.remove();
                continue;
            }
            potentialImplTypes.add(potentialImplType);
        }
        return potentialImplTypes;
    }

    private DIConstructorParams createConstuctorParams(DIConstructor diConstructor) {
        List<String> paramNames = diConstructor.getParameterNames();
        DIConstructorParams constructorParams = new DIConstructorParams();
        for (int i = 0; i < paramNames.size(); i++) {
            constructorParams.add(paramNames.get(i), diConstructor.getParamType(i), diConstructor.getParamAnnotations(i));
        }
        return constructorParams;
    }

    private <T> T constructOneImplementation(Class<T> type, Class<?> potentialImplType, Object... params) {
        List<DIConstructor> diConstructors = findMatchingConstructor(potentialImplType);
        List<Exception> exceptions = new ArrayList<Exception>();
        for (DIConstructor diConstructor : diConstructors) {
            DIConstructorParams constructorParams = createConstuctorParams(diConstructor);
            DIInstanceMethodParams instanceMethodParams = new DIInstanceMethodParams(params);
            try {
                return newInstance(diConstructor, constructorParams, instanceMethodParams);
            } catch (Exception ex) {
                exceptions.add(ex);
                ex.printStackTrace(System.err);
            }
        }

        throw new DIAggregateException("Cound not instantiate implementation for type '" + type + "'", exceptions);
    }

    private Object createArrayInitArg(Class<?> constructorParamType) {
        List<Object> subTypeImpls = new ArrayList<Object>();
        Class<?> paramArrayComponentType = constructorParamType.getComponentType();
        for (Class<?> subTypeImpl : reflectionsCache.getSubTypesOf(paramArrayComponentType)) {
            // filter out abstract types...
            if (Modifier.isAbstract(subTypeImpl.getModifiers())) {
                continue;
            }
            if (!hasDefaultConstructor(subTypeImpl)) {
                continue;
            }
            subTypeImpls.add(constructOneImplementation(paramArrayComponentType, subTypeImpl));
        }
        return subTypeImpls.toArray((Object[]) Array.newInstance(paramArrayComponentType, subTypeImpls.size()));
    }

}
