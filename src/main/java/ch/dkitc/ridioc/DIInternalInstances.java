package ch.dkitc.ridioc;

public interface DIInternalInstances {
    Object instance(Class<?> type) throws IllegalArgumentException;
    Object[] instances(Class<?> elementType) throws IllegalArgumentException;
    Object instanceCache(Class<?> type);
    Object[] instancesCache(Class<?> elementType);
}
