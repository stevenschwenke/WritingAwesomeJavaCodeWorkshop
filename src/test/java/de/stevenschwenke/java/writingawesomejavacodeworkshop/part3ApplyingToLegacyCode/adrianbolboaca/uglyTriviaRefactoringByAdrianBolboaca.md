This is a transcript of the brillant Code Cast from [Adrian Bolboaca](http://blog.adrianbolboaca.ro/). Adrian provides a step by step walkthrough about how to write tests for the [ugly trivia game by J. B. Rainsberger](https://github.com/jbrains/trivia). [Here's](http://blog.adrianbolboaca.ro/2014/04/from-nothing-to-system-tests-code-cast/) the first episode of Adrians Code Cast. I strongly recommend having a look at this - it's awesome!

#Episode 1:
- ["From nothing to system tests"](http://blog.adrianbolboaca.ro/2014/04/from-nothing-to-system-tests-code-cast/)
- How to write black box tests
- tests are safety net for later refactoring
- logic: Make a guess => write characterization test based on guess => Test guess => Modification

##Test 1
1. name: "whenGameIsCreatedNothingHappens"
1. Hijack output
1. Just calling "new Game()" and see what happens. Insight: game is instantiated and prints empty string. This is the foundation of the following tests.
1. First refactoring: Name of test from "whenGameIsCreatedNothingHappens" to "WhenGameIsCreatedNothingIsWrittenToTheOutput"
1. Final version:

    ```java
    @Test
    public void WhenGameIsCreatedNothingIsWrittenToTheOutput() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);
        String emptyString = ""; // extracted to variable to make clear my intent was to have an empty output

        Game game = new Game();

        assertEquals(emptyString, stream.toString());
    }
    ```

##Test 2
1. execute GameRunner. Insight: output of player names. 
1. Guess: When player is added, output of player name
1. Test: game.add("Adi") => output of "Adi was added\nThey are player number 1" => guess was wrong! Change test so it runs green.
1. test name "whenPlayerIsAddedHisNameIsWritten" => "whenPlayerIsAddedHisNameAndPlayerNumberIsWritten"

    ```java
    @Test
    public void whenPlayerIsAddedHisNameAndPlayerNumberIsWritten() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        System.setOut(printStream);
        String playerNameAndNumber = "Adi was added\r\n"
                                     + "They are player number 1\r\n";
        String playerName = "Adi";

        Game game = new Game();
        game.add(playerName);

        assertEquals(playerNameAndNumber, stream.toString());
    }
     ```

##Test 3
1. same as test 2 but add 2 players
1. immediately green => guess of test 2 correct!
1. observation: in 3 tests 3x instantiation of Game and handling of output stream. Refactor to @before- and getConsoleOutput-Method

    ```java
     @Before
     public void setup() {
         game = new Game();
     }   
 
     private ByteArrayOutputStream getConsoleOutput() {
         ByteArrayOutputStream stream = new ByteArrayOutputStream();
         PrintStream printStream = new PrintStream(stream);
         System.setOut(printStream);
         return stream;
     }
 
    @Test
    public void whenTwoPlayersAreAddedTheirNameAndPlayerNumbersAreWritten() {
        ByteArrayOutputStream stream = getConsoleOutput();
        String playerNameAndNumber = "Adi was added\r\n" +
                                     "They are player number 1\r\n" +
                                     "Alex was added\r\n" +
                                     "They are player number 2\r\n";
        String playerName = "Adi";
        String secondPlayerName = "Alex";

        game.add(playerName);
        game.add(secondPlayerName);

        assertEquals(playerNameAndNumber, stream.toString());
    }
    ```

##Test 4
1. new test "whenRollingDiceSomethingHappens" with a call to game.roll(1), at first assert console to empty String
1. observation: IndexOutOfBoundsException.
1. black box to white box: Have a look at class Game. players.get(currentPlayer). players is an ArrayList. Insight: Add at least one player before roll(). Add game.add("SomePlayer"); to test. 
1. Observation: output != empty string
1. set output as expected output, test is green
1. observation: in test class duplication of "... was added\nThey are player number ..." => Tests are fragile. Changes in production code leads to many red tests.

    ```java
    @Test
    public void whenRollingDiceNamesOfPlayersAndGameCategoryIsWritte() {
        ByteArrayOutputStream stream = getConsoleOutput();
        game.add("SomePlayer");

        game.roll(1);

        assertEquals("SomePlayer was added\r\n" +
        "They are player number 1\r\n" +
        "SomePlayer is the current player\r\n"+
        "They have rolled a 1\r\n" +
        "SomePlayer's new location is 1\r\n"+
        "The category is Science\r\n"+
        "Science Question 0\r\n", stream.toString());
    }
     ```
