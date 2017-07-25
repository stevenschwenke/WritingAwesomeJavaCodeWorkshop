package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class AmountOfMoneyExample {

    @Test
    public void immutableClassGeneratedFromInterface() throws Exception {
        AmountOfMoneyWithCurrencyAsInterface amount = ImmutableAmountOfMoneyWithCurrencyAsInterface.builder()
                .amount(300)
                .currency("EUR")
                .build();
        assertEquals(300, amount.getAmount());
        assertEquals("EUR", amount.getCurrency());
    }

    @Test
    public void immutableClassGeneratedFromAbstractClass() throws Exception {
        ImmutableAmountOfMoneyWithCurrency amount = ImmutableAmountOfMoneyWithCurrency.builder()
                .amount(300)
                .currency("EUR")
                .build();
        assertEquals(300, amount.getAmount());
        assertEquals("EUR", amount.getCurrency());
    }

    @Test(expected = IllegalStateException.class)
    public void invalidObjectCreationWillThrowIllegalStateException() throws Exception {
        ImmutableAmountOfMoneyWithCurrency gb = ImmutableAmountOfMoneyWithCurrency.builder()
                .amount(100)
                .build();
    }

    @Test
    public void invalidObjectCreationWillNotThrowExceptionWhenOptionalsAreUsed() throws Exception {
        ImmutableAmountOfMoneyWithOptionalCurrencyAsInterface gb = ImmutableAmountOfMoneyWithOptionalCurrencyAsInterface.builder()
                .amount(100)
                .build();
        assertFalse(gb.getCurrency().isPresent());
    }

    @Test
    public void lazyInitialization() throws Exception {
        ImmutableOrderItemsAsInterface orderItem = ImmutableOrderItemsAsInterface.builder().amount(2).price(12).build();
        AbstractOrder order = ImmutableOrder.builder().addOrderItems(orderItem).build();

        order.getAllRoundPrice();
        order.getAllRoundPrice();
        order.getAllRoundPrice();
        order.getAllRoundPrice();
    }

    @Test
    public void immutableCollectionsAreNotNativeInJava() throws Exception {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        List<Integer> unmodifiableList = Collections.unmodifiableList(list1);

//        unmodifiableList.add(3); // throws UnsupportedOperationException because unmodifiableList cannot be changed

        list1.add(3); // Works, but alters seemingly unmodifiable list2
    }
}
