package ch.dkitc.ridioc;

public class DIParamUtils {

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

    private DIParamUtils() {
    }
}
