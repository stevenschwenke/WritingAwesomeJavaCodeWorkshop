package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.immutables.value.Value;

@Value.Immutable
public abstract class AbstractAmountOfMoneyWithCurrency {
    public abstract long getAmount();
    public abstract String getCurrency();
    // Class-specific methods
}