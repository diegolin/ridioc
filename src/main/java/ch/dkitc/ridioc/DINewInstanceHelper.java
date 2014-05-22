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

    public <T> T newInstance(DIConstructor diConstructor, DIInstanceMethodParams instanceMethodParams) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Object> initArgsAsList = new ArrayList<Object>();
        Map<Class<?>, DIMethodParamsIndex> methodParamsIndexMap = new HashMap<Class<?>, DIMethodParamsIndex>();
        int usedMethodParamCount = 0;
        for (DIConstructorParam constrParam : diConstructor) {
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

            if (constrParam.isList()) {
                newInstanceList(initArgsAsList, constrParam, constrParamType);
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

        checkNewInstancePostConditions(diConstructor.getParamCount(), instanceMethodParams, initArgsAsList, usedMethodParamCount);
        return diConstructor.newInstance(initArgsAsList);
    }

    private void checkNewInstancePostConditions(int constructorParamCount, DIInstanceMethodParams instanceMethodParams, List<Object> initArgsAsList, int usedMethodParamCount) {
        // sanity check 1: do we have all the required constructor parameters?
        if (initArgsAsList.size() != constructorParamCount) {
            throw new IllegalArgumentException("There should be " + constructorParamCount + " arguments, but there are " + initArgsAsList.size() + " arguments");
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

    private void newInstanceList(List<Object> initArgsAsList, DIConstructorParam constrParam, Class<?> constrParamType) {
        if (constrParam.isListOfNumbers()) {
            throw new IllegalArgumentException(constrParam + ": Array of primitives not (yet) supported");
        }
        if (constrParam.isListOfPrimitives()) {
            throw new IllegalArgumentException(constrParam + ": List of primitives not (yet) supported");
        }
        if (constrParam.isListOfEnums()) {
            throw new IllegalArgumentException(constrParam + ": Array of enums are not (yet) supported");
        }
        if (constrParam.isListOfStrings()) {
            initArgsAsList.add(Arrays.asList(stringLiteralStore.getArrayValue(constrParam.getName())));
            return;
        }
        if (constrParam.isListOfDates()) {
            throw new IllegalArgumentException(constrParam + ": Array of dates are not (yet) supported");
        }
        if (constrParam.isListOfArrays()) {
            throw new IllegalArgumentException(constrParam + ": Array of arrays not (yet) supported");
        }

        // if we're here, we are assuming that the constructor expects an array of types
        Object instanceArray = internalInstances.instances(constrParamType);
        initArgsAsList.add(Arrays.asList(instanceArray));
    }


}
