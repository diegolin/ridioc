package ch.dkitc.ridioc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DIMethod {

    private final Method method;

    public DIMethod(Method method) {
        this.method = method;
    }

    public DIMethod mustHaveParametersOfType(Class<?>... givenParamTypesArray) {
        Class<?>[] methodParamTypesArray = method.getParameterTypes();
        if (givenParamTypesArray.length != methodParamTypesArray.length) {
            throw new IllegalArgumentException("Given param types (length=" + givenParamTypesArray.length + ") should have same length as method param types (length=" + methodParamTypesArray.length + ")");
        }

        List<Class<?>> givenParamTypes = new ArrayList<Class<?>>();
        Collections.addAll(givenParamTypes, givenParamTypesArray);
        for (int i = 0; i < methodParamTypesArray.length; i++) {
            Class<?> paramType = methodParamTypesArray[i];
            if (!paramType.isAssignableFrom(givenParamTypes.get(i))) {
                throw new IllegalArgumentException("Method param type '" + paramType + "' is NOT assignable from given param type '" + givenParamTypes.get(i) + "'");
            }
        }
        return this;
    }

    public DIMethod mustHaveReturnType(Class<?> givenReturnType) {
        if (!givenReturnType.isAssignableFrom(method.getReturnType())) {
            throw new IllegalArgumentException("Given Return type '" + givenReturnType + "' is NOT assignable from method return type '" + method.getReturnType() + "'");
        }
        return this;
    }
}
