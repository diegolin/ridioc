package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithEnumConstructor;
import ch.dkitc.ridioc.test.api.MyEnum;

public class BeanWithEnumConstructorImpl implements BeanWithEnumConstructor {

    private final MyEnum testEnum;

    public BeanWithEnumConstructorImpl(MyEnum testEnum) {
        this.testEnum = testEnum;
    }

    @Override
    public MyEnum getTestEnum() {
        return testEnum;
    }
}
