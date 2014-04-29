package ch.dkitc.ridioc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DIInstanceMethodParams implements Iterable<DIInstanceMethodParam> {

    private final List<DIInstanceMethodParam> methodParams = new ArrayList<DIInstanceMethodParam>();

    public DIInstanceMethodParams(Object... params) {
        for (Object param : params) {
            add(param);
        }
    }

    @Override
    public Iterator<DIInstanceMethodParam> iterator() {
        return methodParams.iterator();
    }

    public DIInstanceMethodParam add(Object param) throws IllegalArgumentException {
        if (param == null) {
            throw new IllegalArgumentException("'param' must NOT be null");
        }
        DIInstanceMethodParam methodParam = new DIInstanceMethodParam(param.getClass(), param);
        methodParams.add(methodParam);
        return methodParam;
    }

    public DIInstanceMethodParams getParamsOfType(Class<?> paramType) {
        DIInstanceMethodParams paramsOfType = new DIInstanceMethodParams();
        for (DIInstanceMethodParam methodParam : methodParams) {
            if (paramType.isAssignableFrom(methodParam.getType())) {
                paramsOfType.add(methodParam);
            }
        }
        return paramsOfType;
    }

    private void add(DIInstanceMethodParam methodParam) {
        methodParams.add(methodParam);
    }

    public DIInstanceMethodParam get(int index) {
        return methodParams.get(index);
    }

    public int size() {
        return methodParams.size();
    }
}
