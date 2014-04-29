package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithEnumConstructor;
import ch.dkitc.ridioc.test.api.TestEnum;

public class TestBeanWithEnumConstructorImpl implements TestBeanWithEnumConstructor {

    private final TestEnum testEnum;

    public TestBeanWithEnumConstructorImpl(TestEnum testEnum) {
        this.testEnum = testEnum;
    }

    @Override
    public TestEnum getTestEnum() {
        return testEnum;
    }
}
