package ch.dkitc.ridioc.test.impl;

import java.math.BigInteger;

import ch.dkitc.ridioc.test.api.BeanWithBigIntegerConstructor;

public class BeanWithBigIntegerConstructorImpl implements BeanWithBigIntegerConstructor {

    private final BigInteger testBigInteger;

    public BeanWithBigIntegerConstructorImpl(BigInteger testBigInteger) {
        this.testBigInteger = testBigInteger;
    }

    @Override
    public BigInteger getTestBigInteger() {
        return testBigInteger;
    }
}
