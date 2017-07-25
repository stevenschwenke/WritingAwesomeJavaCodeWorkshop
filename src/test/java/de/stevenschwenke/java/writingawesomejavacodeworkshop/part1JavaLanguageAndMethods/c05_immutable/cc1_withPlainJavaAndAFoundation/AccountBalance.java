package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc1_withPlainJavaAndAFoundation;

/**
 * Immutable implementation of an account balance.
 */
final class AccountBalance {

    /**
     * final so that this attribute cannot be changed
     */
    private final double balance;

    /**
     * Only way to set the balance: via constructor
     */
    AccountBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Normally, new balance would be set by a setter-method. Because this object is immutable, this
     * method creates a new object.
     */
    AccountBalance add(final long transferal) {
        return new AccountBalance(balance + transferal);
    }

    double getBalance() {
        return balance;
    }
}
