package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataExampleLombokTest {

    @Test
    public void lombokGeneratesConstructorAndGetter() throws Exception {

        // Annotation "data" = toString + equalsAndHashCode + Getter + Setter + RequiredArgsConstructor

        DataExampleLombok data = new DataExampleLombok("name");
        assertEquals("name", data.getName());
    }
}
