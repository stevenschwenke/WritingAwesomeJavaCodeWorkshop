package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.immutables.value.Value;

@Value.Immutable
public interface OrderItemsAsInterface {

    public Integer getPrice();

    public Integer getAmount();
}
