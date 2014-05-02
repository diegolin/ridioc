package ch.dkitc.ridioc;

import java.util.ArrayList;
import java.util.List;

public class DIAggregateException extends IllegalArgumentException {

    private List<Exception> exceptionBasket = new ArrayList<Exception>();

    public DIAggregateException(String message, List<Exception> exceptions) {
        super(message);
        exceptionBasket.addAll(exceptions);
    }

    public List<Exception> getExceptionBasket() {
        return exceptionBasket;
    }

    public boolean hasExceptionsInBasket() {
        return !exceptionBasket.isEmpty();
    }
}
