package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DataExampleLombokTest {

    @Test
    void lombokGeneratesConstructorAndGetter() {

        // Annotation "data" = toString + equalsAndHashCode + Getter + Setter + RequiredArgsConstructor

        DataExampleLombok data = new DataExampleLombok("name");
        assertEquals("name", data.getName());
    }
}
