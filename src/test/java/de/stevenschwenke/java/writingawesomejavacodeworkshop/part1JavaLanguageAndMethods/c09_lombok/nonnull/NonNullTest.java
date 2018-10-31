package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.nonnull;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NonNullTest {

    @Test
    public void nonNullTest() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            NonNullExampleLombok x = new NonNullExampleLombok(null);
        });
    }
}
