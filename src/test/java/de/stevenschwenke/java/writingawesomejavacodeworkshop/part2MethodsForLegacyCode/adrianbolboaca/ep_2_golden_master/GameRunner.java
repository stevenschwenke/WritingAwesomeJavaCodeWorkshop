package de.stevenschwenke.java.writingawesomejavacodeworkshop.part2MethodsForLegacyCode.adrianbolboaca.ep_2_golden_master;


import de.stevenschwenke.java.writingawesomejavacodeworkshop.part3ApplyingToLegacyCode.legacy_ugly_trivia.Game;

import java.util.Random;

/**
 * This is a modivide implementation of the "ugly trivia game" from
 * https://github.com/jbrains/trivia/blob/master/java/src/main/java/com/adaptionsoft/games/uglytrivia/Game.java
 *
 * Difference to original: method play() has been extracted to allow usage of Golden Master.
 */
public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        Random rand = new Random();
        play(rand);

    }

    static void play(Random rand) {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");


        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }



        } while (notAWinner);
    }
}
