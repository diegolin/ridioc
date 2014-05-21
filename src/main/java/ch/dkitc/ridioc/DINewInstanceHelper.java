package ch.dkitc.ridioc;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DINewInstanceHelper {

    private final DIInternalInstances internalInstances;
    private final Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap;
    private final DIStringLiteralStore stringLiteralStore;

    public DINewInstanceHelper(DIInternalInstances internalInstances, Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap) {
        this.internalInstances = internalInstances;
        this.wrappedPrimitiveTypeMap = wrappedPrimitiveTypeMap;
        this.stringLiteralStore = new DIStringLiteralStore(wrappedPrimitiveTypeMap);
    }

    public Map<Class<?>, Class<?>> getWrappedPrimitiveTypeMap() {
        return wrappedPrimitiveTypeMap;
    }

    public Class<?> getWrappedPrimitiveType(Class<?> type) {
        if (!type.isPrimitive()) {
            throw new IllegalArgumentException("Given type '" + type + " is NOT primitive");
        }
        if (!wrappedPrimitiveTypeMap.containsKey(type)) {
            throw new IllegalArgumentException("There is no wrapped type for primitive type '" + type + "' available");
        }
        return wrappedPrimitiveTypeMap.get(type);
    }

    public String registerStringLiteral(String key, String singleValue) {
        return stringLiteralStore.putSingleValue(key, singleValue);
    }

    public String[] registerStringLiteralArray(String key, String[] arrayValue) {
        return stringLiteralStore.putArrayValue(key, arrayValue);
    }

    public <T> T newInstance(DIConstructor diConstructor, DIConstructorParams constructorParams, DIInstanceMethodParams instanceMethodParams) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        checkConstructorParameterTypes(constructorParams);

        // diConstructor.getGenericSignature();

        List<Object> initArgsAsList = new ArrayList<Object>();
        Map<Class<?>, DIMethodParamsIndex> methodParamsIndexMap = new HashMap<Class<?>, DIMethodParamsIndex>();
        int usedMethodParamCount = 0;
        for (DIConstructorParam constrParam : constructorParams) {
            Class<?> constrParamType = constrParam.getType();
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
            DIInstanceMethodParam instanceMethodParamOfType = methodParamsIndex.next();
            if (instanceMethodParamOfType != null) {
                // o.k. - a matching method param has been passed - use it
                initArgsAsList.add(instanceMethodParamOfType.getValue());
                usedMethodParamCount++;
                continue;
            }

            if (constrParam.isTypeFactory()) {
                newInstanceTypeFactory(initArgsAsList, constrParam, constrParamType);
                continue;
            }

            if (constrParam.isIterable()) {
                newInstanceIterable(initArgsAsList, constrParam, constrParamType);
                continue;
            }

            if (constrParam.isArray()) {
                newInstanceArray(initArgsAsList, constrParam, constrParamType);
                continue;
            }

            if (constrParam.isLiteral()) {
                newInstanceLiteral(initArgsAsList, constrParam, constrParamType);
                continue;
            }

            // if we're here, it is NOT an array, not a number / primitive / enum / string / date / character / boolean
            initArgsAsList.add(internalInstances.instance(constrParamType));
        }

        checkNewInstancePostConditions(constructorParams, instanceMethodParams, initArgsAsList, usedMethodParamCount);
        return diConstructor.newInstance(initArgsAsList);
    }

    private void newInstanceTypeFactory(List<Object> initArgsAsList, DIConstructorParam constrParam, Class<?> constrParamType) {
        if (constrParam.isTypeFactory()) {
            throw new IllegalArgumentException(constrParam + ": type factories not (yet) supported");
        }

        throw new IllegalArgumentException(constrParam + ": This doesn't seem to be an type factory");
    }

    private void newInstanceIterable(List<Object> initArgsAsList, DIConstructorParam constrParam, Class<?> constrParamType) {
        if (constrParam.isSet()) {
            throw new IllegalArgumentException(constrParam + ": java.util.Set not (yet) supported");
        }

        if (constrParam.isList()) {
            throw new IllegalArgumentException(constrParam + ": java.util.List not (yet) supported");
        }

        if (constrParam.isCollection()) {
            throw new IllegalArgumentException(constrParam + ": java.util.Collection not (yet) supported");
        }

        if (constrParam.isIterable()) {
            throw new IllegalArgumentException(constrParam + ": java.util.Iterable not (yet) supported");
        }

        throw new IllegalArgumentException(constrParam + ": This doesn't seem to be an iterable");
    }

    private void checkNewInstancePostConditions(DIConstructorParams constructorParams, DIInstanceMethodParams instanceMethodParams, List<Object> initArgsAsList, int usedMethodParamCount) {
        // sanity check 1: do we have all the required constructor parameters?
        if (initArgsAsList.size() != constructorParams.size()) {
            throw new IllegalArgumentException("There should be " + constructorParams.size() + " arguments, but there are " + initArgsAsList.size() + " arguments");
        }
        // sanity check 2: did we use all the given method parameters?
        if (usedMethodParamCount != instanceMethodParams.size()) {
            throw new IllegalArgumentException("Only " + usedMethodParamCount + " out of " + instanceMethodParams.size() + " params have been used");
        }
    }

    private void newInstanceLiteral(List<Object> initArgsAsList, DIConstructorParam constrParam, Class<?> constrParamType) {
        Object cachedInstance = internalInstances.instanceCache(constrParamType);
        if (cachedInstance != null) {
            initArgsAsList.add(cachedInstance);
            return;
        }
        if (stringLiteralStore.containsSingleValue(constrParam.getName())) {
            initArgsAsList.add(stringLiteralStore.convertSingleValueTo(constrParam.getName(), constrParamType));
            return;
        }
        throw new IllegalArgumentException("Could not find " + constrParam.getName() + " of type " + constrParamType + " within instance cache or string literal store");
    }

    private void newInstanceArray(List<Object> initArgsAsList, DIConstructorParam constrParam, Class<?> constrParamType) {
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
            return;
        }
        if (constrParam.isArrayOfDates()) {
            throw new IllegalArgumentException(constrParam + ": Array of dates are not (yet) supported");
        }
        if (constrParam.isArrayOfArrays()) {
            throw new IllegalArgumentException(constrParam + ": Array of arrays not (yet) supported");
        }

        // if we're here, we are assuming that the constructor expects an array of types
        initArgsAsList.add(internalInstances.instances(constrParamType));
    }

    private static void checkConstructorParameterTypes(DIConstructorParams constructorParams) {
        for (DIConstructorParam constrParam : constructorParams) {
            Class<?> constrParamType = constrParam.getType();
            if (Collection.class.isAssignableFrom(constrParamType)) {
                // fail early
                throw new IllegalArgumentException("Collections are NOT supported - I cannot determine the element type at runtime: http://stackoverflow.com/questions/10945993/using-java-reflections-to-find-collections-element-type");
            }
        }
    }
}
