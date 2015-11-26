package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangCharacterArrayConstructor;

public class BeanWithJavaLangCharacterArrayConstructorImpl implements BeanWithJavaLangCharacterArrayConstructor {

    private final Character[] testCharacters;

    public BeanWithJavaLangCharacterArrayConstructorImpl(Character[] testCharacters) {
        this.testCharacters = testCharacters;
    }

    @Override
    public Character[] getTestCharacters() {
        return testCharacters;
    }
}
