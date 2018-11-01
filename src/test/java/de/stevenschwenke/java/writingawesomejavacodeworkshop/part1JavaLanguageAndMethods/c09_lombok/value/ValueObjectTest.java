package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ValueObjectTest {

    @Test
    void value() {

        // normal constructor
        ValueObject value1 = new ValueObject("name", 12, 12d, new String[]{});

        //value1.set // no setter

        // "wither"
        ValueObject value2 = value1.withAge(42);

        assertNotEquals(value1, value2);
        assertFalse(value1 == value2);
    }
}
