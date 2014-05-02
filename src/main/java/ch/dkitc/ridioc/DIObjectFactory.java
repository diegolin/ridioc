package ch.dkitc.ridioc;

public interface DIObjectFactory {
    /**
     * Creates a fresh instance of a given type using constructor injection on a potential implementation type.
     * <p>Only ONE implementation of the given is allowed to be available in the scanned classpath.</p>
     * <p>This method always returns a <b>new</b> instance of the give type.</p>
     *
     * @param type   the type to create an instance for
     * @param params the parameters to be used to create
     * @param <T>    describes the type parameter
     * @return a newly created instance of the given type
     * @throws IllegalArgumentException if {@code type} is {@code null}, if any of the given parameters is {@code null}, if no implementation type was found, or if 2 or more implementations types are found
     */
    <T> T newInstance(Class<T> type, Object... params) throws IllegalArgumentException;

    <T> T instance(Class<T> type) throws IllegalArgumentException;

    <T> T registerInstance(Class<T> type, T instance) throws IllegalArgumentException;

    String registerStringLiteral(String key, String value) throws IllegalArgumentException;

    String[] registerStringLiteralArray(String key, String[] valuesArray) throws IllegalArgumentException;
}
