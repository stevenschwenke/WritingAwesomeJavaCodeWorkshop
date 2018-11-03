package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c07_fluentAPI;

import org.junit.jupiter.api.Test;

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
class FluentAPIs {

    /*
        Fluent APIs are APIs that are designed to support method calls like this:
     */
    @Test
    void myCustomFluentAPITest() {
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
    void examplesForFluentAPIs() {
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

}
