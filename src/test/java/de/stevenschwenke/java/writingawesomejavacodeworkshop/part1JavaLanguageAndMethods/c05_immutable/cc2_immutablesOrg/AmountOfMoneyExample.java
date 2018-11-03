package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AmountOfMoneyExample {

    @Test
    void immutableClassGeneratedFromInterface() {
        AmountOfMoneyWithCurrencyAsInterface amount = ImmutableAmountOfMoneyWithCurrencyAsInterface.builder()
                .amount(300)
                .currency("EUR")
                .build();
        assertEquals(300, amount.getAmount());
        assertEquals("EUR", amount.getCurrency());
    }

    @Test
    void immutableClassGeneratedFromAbstractClass() {
        ImmutableAmountOfMoneyWithCurrency amount = ImmutableAmountOfMoneyWithCurrency.builder()
                .amount(300)
                .currency("EUR")
                .build();
        assertEquals(300, amount.getAmount());
        assertEquals("EUR", amount.getCurrency());
    }

    @Test
    void invalidObjectCreationWillThrowIllegalStateException() {

        assertThrows(IllegalStateException.class, () -> {
            ImmutableAmountOfMoneyWithCurrency gb = ImmutableAmountOfMoneyWithCurrency.builder()
                    .amount(100)
                    .build();
        });
    }

    @Test
    void invalidObjectCreationWillNotThrowExceptionWhenOptionalsAreUsed() {
        ImmutableAmountOfMoneyWithOptionalCurrencyAsInterface gb = ImmutableAmountOfMoneyWithOptionalCurrencyAsInterface.builder()
                .amount(100)
                .build();
        assertFalse(gb.getCurrency().isPresent());
    }

    @Test
    void lazyAttributes() {
        ImmutableOrderItemsAsInterface orderItem = ImmutableOrderItemsAsInterface.builder().amount(2).price(12).build();
        AbstractOrder order = ImmutableOrder.builder().addOrderItems(orderItem).build();

        order.getAllRoundPrice();
        order.getAllRoundPrice();
        order.getAllRoundPrice();
        order.getAllRoundPrice();
    }

    @Test
    void from() {
        AmountOfMoneyWithCurrencyAsInterface templateAmount = ImmutableAmountOfMoneyWithCurrencyAsInterface.builder()
                .amount(300)
                .currency("EUR")
                .build();
        ImmutableAmountOfMoneyWithCurrencyAsInterface amount2 = ImmutableAmountOfMoneyWithCurrencyAsInterface.builder().from(templateAmount).amount(500).build();

        assertEquals(500, amount2.getAmount());
        assertEquals("EUR", amount2.getCurrency()); // copied via builder.from(...)
    }

    @Test
    void immutableCollectionsAreNotNativeInJava() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        List<Integer> unmodifiableList = Collections.unmodifiableList(list1);

//        unmodifiableList.add(3); // throws UnsupportedOperationException because unmodifiableList cannot be changed

        list1.add(3); // Works, but alters seemingly unmodifiable list2
    }

    @Test
    void supportedCollectionTypes() {

        ImmutableAmountOfMoneyWithCurrency money1 = ImmutableAmountOfMoneyWithCurrency.builder().amount(100).currency("EUR").build();
        ImmutableAmountOfMoneyWithCurrency money2 = ImmutableAmountOfMoneyWithCurrency.builder().amount(100).currency("EUR").build();

        ImmutableBagOfMoney bagOfMoney = ImmutableBagOfMoney.builder().addMoney(money1, money2).build();

        /* supported are:
            T[]
            java.util.List<T>
            java.util.Set<T>
            java.util.Map<K, V>
            com.google.common.collect.Multiset<T>
            com.google.common.collect.Multimap<K, V> (ListMultimap, SetMultimap)
            com.google.common.collect.BiMap<K, V>
            com.google.common.collect.Immutable* variants for collections above

            Also: For every Type nice methods are generated like addMoney(T...) above.

        "Array attributes are cloned for safety (due to the mutable nature of Java arrays). Collection attributes are backed by Guava immutable collections if Guava is available on the classpath. Otherwise, they are safely copied and wrapped in unmodifiable collection classes from the standard JDK."

         */
    }
}
