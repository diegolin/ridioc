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

    private DIUtils() {
    }
}
