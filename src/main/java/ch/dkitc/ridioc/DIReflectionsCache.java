package ch.dkitc.ridioc;

import org.reflections.Reflections;

import java.net.URL;
import java.util.Set;

public class DIReflectionsCache {

    private final String packagePrefix;
    private Reflections reflections;

    public DIReflectionsCache(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        return getReflections().getSubTypesOf(type);
    }

    public Set<URL> getUrls() {
        return getReflections().getConfiguration().getUrls();
    }

    private Reflections getReflections() {
        if (reflections == null) {
            reflections = new Reflections(packagePrefix);
        }
        return reflections;
    }
}
