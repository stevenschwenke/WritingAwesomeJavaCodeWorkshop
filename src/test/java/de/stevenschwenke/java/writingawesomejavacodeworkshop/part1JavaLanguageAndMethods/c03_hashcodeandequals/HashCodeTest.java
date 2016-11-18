package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c03_hashcodeandequals;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

public class HashCodeTest {

    /*

    Beginners question: What are hashCode() and equals() ? Why are they the best friends (BFF) ever?

    Pro-question: What ways do you know to implement hashCode() and equals() ? What are the (dis)advantages?

     */

    @Test
    public void objectInHashSetGetsLostWhenHashCodeChanges() {
        Set<Person> crowd = new HashSet<>();
        Person hans = new Person("Hans");
        crowd.add(hans);

        // If this line is uncommented, the person known as Hans cannot be found in the crowd,
        // because his name changed and each person is identified by its name. The change of
        // the name changed the hash code of the object, which causes the HashSet to "loose"
        // the object.
//        hans.setName("Peter");

        assertTrue(crowd.contains(hans));
    }
}
