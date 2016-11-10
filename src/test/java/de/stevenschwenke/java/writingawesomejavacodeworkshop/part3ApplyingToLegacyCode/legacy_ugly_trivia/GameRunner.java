package de.stevenschwenke.java.writingawesomejavacodeworkshop.part3ApplyingToLegacyCode.legacy_ugly_trivia;


import java.util.Random;

/**
 * This is the Java implementation of the "ugly trivia game", see
 * https://github.com/jbrains/trivia/blob/master/java/src/main/java/com/adaptionsoft/games/uglytrivia/Game.java
 */
public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random();

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
