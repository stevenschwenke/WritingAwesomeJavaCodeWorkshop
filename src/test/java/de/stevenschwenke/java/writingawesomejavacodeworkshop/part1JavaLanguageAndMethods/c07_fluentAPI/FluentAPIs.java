package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c07_fluentAPI;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This chapter explains what fluent APIs are, how to write them and where they are already used.
 */
public class FluentAPIs {

    /*
        Fluent APIs are APIs that are designed to support method calls like this:
     */
    @Test
    public void myCustomFluentAPITest() {
        Store myStore = new Store();
        myStore.with("Desk").with("Rack").with("Chair").openFrom(LocalTime.of(9, 0))
            .openUntil(LocalTime.of(18, 0));

        // This is an alternative to the builder pattern.
    }

    /**
     * All methods in this class return the instance of this class ("this"). That way, multiple
     * method calls can be made on this class in one line of code. The result of the current
     * operation is the base for the new operation.
     */
    private class Store {

        private Set<String> furniture = new HashSet<>();
        private LocalTime openingHoursStart;
        private LocalTime openingHoursEnd;

        // Notice this method seems to be a add- method from the Collection-interface. However, add-
        // methods from this interface don't return an instance of the collection and can not be
        // used to build a fluent API.
        Store with(String newFurniture) {
            furniture.add(newFurniture);
            return this;
        }

        // Notice these two methods are similar to setters, but they return a value.
        // Both of these methods break the Java naming convention and can not be used as setters by
        // frameworks that rely on finding methods by the naming convention. That is the reason
        // why I didn't name this method "setOpenFrom" - to set it apart from the (non-existing)
        // setter.
        Store openFrom(LocalTime openingHoursStart) {
            this.openingHoursStart = openingHoursStart;
            return this;
        }

        Store openUntil(LocalTime openingHoursEnd) {
            this.openingHoursEnd = openingHoursEnd;
            return this;
        }

        @Override
        public String toString() {
            return "Store{" +
                   "furniture=" + furniture +
                   ", openingHoursStart=" + openingHoursStart +
                   ", openingHoursEnd=" + openingHoursEnd +
                   '}';
        }
    }

    // Maybe you want to write fluent APIs that are used as factories, but don't want to write
    // code like the above one. Maybe the @Builder annotation in Lombok (https://projectlombok.org)
    // is for you. However, it needs plugins to enable annotation processors to compile.

    @Test
    public void examplesForFluentAPIs() {
        // Streams:
        Stream<Double> stream1 = Stream.generate(Math::random);
        stream1.limit(3).sorted().forEach(System.out::println);

        // Date & Time API:
        System.out.println(
            LocalTime.of(8, 0).atDate(LocalDate.now()).plusDays(1).atZone(ZoneId.systemDefault()).format(
                DateTimeFormatter.ISO_DATE_TIME));

        // NUnit:
        // Assert.That( result, Is.EqualTo(4.0).Within(0.01));
        // => Example that you don't need to return "this" every time, but also other objects.
    }

    /*
        - One of the first mentions of Fluent API Design by Martin Fowler here:
          http://martinfowler.com/bliki/FluentInterface.html
        - Martin states in his blog post, that Fluent APIs violate the Command-Query-Separation-
          Principle (http://martinfowler.com/bliki/CommandQuerySeparation.html)
        - important: Methods in fluent APIs often don't make sense on their own, just when used
          fluently. => Use them only in Value Objects, not in business logic. However make sure that
          classes that are to be persisted have "real" getters and setters, too.
     */

    ////////////////////////
    // Using Fluent APIs to write DSLs (Domain Specific Languages).
    // Code taken from https://blog.jooq.org/2012/01/05/the-java-fluent-api-designer-crash-course/
    //
    // This example illustrates how a fluent API can be designed. However, chains of calls through
    // too much classes and methods can become unreadable easily. Also, code formatting can render
    // your nice-looking chain an unreadable trainwreck.
    ////////////////////////

    // Initial interface, entry point of the DSL
    // Depending on your DSL's nature, this can also be a class with static
    // methods which can be static imported making your DSL even more fluent
    interface Start {

        End singleWord();

        End parameterisedWord(String parameter);

        Intermediate1 word1();

        Intermediate2 word2();

        Intermediate3 word3();
    }

    // Terminating interface, might also contain methods like execute();
    interface End {

        void end();
    }

    // Intermediate DSL "step" extending the interface that is returned
    // by optionalWord(), to make that method "optional"
    interface Intermediate1 extends End {

        End optionalWord();
    }

    // Intermediate DSL "step" providing several choices (similar to Start)
    interface Intermediate2 {

        End wordChoiceA();

        End wordChoiceB();
    }

    // Intermediate interface returning itself on word3(), in order to allow
    // for repetitions. Repetitions can be ended any time because this
    // interface extends End
    interface Intermediate3 extends End {

        Intermediate3 word3();
    }

    private class DSLStart implements Start {

        @Override
        public End singleWord() {
            return null;
        }

        @Override
        public End parameterisedWord(String parameter) {
            return null;
        }

        @Override
        public Intermediate1 word1() {
            return null;
        }

        @Override
        public Intermediate2 word2() {
            return null;
        }

        @Override
        public Intermediate3 word3() {
            return null;
        }
    }

    @Test
    public void testingOurDSL() {
        Start start = new DSLStart();

        start.singleWord().end();
        start.parameterisedWord("abc").end();

        start.word1().end();
        start.word1().optionalWord().end();

        start.word2().wordChoiceA().end();
        start.word2().wordChoiceB().end();

        start.word3().end();
        start.word3().word3().end();
        start.word3().word3().word3().end();
    }
}
