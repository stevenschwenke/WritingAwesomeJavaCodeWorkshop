package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.gettersetter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetterSetterTest {

    @Test
    void name() {
        GetterSetterExampleLombok object = new GetterSetterExampleLombok();
        object.setAge(12);
        assertEquals(12, object.getAge());
        object.setName("name");
        //object.getName();  // Only setter specified!
    }
}
