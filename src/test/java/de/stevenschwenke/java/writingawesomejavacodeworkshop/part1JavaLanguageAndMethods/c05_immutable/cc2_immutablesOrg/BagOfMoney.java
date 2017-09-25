package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
public interface BagOfMoney {

    Set<ImmutableAmountOfMoneyWithCurrency> getMoney();
}
