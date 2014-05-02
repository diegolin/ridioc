package ch.dkitc.ridioc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DIListUtils {

    public static <T> List<T> toList(T ... ts) {
        List<T> list = new ArrayList<T>();
        Collections.addAll(list, ts);
        return list;
    }

    private DIListUtils() {
    }

}
