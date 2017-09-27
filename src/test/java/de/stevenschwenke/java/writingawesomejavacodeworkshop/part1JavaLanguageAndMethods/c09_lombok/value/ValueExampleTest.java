package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.value;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ValueExampleTest {

    @Test
    public void value() throws Exception {

        // normal constructor
        ValueExample value1 = new ValueExample("name", 12, 12d, new String[]{});

        //value1.set // no setter

        // "wither"
        ValueExample value2 = value1.withAge(42);

        assertNotEquals(value1, value2);
        assertFalse(value1 == value2);
    }
}
