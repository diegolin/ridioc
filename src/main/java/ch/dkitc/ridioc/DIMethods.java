package ch.dkitc.ridioc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DIMethods extends HashMap<String, Method> {

    private final Class<?> type;
    private final Map<String, DIMethod> diMethodMap = new HashMap<String, DIMethod>();

    public DIMethods(Class<?> type) {
        this.type = type;
        for (Method method : type.getMethods()) {
            put(method.getName(), method);
        }
    }

    public void mustContainMethods(String... methodNames) {
        for (String methodName : methodNames) {
            mustContainMethod(methodName);
        }
    }

    public Method mustContainMethod(String methodName) {
        if (!containsKey(methodName)) {
            throw new IllegalArgumentException(type + " must have a method '" + methodName + "'");
        }
        return get(methodName);
    }

    public DIMethod method(String methodName) {
        DIMethod diMethod = diMethodMap.get(methodName);
        if (diMethod == null) {
            diMethod = new DIMethod(mustContainMethod(methodName));
        }
        return diMethod;
    }

    public DIMethods mustHaveNumberOfMethods(int numberOfMethods) {
        if (size() != numberOfMethods) {
            throw new IllegalArgumentException(type + " must have EXACTLY " + numberOfMethods + " method(s)");
        }
        return this;
    }

    public DIMethods mustNotBeAnInterface() {
        if (!type.isInterface()) {
            throw new IllegalArgumentException(type + " must be an interface");
        }
        return this;
    }
}
