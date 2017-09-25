package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.immutables.value.Value;

@Value.Immutable
public interface Car {

    @Value.Parameter(order = 1)
    public Integer getAmountOfWheels();

    @Value.Parameter(order = 2)
    public String getColor();
}
