package ch.dkitc.ridioc;

public interface DITypeFactory<T> {
    T newInstance(Object... params) throws IllegalArgumentException;
}
