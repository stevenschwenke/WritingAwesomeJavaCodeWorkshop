This is a transcript of the brillant Code Cast from [Adrian Bolboaca](http://blog.adrianbolboaca.ro/). Adrian provides a step by step walkthrough about how to write tests for the [ugly trivia game by J. B. Rainsberger](https://github.com/jbrains/trivia). [Here's](http://blog.adrianbolboaca.ro/2014/04/from-nothing-to-system-tests-code-cast/) the first episode of Adrians Code Cast. I strongly recommend having a look at this - it's awesome!

# Episode 1
- ["From nothing to system tests"](http://blog.adrianbolboaca.ro/2014/04/from-nothing-to-system-tests-code-cast/)
- How to write black box tests
- tests are safety net for later refactoring
- logic: Make a guess => write characterization test based on guess => Test guess => Modification

## Test 1
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

## Test 2
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

## Test 3
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

## Test 4
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

## Using System Rules to hijack system.out
The method getConsoleOutput used above can be replaced by using [System Rules](http://stefanbirkner.github.io/system-rules/index.html) by [Stefan Birkner](http://www.stefan-birkner.de/). Here's the setup and first test:

   ```java
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
    
    // ...
   ``` 

# Episode 2
- ["Golden Master"](http://blog.adrianbolboaca.ro/2014/05/golden-master-code-cast/)
- description of technique see HowToDealWithLegacyCode.md
- code see package ep_2_golden_master
1. Refactor GameRunner: extract method play(Random rand)
1. Write GoldenMaster.java to create Golden Master and test against it
1. Write GameTestsEpisode2.java to execute tests

# Episode 3
- [Fix bugs on legacy code](http://blog.adrianbolboaca.ro/2014/05/fix-bugs-on-legacy-code-code-cast/)
- bug report:
 The message announcing a correct answer should be "Answer was correct!!!!" but it is "Answer was corrent!!!!"
- goal: fix defect without introducing new defects
- code see package ep_3_fix_a_bug

## Steps
1. Search String "Answer was corrent!!!!" -> Game:wasCorrectlyAnswered()
1. write system test to verify current behavior (with the defect)

   ```java
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
    ```

1. extract and isolate defect: extract String in a method getCorrectAnswerMessage()
1. write a test that clearly shows the bug
   ```java
    @Test
    public void correctAnswerMessageIsValid() {
        Game game = new Game();
        String expected = "Answer was corrent!!!!";
        String actual = game.getCorrectAnswerMessage();

        assertEquals(expected, actual);
    }
    ```
1. write new test with correct answer message
   ```java
    @Test
    public void correctAnswerMessageChangedBecauseOfBug() {
        Game game = new Game();
        String expected = "Answer was correct!!!!";
        String actual = game.getCorrectAnswerMessage();

        assertEquals(expected, actual);
    }
    ```
    => Test will fail!
1. Change text in production code, so that the last test runs green but the test correctAnswerMessageIsValid() and the first system test fail. => Very important because that is the proof that defect has been fixed. Without these tests, there would be no way of telling if the defect has been fixed.
1. change system test to correct text
1. delete only failing test correctAnswerMessageIsValid() - one of those rare moments when deleting a test is OK ;)