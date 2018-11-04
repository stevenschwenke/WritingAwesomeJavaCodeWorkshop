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
class GameTestsEpisode1 {

    private Game game;
    private ByteArrayOutputStream consoleOutput;

    @BeforeEach
    void setup() {
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
    void WhenGameIsCreatedNothingIsWrittenToTheOutput() {
        String emptyString = ""; // extracted to variable to make clear my intent was to have an empty output

        assertEquals(emptyString, consoleOutput.toString());
    }

    @Test
    void whenPlayerIsAddedHisNameAndPlayerNumberIsWritten() {
        String playerNameAndNumber = "Adi was added" + System.lineSeparator()
                + "They are player number 1" + System.lineSeparator();
        String playerName = "Adi";

        game.add(playerName);

        assertEquals(playerNameAndNumber, consoleOutput.toString());
    }

    @Test
    void whenTwoPlayersAreAddedTheirNameAndPlayerNumbersAreWritten() {
        String playerNameAndNumber = "Adi was added" + System.lineSeparator() +
                "They are player number 1" + System.lineSeparator() +
                "Alex was added" + System.lineSeparator() +
                "They are player number 2" + System.lineSeparator();
        String playerName = "Adi";
        String secondPlayerName = "Alex";

        game.add(playerName);
        game.add(secondPlayerName);

        assertEquals(playerNameAndNumber, consoleOutput.toString());
    }

    @Test
    void whenRollingDiceNamesOfPlayersAndGameCategoryIsWritte() {
        game.add("SomePlayer");

        game.roll(1);

        assertEquals("SomePlayer was added" + System.lineSeparator() +
                "They are player number 1" + System.lineSeparator() +
                "SomePlayer is the current player" + System.lineSeparator() +
                "They have rolled a 1" + System.lineSeparator() +
                "SomePlayer's new location is 1" + System.lineSeparator() +
                "The category is Science" + System.lineSeparator() +
                "Science Question 0" + System.lineSeparator(), consoleOutput.toString());
    }
}
