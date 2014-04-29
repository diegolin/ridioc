package ch.dkitc.ridioc;

public class DIMethodParamsIndex {

    private final DIInstanceMethodParams methodParams;
    private int index = 0;

    public DIMethodParamsIndex(DIInstanceMethodParams methodParams) {
        this.methodParams = methodParams;
    }

    public DIInstanceMethodParam next() {
        try {
            DIInstanceMethodParam methodParam = methodParams.get(index);
            index++;
            return methodParam;
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
}
