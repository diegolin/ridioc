package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.BeanWithCharacterConstructor;

public class BeanWithCharacterConstructorImpl implements BeanWithCharacterConstructor {

    private final Character testCharacter;

    public BeanWithCharacterConstructorImpl(Character testCharacter) {
        this.testCharacter = testCharacter;
    }

    @Override
    public Character getTestCharacter() {
        return testCharacter;
    }
}
