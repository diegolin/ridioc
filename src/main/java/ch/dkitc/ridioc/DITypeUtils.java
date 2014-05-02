package ch.dkitc.ridioc;

public class DITypeUtils {

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

    private DITypeUtils() {
    }
}
