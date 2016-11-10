package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods;

import org.junit.Test;

/**
 * This class contains things to know about {@link String}.
 *
 * Extracted from Bernd Müllers talk, see
 * http://www.jug-ostfalen.de/article/2016/02/04/string-folien.html
 */
public class ThingsToKnowAboutStrings {

    @Test
    public void stringBufferAndStringBuilder() {
        // Know the difference between

        String sbuffer = new StringBuffer("a").append("b").append("c").toString();
        // and
        String sbuilder = new StringBuilder("a").append("b").append("c").toString();
        // ?

        // Note the warning at StringBuffer and StringBuilder? That's because "+" is optimized in
        // today's JDKs and its performance is as well as those two.
        // HOWEVER: Don't use "+" in for-loops because the compiler optimization doesn't work there.
    }

    // There are many more things to know about Strings, but that is not the focus of this workshop.
}
