package ch.dkitc.ridioc;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DIConstructors extends ArrayList<DIConstructor> {

    private final Class<?> type;

    public DIConstructors(Class<?> type, Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap) {
        this.type = type;
        for (Constructor<?> constructor : type.getConstructors()) {
            add(new DIConstructor(constructor, wrappedPrimitiveTypeMap));
        }
    }

    public DIConstructors mustHaveAtLeastOnePublicConstructor() {
        if (size() <= 0) {
            throw new IllegalArgumentException(type + ": There must be at least ONE public constructor");
        }
        return this;
    }

    public List<DIConstructor> findMatchingConstructorsByParams(Object... params) {
        mustHaveAtLeastOnePublicConstructor();

        DIConstructor diConstructor = findMatchingConstructorByParams(params);
        if (diConstructor != null) {
            // leave early!
            return Arrays.asList(diConstructor);
        }

        // if we're here, no exact constructor was found for given params
        // return DEFAULT constructor
        DIConstructor diDefaultConstructor = findDefaultConstructor();
        if (diDefaultConstructor != null) {
            // leave early!
            return Arrays.asList(diDefaultConstructor);
        }

        // o.k. let's just return ALL public constructors
        return this;
    }

    public DIConstructor findMatchingConstructorByParams(Object... params) {
        for (DIConstructor diConstructor : this) {
            if (diConstructor.matchesParams(params)) {
                return diConstructor;
            }
        }

        // if we're here, no constructor was found
        return null;
    }

    public DIConstructor findMatchingConstructorByParamTypes(Class<?>... paramTypes) {
        for (DIConstructor diConstructor : this) {
            if (diConstructor.matchesParamTypes(paramTypes)) {
                return diConstructor;
            }
        }

        // if we're here, no constructor was found
        return null;
    }

    public DIConstructor findDefaultConstructor() {
        return findMatchingConstructorByParams(/* no parameters means: default constructor */);
    }
}
