package ch.dkitc.ridioc.test.api;

public interface MyObjectFactory {
    <T> T newInstance(Class<T> type, Object... params);
    <T> T instance(Class<T> type);
    <T> T registerInstance(Class<T> type, T instance);
    String registerStringLiteral(String key, String value);
    String[] registerStringLiteralArray(String key, String[] valuesArray);
}
