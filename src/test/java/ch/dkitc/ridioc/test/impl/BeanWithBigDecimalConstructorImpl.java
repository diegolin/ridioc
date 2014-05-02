package ch.dkitc.ridioc.test.impl;

import java.math.BigDecimal;

import ch.dkitc.ridioc.test.api.BeanWithBigDecimalConstructor;

public class BeanWithBigDecimalConstructorImpl implements BeanWithBigDecimalConstructor {

    private final BigDecimal testBigDecimal;

    public BeanWithBigDecimalConstructorImpl(BigDecimal testBigDecimal) {
        this.testBigDecimal = testBigDecimal;
    }

    @Override
    public BigDecimal getTestBigDecimal() {
        return testBigDecimal;
    }
}
