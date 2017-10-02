package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.value;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ValueWithCollectionTest {

    @Test
    public void collectionsInLombokAreNotImmutable() throws Exception {

        ValueWithCollection value = new ValueWithCollection("name", 12, 12d, new String[]{}, new ArrayList<>());

        assertEquals(0, value.getBlubbers().size());

        value.getBlubbers().add("blubber2");

        assertEquals(1, value.getBlubbers().size());
    }
}
