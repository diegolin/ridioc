package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangLongArrayConstructor;

public class BeanWithJavaLangLongArrayConstructorImpl implements BeanWithJavaLangLongArrayConstructor {

    private final Long[] testLongs;

    public BeanWithJavaLangLongArrayConstructorImpl(Long[] testLongs) {
        this.testLongs = testLongs;
    }

    @Override
    public Long[] getTestLongs() {
        return testLongs;
    }
}
