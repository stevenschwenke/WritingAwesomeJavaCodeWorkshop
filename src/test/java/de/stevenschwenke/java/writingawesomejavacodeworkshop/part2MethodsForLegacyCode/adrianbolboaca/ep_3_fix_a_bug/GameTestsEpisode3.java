package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.adrianbolboaca.ep_3_fix_a_bug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * These are the resulting tests from Adrian Bolboacas great Code Cast, see
 * http://blog.adrianbolboaca.ro/2014/05/fix-bugs-on-legacy-code-code-cast/.
 * <p>
 * Bug report:
 * The message announcing a correct answer should be
 * "Answer was correct!!!!"
 * but it is
 * "Answer was corrent!!!!"
 */
class GameTestsEpisode3 {

    private ByteArrayOutputStream consoleOutput;

    @BeforeEach
    void setup() {
        consoleOutput = getConsoleOutput();
    }

    private ByteArrayOutputStream getConsoleOutput() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);
        return stream;
    }

    /**
     * Attempt to write a system test: Simply calling method with defect in it. Results in an exception.
     */
    @Test
    void createSystemTestAttempt1() {

        assertThrows(IndexOutOfBoundsException.class, () -> {

            Game_unmodified game = new Game_unmodified();
            game.wasCorrectlyAnswered();

            assertEquals("", consoleOutput.toString());
        });
    }

    /**
     * Final version of system test: Trace source of exception -> There has to be a player added to the game.
     */
    @Test
    void gameWasCorrectlyAnsweredWhenNotInPenaltyBox() {
        Game_unmodified game = new Game_unmodified();
        game.add("player1");
        game.wasCorrectlyAnswered();

        assertEquals("player1 was added\r\n" +
                "They are player number 1\r\n" +
                "Answer was corrent!!!!\r\n" +
                "player1 now has 1 Gold Coins.\r\n", consoleOutput.toString());
    }

    @Disabled("Will be deleted later on")
    @Test
    void correctAnswerMessageIsValid() {
        Game game = new Game();
        String expected = "Answer was corrent!!!!";
        String actual = game.getCorrectAnswerMessage();

        assertEquals(expected, actual);
    }

    @Test
    void correctAnswerMessageChangedBecauseOfBug() {
        Game game = new Game();
        String expected = "Answer was correct!!!!";
        String actual = game.getCorrectAnswerMessage();

        assertEquals(expected, actual);
    }
}
