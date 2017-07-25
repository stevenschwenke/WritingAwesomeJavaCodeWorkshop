package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public interface AmountOfMoneyWithOptionalCurrencyAsInterface
{
    long getAmount();
    Optional<String> getCurrency();
}
