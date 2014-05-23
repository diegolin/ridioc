package ch.dkitc.ridioc;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.*;

import com.thoughtworks.paranamer.Paranamer;
import static ch.dkitc.ridioc.DIUtils.checkParams;
import static ch.dkitc.ridioc.DIUtils.checkType;

public class DIObjectFactoryHelper implements DIInternalInstances {

    private final Map<Class<?>, Object> instanceCache;
    private final Map<Class<?>, Object[]> instancesCache;
    private final DIReflectionsCache reflectionsCache;
    private final DINewInstanceHelper newInstanceHelper;
    private final Paranamer paranamer;

    public DIObjectFactoryHelper(String packagePrefix, Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap, Paranamer paranamer) {
        instanceCache = new HashMap<Class<?>, Object>();
        instancesCache = new HashMap<Class<?>, Object[]>();
        this.reflectionsCache = new DIReflectionsCache(packagePrefix);
        this.newInstanceHelper = new DINewInstanceHelper(this, wrappedPrimitiveTypeMap);
        this.paranamer = paranamer;
    }

    public <T> T newInstance(Class<T> type, Object... params) {
        checkType(type);
        checkParams(params);
        Class<? extends T> potentialImplType = getSinglePotentialImplType(type);
        return constructOneImplementation(type, potentialImplType, params);
    }

    /**
     * Looks up or creates an instance of the given type.
     * <p>Tries to look up the give type in the instance cache.
     * If the instance cache does not yet contain an instance of the given type, the method
     * tries to find exactly ONE implementation of the given type. The method creates a new instance using
     * dependency injection.</p>
     *
     * @param type a type to look up or create an instance for. Must not be {@code null}
     * @return the looked up instance or a newly created instance of the given type
     * @throws IllegalArgumentException
     */
    public Object instance(Class<?> type) throws IllegalArgumentException {
        checkType(type);
        Object instance = instanceCache.get(type);
        // not yet cached?
        if (instance == null) {
            // create it & cache it!
            Class<?> potentialImplType = getSinglePotentialImplType(type);

            // TODO intercept dynamic creation of factory type!!

            instance = constructOneImplementation(type, potentialImplType);
            instanceCache.put(type, instance);
        }
        return instance;
    }

    public Object[] instances(Class<?> elementType) throws IllegalArgumentException {
        Object[] instances = instancesCache.get(elementType);
        // not yet cached?
        if (instances == null) {
            // create it & cache it!
            List<Object> subTypeImplementations = new ArrayList<Object>();
            for (Class<?> subTypeImpl : getPotentialImplTypes(elementType)) {
                subTypeImplementations.add(constructOneImplementation(elementType, subTypeImpl));
            }
            instances = subTypeImplementations.toArray((Object[]) Array.newInstance(elementType, subTypeImplementations.size()));
            instancesCache.put(elementType, instances);
        }
        return instances;
    }

    public Object registerStringLiteral(String key, Object singleValue) {
        return newInstanceHelper.registerStringLiteral(key, singleValue);
    }

    public Object[] registerStringLiteralArray(String key, Object[] arrayValue) {
        return newInstanceHelper.registerStringLiteralArray(key, arrayValue);
    }

    public Object registerInstance(Class<?> type, Object instance) {
        Class<?> realType;
        if (type.isPrimitive()) {
            realType = newInstanceHelper.getWrappedPrimitiveType(type);
        } else {
            realType = type;
        }
        return instanceCache.put(realType, instance);
    }

    @Override
    public Object instanceCache(Class<?> type) {
        return instanceCache.get(type);
    }

    @Override
    public Object[] instancesCache(Class<?> elementType) {
        return instancesCache.get(elementType);
    }

    private <T> T constructOneImplementation(Class<T> type, Class<?> potentialImplType, Object... params) throws DIAggregateException {
        List<DIConstructor> diConstructors = new DIConstructors(potentialImplType, newInstanceHelper.getWrappedPrimitiveTypeMap(), paranamer).findMatchingConstructorsByParams(params);
        List<Exception> exceptions = new ArrayList<Exception>();
        for (DIConstructor diConstructor : diConstructors) {
            DIInstanceMethodParams instanceMethodParams = new DIInstanceMethodParams(params);
            try {
                return newInstanceHelper.newInstance(diConstructor, instanceMethodParams);
            } catch (Exception ex) {
                exceptions.add(ex);
            }
        }

        switch (exceptions.size()) {
            case 0:
                throw new IllegalArgumentException("Cound not instantiate implementation for type '" + type + "' with parameters '" + Arrays.toString(params) + "'");
            case 1:
                throw new IllegalArgumentException("Cound not instantiate implementation for type '" + type + "' with parameters '" + Arrays.toString(params) + "'", exceptions.get(0));
            default:
                throw new DIAggregateException("Cound not instantiate implementation for type '" + type + "'", exceptions);
        }
    }

    private <T> List<Class<? extends T>> getPotentialImplTypes(Class<T> type) {
        Set<Class<? extends T>> types = reflectionsCache.getSubTypesOf(type);
        List<Class<? extends T>> potentialImplTypes = new ArrayList<Class<? extends T>>();
        Iterator<Class<? extends T>> it = types.iterator();
        while (it.hasNext()) {
            Class<? extends T> potentialImplType = it.next();
            if (Modifier.isAbstract(potentialImplType.getModifiers())) {
                it.remove();
                continue;
            }
            potentialImplTypes.add(potentialImplType);
        }
        return potentialImplTypes;
    }

    /**
     * Returns a <b>single</b> potential implementation type for a given type.
     *
     * @param type the type to look for a potential implementation type
     * @param <T>  describes the type parameter
     * @return a <b>single</b> potential implementation type for the given type
     * @throws IllegalArgumentException if no potential implementation type could be found; or if 2 or more potential implementations types have bee found
     */
    private <T> Class<? extends T> getSinglePotentialImplType(Class<T> type) throws IllegalArgumentException {
        List<Class<? extends T>> potentialImplTypes = getPotentialImplTypes(type);
        switch (potentialImplTypes.size()) {
            case 0:
                // not good
                throw new IllegalArgumentException("no matching implementation of type " + type + "' found within '" + reflectionsCache.getUrls() + '"');
            case 1:
                // good
                return potentialImplTypes.get(0);
            default:
                // not good
                throw new IllegalArgumentException(potentialImplTypes.size() + " matching implementation types found within '" + reflectionsCache.getUrls() + '"');
        }
    }
}