package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithJavaLangCharacterConstructor;

public class BeanWithJavaLangCharacterConstructorImpl implements BeanWithJavaLangCharacterConstructor {

    private final Character testCharacter;

    public BeanWithJavaLangCharacterConstructorImpl(Character testCharacter) {
        this.testCharacter = testCharacter;
    }

    @Override
    public Character getTestCharacter() {
        return testCharacter;
    }
}
