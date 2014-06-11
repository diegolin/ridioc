package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangLongListConstructor;

public class BeanWithJavaLangLongListConstructorImpl implements BeanWithJavaLangLongListConstructor {

    private final List<Long> testLongs;

    public BeanWithJavaLangLongListConstructorImpl(List<Long> testLongs) {
        this.testLongs = testLongs;
    }

    @Override
    public List<Long> getTestLongs() {
        return testLongs;
    }
}
