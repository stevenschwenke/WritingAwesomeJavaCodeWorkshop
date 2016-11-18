package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable;

import com.ajjpj.afoundation.collection.immutable.AList;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable implementation of an account that has a number of balances.
 */
class Account {

    private final String name;

    /**
     * There is no c05_immutable list in Java. We use Arno Haases library.
     */
    private final AList<AccountBalance> transactions;

    Account(String name, Iterable<AccountBalance> transactions) {
        this(name, AList.create(transactions));
    }

    // Because we don't want to expose the AList implementation, we have the constructor with Iterable above.

    private Account(String name,
                    AList<AccountBalance> transactions) {
        this.name = name;
        this.transactions = transactions;
    }

    String getName() {
        return name;
    }

    AList<AccountBalance> getTransactions() {
        return transactions;
    }

    Account rename(String name) {
        return new Account(name, transactions);
    }

    Account addTransaction(AccountBalance transaction) {
        final List<AccountBalance> newBalanceList =
            new ArrayList<>(this.transactions.asJavaUtilList());
        newBalanceList.add(transaction);
        return new Account(name, AList.create(newBalanceList));
    }
}
