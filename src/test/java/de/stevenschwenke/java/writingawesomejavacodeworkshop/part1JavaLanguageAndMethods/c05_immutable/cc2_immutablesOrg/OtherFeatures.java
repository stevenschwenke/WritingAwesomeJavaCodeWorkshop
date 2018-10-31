package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.junit.jupiter.api.Test;

public class OtherFeatures {

    @Test
    public void constructorMethod() throws Exception {

        // of()- method = constructor with attributes annotated with "@Value.Parameter":
        ImmutableCar redCar = ImmutableCar.of(4, "red");

    }
}
