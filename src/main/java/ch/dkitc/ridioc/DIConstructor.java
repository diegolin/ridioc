package ch.dkitc.ridioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.thoughtworks.paranamer.AdaptiveParanamer;
import com.thoughtworks.paranamer.Paranamer;

public class DIConstructor {

    private final Constructor<?> constructor;
    private final Paranamer paranamer = new AdaptiveParanamer();
    private final Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap;

    public DIConstructor(Constructor<?> constructor, Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap) {
        this.constructor = constructor;
        this.wrappedPrimitiveTypeMap = wrappedPrimitiveTypeMap;
    }

    public String getName() {
        return constructor.getDeclaringClass().getName();
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
                wrappedParamType = wrappedPrimitiveTypeMap.get(paramType);
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
        return (T) constructor.newInstance(initArgs);
    }

    public List<String> getParameterNames() {
        List<String> paramNames = new ArrayList<String>();
        Collections.addAll(paramNames, paranamer.lookupParameterNames(constructor));
        return paramNames;
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

    private void checkParamIndex(int paramIndex) {
        if (paramIndex < 0) {
            throw new IllegalArgumentException(constructor + ": Param index must be 0 or greater, but is " + paramIndex);
        }
        if (paramIndex >= constructor.getParameterTypes().length) {
            throw new IndexOutOfBoundsException(constructor + ": Invalid parameter index " + paramIndex + " - constructor only " + constructor.getParameterTypes().length + " parameters");
        }
    }
}
