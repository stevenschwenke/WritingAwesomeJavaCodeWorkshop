package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.adrianbolboaca;

import de.stevenschwenke.java.writingawesomejavacodeworkshop.part3ApplyingToLegacyCode.legacy_ugly_trivia.Game;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

/**
 * These are the resulting tests from Adrian Bolboacas great Code Cast, see
 * http://blog.adrianbolboaca.ro/2014/04/from-nothing-to-system-tests-code-cast/.
 * I enhanced them by using the the great System Rules (http://stefanbirkner.github.io/system-rules/).
 */
public class GameTestsEpisode1 {

    private Game game;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void WhenGameIsCreatedNothingIsWrittenToTheOutput() {
        String systemOut = systemOutRule.getLog();
        String emptyString = ""; // extracted to variable to make clear my intent was to have an empty output

        assertEquals(emptyString, systemOut);
    }

    @Test
    public void whenPlayerIsAddedHisNameAndPlayerNumberIsWritten() {
        String playerNameAndNumber = "Adi was added\r\n"
                + "They are player number 1\r\n";
        String playerName = "Adi";

        game.add(playerName);

        assertEquals(playerNameAndNumber, systemOutRule.getLog());
    }

    @Test
    public void whenTwoPlayersAreAddedTheirNameAndPlayerNumbersAreWritten() {
        String playerNameAndNumber = "Adi was added\r\n" +
                "They are player number 1\r\n" +
                "Alex was added\r\n" +
                "They are player number 2\r\n";
        String playerName = "Adi";
        String secondPlayerName = "Alex";

        game.add(playerName);
        game.add(secondPlayerName);

        assertEquals(playerNameAndNumber, systemOutRule.getLog());
    }

    @Test
    public void whenRollingDiceNamesOfPlayersAndGameCategoryIsWritte() {
        game.add("SomePlayer");

        game.roll(1);

        assertEquals("SomePlayer was added\r\n" +
                "They are player number 1\r\n" +
                "SomePlayer is the current player\r\n" +
                "They have rolled a 1\r\n" +
                "SomePlayer's new location is 1\r\n" +
                "The category is Science\r\n" +
                "Science Question 0\r\n", systemOutRule.getLog());
    }
}
