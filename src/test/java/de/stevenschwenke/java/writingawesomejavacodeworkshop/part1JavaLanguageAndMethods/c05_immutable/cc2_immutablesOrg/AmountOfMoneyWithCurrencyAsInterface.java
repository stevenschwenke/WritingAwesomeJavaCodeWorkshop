package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.immutables.value.Value;

@Value.Immutable
public interface AmountOfMoneyWithCurrencyAsInterface
{
    long getAmount();
    String getCurrency();
}
