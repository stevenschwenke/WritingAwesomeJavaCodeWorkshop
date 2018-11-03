package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The following examples are extracted from the talk "API-Design with Lambdas" by Angelika Langer.
 * See https://jaxenter.de/api-design-mit-lambdas-38265 for more details, topics and issues with
 * the code below.
 */
class APIDesignWithLambdas {

    ////////////////////////
    // Deferred Execution:
    //
    // Functionality is written so it can be executed when needed instead of being executed
    // immediately.
    //
    ////////////////////////

    /**
     * Calculating X is very expensive.
     *
     * @return X
     */
    // Access has to be public because of Mockito spy in the test.
    public int calcX() {
        System.out.println("expensive method calcX called");
        // Some expensive code here
        return 0;
    }

    /**
     * Calculating Y is very expensive.
     *
     * @return Y
     */
    // Access has to be public because of Mockito spy in the test.
    public int calcY() {
        System.out.println("expensive method calcY called");
        // Some expensive code here
        return 1;
    }

    /**
     * Does some calculation with two integer. Will always return 0 if one of those integers is 0.
     *
     * @param x first integer
     * @param y second integer
     * @return sum of both arguments except if one of those arguments is 0, then returns 0.
     */
    private int eager(int x, int y) {
        // "y!=0" is not called if x is already 0.
        if (x != 0 && y != 0) {
            return x + y;
        }
        return 0;
    }

    /**
     * Does some calculation with two integer that are provided by {@link
     * java.util.function.Supplier}s. Will always return 0 if one of those integers is 0. Second
     * supplier will only be called if integer from first supplier is not 0.
     *
     * @param xs supplier for first integer
     * @param ys supplier for second integer
     * @return sum of both arguments except if one of those arguments is 0, then returns 0.
     */
    private int lazy(IntSupplier xs, IntSupplier ys) {
        if (xs.getAsInt() != 0 && ys.getAsInt() != 0) {
            return xs.getAsInt() + ys.getAsInt();
        }
        return 0;
    }

    @Test
    void eagerWillCallCalcYEvenIfXIsZero() {
        APIDesignWithLambdas instance = spy(this);

        instance.eager(instance.calcX(), instance.calcY());

        verify(instance, atLeastOnce()).calcX();
        verify(instance, atLeastOnce()).calcY();
    }

    @Test
    void lazyWillNotCallCalcYIfXIsZero() {
        APIDesignWithLambdas instance = spy(this);

        instance.lazy(instance::calcX, instance::calcY);

        verify(instance, atLeastOnce()).calcX();
        verify(instance, never()).calcY();
    }

    @Test
    void otherExamplesOfDeferredExecution() {
        Map<Integer, Integer> myMap = new HashMap<>();

        System.out.println("Always puts a value into the map:");
        myMap.put(1, calcX());
        myMap.put(1, calcX());

        System.out.println("\nOnly puts a value into the map if it's not already there:");
        myMap.computeIfAbsent(2, k -> calcX());
        myMap.computeIfAbsent(2, k -> calcX());

        System.out.println("\nThis works for Consumers also:");
        OptionalInt myOptional = OptionalInt.of(42);
        myOptional.ifPresent(System.out::println);

        myOptional = OptionalInt.empty();
        myOptional.ifPresent(System.out::println); // will not be called because optional is empty.

        System.out.println("\nPredicates are also examples of deferred execution because they get "
                           + "executed multiple times when needed:");
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> predicate = i -> i > 5;
        integerStream.filter(predicate).forEach(System.out::print);
    }

    ////////////////////////
    // Flexible APIs:
    //
    // Use lambda expressions to substitute fixed APIs with generic ones to have a more flexible
    // API with more options.
    //
    ////////////////////////

    /**
     * This is a normal container class with some data in it and methods do get information
     * about this data.
     *
     * @param <V> Value type of this container
     */
    private class InflexibleContainer<V> {
        V value;

        InflexibleContainer(V value) {
            this.value = value;
        }

        boolean valueLonger5Chars() {
            return (value instanceof String) && ((String) value).length() > 5;
        }

        boolean valueStartsWith1() {
            return (value instanceof String) && ((String) value).startsWith("1");
        }
    }

    @Test
    void inflexibleContainerTest() {
        InflexibleContainer<String> box = new InflexibleContainer<>("100100101010");
        assertTrue(box.valueLonger5Chars());
        assertTrue(box.valueStartsWith1());
    }

    /**
     * This is a normal container class with some data in it and a method do get information
     * about this data. This method is written in a flexible and generic way, using a lambda.
     *
     * @param <V> Value type of this container
     */
    private class Container<V> {
        V value;

        Container(V value) {
            this.value = value;
        }

        boolean holdsValueWith(Predicate<? super V> property) {
            return property.test(value);
        }
    }

    @Test
    void flexibleContainerTest() {
        Container<String> box = new Container<>("100100101010");
        assertTrue(box.holdsValueWith(s -> s.length() > 5));
        assertTrue(box.holdsValueWith(s -> s.startsWith("1")));
    }

    @Test
    void otherExamplesOfFlexibleAPI() {

        Set<Integer> integerSet = new HashSet<>(Arrays.asList(1,2,3));
        assertEquals(3, integerSet.size());
        integerSet.removeIf(x -> x  == 3); // Flexible API
        assertEquals(2, integerSet.size());

        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Object[] array = integerStream.filter(x -> x < 3).toArray(); // filter() = flexible API
        assertEquals(2, array.length);

        assertThrows(RuntimeException.class, () -> {
            Optional<String> optional = Optional.empty();
            // Exception can be chosen by by user of the optional (flexible!) and is constructed
            // only when needed (deferred execution!).
            optional.orElseThrow(() -> new RuntimeException("My special exception"));
        });
    }

    ////////////////////////
    // Execute-around-Pattern:
    //
    // Target: eliminate duplicated code, for example from exception-handling.
    //
    ////////////////////////

    @Test
    void executeAroundPatternTest() {

        // Imagine a method call that throws many exceptions that have to be catched:

        try {
            Collection.class.getMethod("xyz", Collection.class).invoke(null);
        } catch (IllegalAccessException e) {
            // many lines of code 1
        } catch (InvocationTargetException e) {
            // many lines of code 2
        } catch (NoSuchMethodException e) {
            // many lines of code 3
        }

        // The catch-clauses have to be copy-pasted everywhere this kind of code is called.

        // This can be much shorter with the execute-around-pattern:
        handleExec(() -> Collection.class.getMethod("xyz", Collection.class).invoke(null));
    }

    /**
     * This methods holds the code that is the same everywhere the duplication occurs.
     *
     * @param action the code that causes the exceptions
     */
    private void handleExec(ReflectiveExecution action) {
        try {
            action.run();
        } catch (IllegalAccessException e) {
            // many lines of code 1
        } catch (InvocationTargetException e) {
            // many lines of code 2
        } catch (NoSuchMethodException e) {
            // many lines of code 3
        }
    }

    /**
     * This small functional interface holds the code that is different everywhere the former
     * duplication occurred.
     */
    private interface ReflectiveExecution {
        void run() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
    }
}
