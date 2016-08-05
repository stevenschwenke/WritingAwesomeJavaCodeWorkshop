package de.stevenschwenke.java.writingawesomejavacodeworkshop;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.IntSupplier;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * The following examples are extracted from the talk "API-Design with Lambdas" by Angelika Langer.
 * See https://jaxenter.de/api-design-mit-lambdas-38265.
 */
public class APIDesign_AngelikaLanger {

    ////////////////////////
    // Deferred Execution
    ////////////////////////

    /**
     * Calculating X is very expensive.
     *
     * @return X
     */
    int calcX() {
        System.out.println("expensive method calcX called");
        // Some expensive code here
        return 0;
    }

    /**
     * Calculating Y is very expensive.
     *
     * @return Y
     */
    int calcY() {
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
    int eager(int x, int y) {
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
    int lazy(IntSupplier xs, IntSupplier ys) {
        if (xs.getAsInt() != 0 && ys.getAsInt() != 0) {
            return xs.getAsInt() + ys.getAsInt();
        }
        return 0;
    }

    @Test
    public void eagerWillCallCalcYEvenIfXIsZero() {
        APIDesign_AngelikaLanger instance = spy(this);

        instance.eager(instance.calcX(), instance.calcY());

        verify(instance, atLeastOnce()).calcX();
        verify(instance, atLeastOnce()).calcY();
    }

    @Test
    public void lazyWillNotCallCalcYIfXIsZero() {
        APIDesign_AngelikaLanger instance = spy(this);

        instance.lazy(instance::calcX, instance::calcY);

        verify(instance, atLeastOnce()).calcX();
        verify(instance, never()).calcY();
    }

    @Test
    public void otherExamplesOfDefferedExecution() throws Exception {
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

    }

    ////////////////////////
    //
    ////////////////////////


}
