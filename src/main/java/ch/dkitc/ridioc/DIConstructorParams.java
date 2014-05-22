package ch.dkitc.ridioc;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DIConstructorParams implements Iterable<DIConstructorParam> {

    private final List<DIConstructorParam> constructorParams = new ArrayList<DIConstructorParam>();

    public DIConstructorParams() {
    }

    @Override
    public Iterator<DIConstructorParam> iterator() {
        return constructorParams.iterator();
    }

    public DIConstructorParam add(String paramName, Class<?> paramType, List<Annotation> annotations, List<Class<?>> genericTypes) {
        DIConstructorParam constructorParam = new DIConstructorParam(paramName, paramType, annotations, genericTypes);
        constructorParams.add(constructorParam);
        return constructorParam;
    }

    public int size() {
        return constructorParams.size();
    }
}
