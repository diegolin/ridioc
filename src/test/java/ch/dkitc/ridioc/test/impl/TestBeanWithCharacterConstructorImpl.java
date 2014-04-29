package ch.dkitc.ridioc.test.impl;

import ch.dkitc.ridioc.test.api.TestBeanWithCharacterConstructor;

public class TestBeanWithCharacterConstructorImpl implements TestBeanWithCharacterConstructor {

    private final Character testCharacter;

    public TestBeanWithCharacterConstructorImpl(Character testCharacter) {
        this.testCharacter = testCharacter;
    }

    @Override
    public Character getTestCharacter() {
        return testCharacter;
    }
}
