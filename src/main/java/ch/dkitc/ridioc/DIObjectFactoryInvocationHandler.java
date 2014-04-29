package ch.dkitc.ridioc;

import java.lang.reflect.*;
import java.util.*;

public final class DIObjectFactoryInvocationHandler implements InvocationHandler {

    public static interface ObjectFactoryMethodHandler {
        Object invoke(Method method, Object[] args);
    }

    private final DIObjectFactoryHelper objectFactoryHelper;
    private final Map<String, ObjectFactoryMethodHandler> objectFactoryMethodHandlerMap = new HashMap<String, ObjectFactoryMethodHandler>();

    protected DIObjectFactoryInvocationHandler(String packagePrefix, Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap) {
        this.objectFactoryHelper = new DIObjectFactoryHelper(packagePrefix, wrappedPrimitiveTypeMap);
        this.objectFactoryMethodHandlerMap.put("newInstance", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                if (args.length <= 0) {
                    throw new IllegalArgumentException("There should be at least ONE parameter of type 'Class<?>'");
                }
                Class<?> type = castTo(args[0], Class.class);
                Object[] params = castTo(args[1], Object[].class);
                return objectFactoryHelper.newInstance(type, params);
            }
        });
        this.objectFactoryMethodHandlerMap.put("instance", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                if (args.length != 1) {
                    throw new IllegalArgumentException("There should be EXACTLY ONE parameter of type 'Class<?>'");
                }
                Class<?> type = castTo(args[0], Class.class);
                // is it ourselves?
                if (type.getClass().isInstance(this)) {
                    return castTo(this, type);
                }
                return objectFactoryHelper.instance(type);
            }
        });
        this.objectFactoryMethodHandlerMap.put("registerStringLiteral", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                if (args.length != 2) {
                    throw new IllegalArgumentException("There should be at EXACTLY TWO parameters of type 'java.lang.String'");
                }
                return objectFactoryHelper.registerStringLiteral(castTo(args[0], String.class), castTo(args[1], String.class));
            }
        });
        this.objectFactoryMethodHandlerMap.put("registerStringLiteralArray", new ObjectFactoryMethodHandler() {
            @Override
            public Object invoke(Method method, Object[] args) {
                argumentsMustHaveExactArgumentsCountOf(args, 2);

                return objectFactoryHelper.registerStringLiteralArray(castTo(args[0], String.class), castTo(args[1], String[].class));
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

    private <T> T castTo(Object arg, Class<T> type) {
        if (type.isInstance(arg)) {
            return (T) arg;
        }
        throw new ClassCastException("Cannot cast value " + arg.getClass().getName() + " to type " + type.getName());
    }

    private static void argumentsMustHaveExactArgumentsCountOf(Object[] args, int count) {
        if (args.length != count) {
            throw new IllegalArgumentException("There should be at EXACTLY " + count + " parameters, but there are " + args.length);
        }
    }

}
