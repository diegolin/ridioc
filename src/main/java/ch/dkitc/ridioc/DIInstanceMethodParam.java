package ch.dkitc.ridioc;

public class DIInstanceMethodParam {

    private final Class<?> type;
    private final Object value;

    public DIInstanceMethodParam(Class<?> type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
