package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.adrianbolboaca.ep_2_golden_master;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * These are the resulting tests from Adrian Bolboacas great Code Cast, see
 * http://blog.adrianbolboaca.ro/2014/05/golden-master-code-cast/.
 */
class GameTestsEpisode2 {

    @Disabled("Ignored because this should only be called once to create Golden Master data.")
    @Test
    void generateGoldenMasters() throws IOException {
        GoldenMaster goldenMaster = new GoldenMaster();
        goldenMaster.generateGoldenMaster();
    }

    @Test
    void checkTriviaAgainstGoldenMaster() throws IOException {
        GoldenMaster goldenMaster = new GoldenMaster();

        for (long seed = 0; seed < GoldenMaster.SAMPLESIZE; seed++) {
            String expected = goldenMaster.getGoldenMaster(seed);
            String actual = goldenMaster.getGameResult(seed);
            assertEquals(expected, actual);
        }
    }
}
