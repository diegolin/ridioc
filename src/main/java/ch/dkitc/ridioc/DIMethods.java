package ch.dkitc.ridioc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DIMethods {

    private final Class<?> type;

    public DIMethods(Class<?> type) {
        this.type = type;
    }

    public DIMethod mustContainExactlyOneMethod(String methodName) {
        List<Method> methods = getMethods(methodName);
        switch (methods.size()) {
            case 0:
                throw new IllegalArgumentException(type + " does not contain a method '" + methodName + "'");
            case 1:
                return new DIMethod(methods.get(0));
            default:
                throw new IllegalArgumentException(type + " does contain multiple methods '" + methodName + "': " + methods);
        }
    }

    public DIMethods mustHaveNumberOfMethods(int numberOfMethods) {
        if (type.getMethods().length != numberOfMethods) {
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

    public boolean containsMethod(String methodName, Class<?> ... paramTypes) {
        List<Method> methods = getMethods(methodName);
        for (Method method : methods) {
            if (new DIMethod(method).hasParametersOfType(paramTypes)) {
                return true;
            }
        }
        return false;
    }

    private List<Method> getMethods(String methodName) {
        List<Method> methods = new ArrayList<Method>();
        for (Method method : type.getMethods()) {
            if (method.getName().endsWith(methodName)) {
                methods.add(method);
            }
        }
        return methods;
    }

}
