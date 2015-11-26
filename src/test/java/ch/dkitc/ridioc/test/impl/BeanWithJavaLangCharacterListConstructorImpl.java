package ch.dkitc.ridioc.test.impl;

import java.util.List;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangCharacterListConstructor;

public class BeanWithJavaLangCharacterListConstructorImpl implements BeanWithJavaLangCharacterListConstructor {

    private final List<Character> testCharacters;

    public BeanWithJavaLangCharacterListConstructorImpl(List<Character> testCharacters) {
        this.testCharacters = testCharacters;
    }

    @Override
    public List<Character> getTestCharacters() {
        return testCharacters;
    }
}
