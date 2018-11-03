package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.constructor;

import org.junit.jupiter.api.Test;

class ConstructorExampleTest {

    @Test
    void constructorExampleTest() {

        // "of"-Constructor:
        ConstructorExample<String> description = ConstructorExample.of("description");

        // no-args-constructor:
        ConstructorExample.NoArgsExample noArgsExample1 = new ConstructorExample.NoArgsExample();

        // all-args-constructor:
        ConstructorExample<String> allArgs = new ConstructorExample<>(1, 2, "description");
    }
}