package ch.dkitc.ridioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.paranamer.Paranamer;
import static ch.dkitc.ridioc.DIUtils.*;

public final class DIObjectFactoryInvocationHandler implements InvocationHandler {

    public static interface ObjectFactoryMethodHandler {
        Object invoke(Method method, Object[] args);
    }

    private final DIObjectFactoryHelper objectFactoryHelper;
    private final Map<String, ObjectFactoryMethodHandler> objectFactoryMethodHandlerMap = new HashMap<String, ObjectFactoryMethodHandler>();

    protected DIObjectFactoryInvocationHandler(String packagePrefix, Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap, Paranamer paranamer) {
        this.objectFactoryHelper = new DIObjectFactoryHelper(packagePrefix, wrappedPrimitiveTypeMap, paranamer);
        this.objectFactoryMethodHandlerMap.put("newInstance", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                argumentsMustHaveAtLeastArgumentsCountOf(args, 1);
                Class<?> type = castTo(args[0], Class.class);
                Object[] params = castTo(args[1], Object[].class);
                return objectFactoryHelper.newInstance(type, params);
            }
        });
        this.objectFactoryMethodHandlerMap.put("instance", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                argumentsMustHaveExactArgumentsCountOf(args, 1);
                Class<?> type = castTo(args[0], Class.class);
                return objectFactoryHelper.instance(type);
            }
        });
        this.objectFactoryMethodHandlerMap.put("registerStringLiteral", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                argumentsMustHaveExactArgumentsCountOf(args, 2);
                String key = castTo(args[0], String.class);
                Object singleValue = args[1];
                return objectFactoryHelper.registerStringLiteral(key, singleValue);
            }
        });
        this.objectFactoryMethodHandlerMap.put("registerStringLiteralArray", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                argumentsMustHaveExactArgumentsCountOf(args, 2);
                String key = castTo(args[0], String.class);
                Object [] arrayValue = castTo(args[1], Object[].class);
                return objectFactoryHelper.registerStringLiteralArray(key, arrayValue);
            }
        });
        this.objectFactoryMethodHandlerMap.put("registerInstance", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                argumentsMustHaveExactArgumentsCountOf(args, 2);
                Class<?> type = castTo(args[0], Class.class);
                Object instance = args[1];
                return objectFactoryHelper.registerInstance(type, instance);
            }
        });
    }

    @Override
    public final Object invoke(Object proxy, Method method, Object[] args) {
        ObjectFactoryMethodHandler methodHandler = objectFactoryMethodHandlerMap.get(method.getName());
        if (methodHandler == null) {
            throw new IllegalArgumentException("Cannot handle method '" + method.getName() + "'");
        }
        return methodHandler.invoke(method, args);
    }
}
