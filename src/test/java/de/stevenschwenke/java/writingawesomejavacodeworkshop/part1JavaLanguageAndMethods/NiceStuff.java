package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods;

import org.junit.jupiter.api.Test;

/**
 * Demonstrates some nice features.
 */
class NiceStuff {

    @Test
    void readabilityOfBigIntegers() {

        // big integers can have separators with "_":
        int x = 1_000_000;

        // However, these are not semantically checked:
        int y = 1_00;
    }

    @Test
    void switchOverStrings() {

        String string = "a";

        // Since Java 7 it's possible to switch over a String:

        switch (string) {
            case "a":
                System.out.println("a");

            default:
                System.out.println("not a");
        }

    }
}
