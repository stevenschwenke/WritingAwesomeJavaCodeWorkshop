package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.adrianbolboaca.ep_1_from_nothing_to_system_tests;

import de.stevenschwenke.java.writingawesomejavacodeworkshop.part3ApplyingToLegacyCode.legacy_ugly_trivia.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These are the resulting tests from Adrian Bolboacas great Code Cast, see
 * http://blog.adrianbolboaca.ro/2014/04/from-nothing-to-system-tests-code-cast/.
 */
public class GameTestsEpisode1 {

    private Game game;
    private ByteArrayOutputStream consoleOutput;

    @BeforeEach
    public void setup() {
        game = new Game();
        consoleOutput = getConsoleOutput();
    }

    private ByteArrayOutputStream getConsoleOutput() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);
        return stream;
    }

    @Test
    public void WhenGameIsCreatedNothingIsWrittenToTheOutput() {
        String emptyString = ""; // extracted to variable to make clear my intent was to have an empty output

        assertEquals(emptyString, consoleOutput.toString());
    }

    @Test
    public void whenPlayerIsAddedHisNameAndPlayerNumberIsWritten() {
        String playerNameAndNumber = "Adi was added\r\n"
                + "They are player number 1\r\n";
        String playerName = "Adi";

        game.add(playerName);

        assertEquals(playerNameAndNumber, consoleOutput.toString());
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

        assertEquals(playerNameAndNumber, consoleOutput.toString());
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
                "Science Question 0\r\n", consoleOutput.toString());
    }
}
