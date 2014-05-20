package ch.dkitc.ridioc;

public interface DIInternalInstances {
    Object instance(Class<?> type) throws IllegalArgumentException;
    Object instances(Class<?> type) throws IllegalArgumentException;
    Object instanceCache(Class<?> type);
}
