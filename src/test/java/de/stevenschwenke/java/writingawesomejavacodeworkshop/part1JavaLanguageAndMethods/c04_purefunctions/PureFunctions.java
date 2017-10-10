package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c04_purefunctions;

/**
 * Pure functions can be used to write maintainable code that is much easier to debug.
 */
public class PureFunctions {

    /*
        Pure functions are functions that
        1. always evaluates the same result value given the same argument value(s)
        2. don't have any side effects.
     */

    private int y;

    private int myPureFunction(int x) {
        return x + 1;
    }

    // According to IDEA, this impure function is pure. However, it uses the environment and cannot be made static.
    private int myPureFunction2(int x) {
        return x + y +1;
    }

    private int myNotSoPureFunction(int x) {
        System.out.println(x); // Side effect!
        return x + 1;
    }

    private int myNotSoPureFunction2(int x) {
        y = 0;  // Side effect!
        return x + 1;
    }

    /*
        Main benefits of using pure functions:
        - Much easier to understand, especially when debugging.
        - Developers tend to write shorter methods when these methods are pure functions

        Finding out if a method is a pure function:
        - (just a hint) add a temporary "static" => doesn't work for myPureFunction2!
        - let the IDE do the work for you: IDEA shows a little icon at the line of the method
     */

}
