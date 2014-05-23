package ch.dkitc.ridioc;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

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

    public Object registerStringLiteral(String key, Object singleValue) {
        return stringLiteralStore.putSingleValue(key, singleValue);
    }

    public Object[] registerStringLiteralArray(String key, Object[] arrayValue) {
        return stringLiteralStore.putArrayValue(key, arrayValue);
    }

    public <T> T newInstance(DIConstructor diConstructor, DIInstanceMethodParams instanceMethodParams) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Object> initArgsAsList = new ArrayList<Object>();
        DIMethodParamsIndexMap methodParamsIndexMap = new DIMethodParamsIndexMap(instanceMethodParams);
        int usedMethodParamCount = 0;
        for (DIConstructorParam constrParam : diConstructor) {
            Class<?> constrParamType = new OverrideConstrParamType(constrParam).invoke();

            // try to find constructor-param-value BY TYPE among them method-params
            DIInstanceMethodParam instanceMethodParamOfType = methodParamsIndexMap.get(constrParamType).next();
            if (instanceMethodParamOfType != null) {
                // o.k. - a matching method param has been passed - use it
                initArgsAsList.add(instanceMethodParamOfType.getValue());
                usedMethodParamCount++;
                continue;
            }

            if (constrParam.isListOrArray()) {
                new NewInstanceListOrArray(initArgsAsList, constrParam).invoke();
                continue;
            }

            if (constrParam.isLiteral()) {
                new NewInstanceLiteral(initArgsAsList, constrParam, constrParamType).invoke();
                continue;
            }

            // if we're here, it is NOT an array, NOT a list, NOT a literal
            initArgsAsList.add(internalInstances.instance(constrParamType));
        }

        checkNewInstancePostConditions(diConstructor.getParamCount(), instanceMethodParams, initArgsAsList, usedMethodParamCount);
        return diConstructor.newInstance(initArgsAsList.toArray());
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

    private class NewInstanceListOrArray {
        private final List<Object> initArgsAsList;
        private final DIConstructorParam constrParam;

        public NewInstanceListOrArray(List<Object> initArgsAsList, DIConstructorParam constrParam) {
            this.initArgsAsList = initArgsAsList;
            this.constrParam = constrParam;
        }

        public void invoke() {
            Object [] instances = internalInstances.instancesCache(constrParam.getListOrArrayElementType());
            if (instances == null) {
                if (constrParam.isArrayOfLiterals()) {
                    Class<?> overrideType = new OverrideConstrParamType(constrParam).invoke();
                    instances = stringLiteralStore.convertArrayValueTo(constrParam.getName(), overrideType.getComponentType());
                } else if (constrParam.isListOrArrayOfLiterals()) {
                    instances = stringLiteralStore.convertArrayValueTo(constrParam.getName(), constrParam.getListType());
                } else {
                    instances = internalInstances.instances(constrParam.getListOrArrayElementType());
                }
            }
            if (constrParam.isList()) {
                initArgsAsList.add(ImmutableList.copyOf(instances));
            } else {
                initArgsAsList.add(instances);
            }
        }
    }

    private class NewInstanceLiteral {
        private final List<Object> initArgsAsList;
        private final DIConstructorParam constrParam;
        private final Class<?> constrParamType;

        public NewInstanceLiteral(List<Object> initArgsAsList, DIConstructorParam constrParam, Class<?> constrParamType) {
            this.initArgsAsList = initArgsAsList;
            this.constrParam = constrParam;
            this.constrParamType = constrParamType;
        }

        public void invoke() {
            Object instance = internalInstances.instanceCache(constrParamType);
            if (instance == null) {
                instance = stringLiteralStore.convertSingleValueTo(constrParam.getName(), constrParamType);
            }
            initArgsAsList.add(instance);
        }
    }

    private class OverrideConstrParamType {
        private final DIConstructorParam constrParam;

        public OverrideConstrParamType(DIConstructorParam constrParam) {
            this.constrParam = constrParam;
        }

        public Class<?> invoke() {
            Class<?> constrParamType;
            if (constrParam.isPrimitive()) {
                // override primitive type with wrapped type
                constrParamType = getWrappedPrimitiveType(constrParam.getType());
            } else if (constrParam.isArrayOfPrimitives()) {
                Class<?> wrappedComponentType = getWrappedPrimitiveType(constrParam.getComponentType());
                constrParamType = Array.newInstance(wrappedComponentType, 0).getClass();
            } else {
                constrParamType = constrParam.getType();
            }
            return constrParamType;
        }
    }
}
