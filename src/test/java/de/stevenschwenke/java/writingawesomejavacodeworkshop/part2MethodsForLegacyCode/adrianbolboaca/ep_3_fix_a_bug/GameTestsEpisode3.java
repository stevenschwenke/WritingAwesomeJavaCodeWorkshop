package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.adrianbolboaca.ep_3_fix_a_bug;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

/**
 * These are the resulting tests from Adrian Bolboacas great Code Cast, see
 * http://blog.adrianbolboaca.ro/2014/05/fix-bugs-on-legacy-code-code-cast/.
 *
 * Bug report:
 * The message announcing a correct answer should be
 * "Answer was correct!!!!"
 * but it is
 * "Answer was corrent!!!!"
 */
public class GameTestsEpisode3 {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    /**
     * Attempt to write a system test: Simply calling method with defect in it. Results in an exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void createSystemTestAttempt1() {
        Game_unmodified game = new Game_unmodified();
        game.wasCorrectlyAnswered();

        assertEquals("", systemOutRule.getLog());
    }

    /**
     * Final version of system test: Trace source of exception -> There has to be a player added to the game.
     */
    @Test
    public void gameWasCorrectlyAnsweredWhenNotInPenaltyBox() {
        Game_unmodified game = new Game_unmodified();
        game.add("player1");
        game.wasCorrectlyAnswered();

        assertEquals("player1 was added\r\n" +
                "They are player number 1\r\n" +
                "Answer was corrent!!!!\r\n" +
                "player1 now has 1 Gold Coins.\r\n", systemOutRule.getLog());
    }

    @Ignore("Will be deleted later on")
    @Test
    public void correctAnswerMessageIsValid() {
        Game game = new Game();
        String expected = "Answer was corrent!!!!";
        String actual = game.getCorrectAnswerMessage();

        assertEquals(expected, actual);
    }

    @Test
    public void correctAnswerMessageChangedBecauseOfBug() {
        Game game = new Game();
        String expected = "Answer was correct!!!!";
        String actual = game.getCorrectAnswerMessage();

        assertEquals(expected, actual);
    }
}
