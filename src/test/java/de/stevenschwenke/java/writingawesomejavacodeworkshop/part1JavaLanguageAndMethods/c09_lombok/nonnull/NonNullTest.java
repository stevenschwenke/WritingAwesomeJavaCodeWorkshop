package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.nonnull;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NonNullTest {

    @Test
    void nonNullTest() {
        assertThrows(NullPointerException.class, () -> {
            NonNullExampleLombok x = new NonNullExampleLombok(null);
        });
    }
}
