package de.stevenschwenke.java.javaadvancedworkshop.hashcodeandequals;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class HashCodeTest {

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
