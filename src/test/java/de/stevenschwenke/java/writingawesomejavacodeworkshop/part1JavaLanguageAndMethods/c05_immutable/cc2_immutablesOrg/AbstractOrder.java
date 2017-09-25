package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc2_immutablesOrg;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class AbstractOrder
{
    public abstract List<OrderItemsAsInterface> getOrderItems();

    /**
     * This method will be overriden by the implementation of this class. The result of this method is calculated only
     * once when the class is called for the very first time. The result will be stored in the class.
     *
     * @return price for whole order
     */
    @Value.Lazy
    public long getAllRoundPrice() {

        // This will only be printed once to console, even if this method is called multiple times.
        System.out.println("Calculating ...");

        long price = 0;
        for (OrderItemsAsInterface bp : getOrderItems()    )    {
            price += bp.getPrice() * bp.getAmount();
        }
        return price;
    }
}
