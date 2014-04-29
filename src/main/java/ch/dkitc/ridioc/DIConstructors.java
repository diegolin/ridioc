package ch.dkitc.ridioc;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class DIConstructors extends ArrayList<DIConstructor> {

    private final Class<?> type;

    public DIConstructors(Class<?> type) {
        this.type = type;
        for (Constructor<?> constructor : type.getConstructors()) {
            add(new DIConstructor(constructor));
        }
    }

    public DIConstructors mustHaveAtLeastOnePublicConstructor() {
        if (size() <= 0) {
            throw new IllegalArgumentException(type + ": There must be at least ONE public constructor");
        }
        return this;
    }

    public DIConstructor findMatchingConstructor(Object... params) {
        for (DIConstructor diConstructor : this) {
            if (diConstructor.matchesParams(params)) {
                return diConstructor;
            }
        }

        // if we're here, no constructor was found
        return null;
    }

    public DIConstructor findDefaultConstructor() {
        return findMatchingConstructor(/* no parameters means: default constructor */);
    }
}
