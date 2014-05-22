package ch.dkitc.ridioc;

import java.util.HashMap;

public class DIMethodParamsIndexMap extends HashMap<Class<?>, DIMethodParamsIndex> {

    private final DIInstanceMethodParams instanceMethodParams;

    public DIMethodParamsIndexMap(DIInstanceMethodParams instanceMethodParams) {
        this.instanceMethodParams = instanceMethodParams;
    }

    @Override
    public DIMethodParamsIndex get(Object constrParamType) {
        DIMethodParamsIndex index = super.get(constrParamType);
        if (index == null) {
            DIInstanceMethodParams methodParamsOfType = instanceMethodParams.getParamsOfType((Class<?>) constrParamType);
            index = new DIMethodParamsIndex(methodParamsOfType);
            put((Class<?>) constrParamType, index);
        }
        return index;
    }
}
