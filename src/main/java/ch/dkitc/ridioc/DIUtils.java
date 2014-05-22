package ch.dkitc.ridioc;

public class DIUtils {

    public static boolean hasDefaultConstructor(Class<?> type) {
        try {
            type.getDeclaredConstructor();
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static <T> void checkType(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Argument 'type' must NOT be null");
        }
    }

    public static void checkParams(Object[] params) throws IllegalArgumentException {
        if (params ==  null) {
            throw new IllegalArgumentException("'params' must not be null");
        }
        for (Object param : params) {
            if (param == null) {
                throw new IllegalArgumentException("Parameter within 'params' may NOT be null");
            }
        }
    }

    public static void argumentsMustHaveExactArgumentsCountOf(Object[] args, int count) {
        if (args.length != count) {
            throw new IllegalArgumentException("There should be at EXACTLY " + count + " parameters, but there are " + args.length);
        }
    }

    public static void argumentsMustHaveAtLeastArgumentsCountOf(Object[] args, int count) {
        if (args.length < count) {
            throw new IllegalArgumentException("There should be at AT LEAST " + count + " parameters, but there are " + args.length);
        }
    }

    public static <T> T castTo(Object arg, Class<T> type) {
        if (type.isInstance(arg)) {
            return (T) arg;
        }
        throw new ClassCastException("Cannot cast value " + arg.getClass().getName() + " to type " + type.getName());
    }

    private DIUtils() {
    }
}
