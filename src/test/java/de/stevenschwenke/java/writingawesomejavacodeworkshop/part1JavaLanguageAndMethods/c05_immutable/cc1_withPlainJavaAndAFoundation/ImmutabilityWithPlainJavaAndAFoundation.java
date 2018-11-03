package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c05_immutable.cc1_withPlainJavaAndAFoundation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImmutabilityWithPlainJavaAndAFoundation {

    @Test
    void someImmutability() {
        AccountBalance b1 = new AccountBalance(42.4);
        assertEquals(42.4, b1.getBalance());

        b1 = b1.add(2);
        assertEquals(44.4, b1.getBalance());

        Account account = new Account("Stevens Account", new ArrayList<>());
        assertEquals(0, account.getTransactions().size());

        account = account.addTransaction(b1);
        assertEquals(1, account.getTransactions().size());
        assertEquals(b1, account.getTransactions().head());

        // interesting: Normally, there would be a method setName(). We don't want setters here, so name it "rename" :)
        account = account.rename("Former Stevens Account");
        assertEquals("Former Stevens Account", account.getName());
        assertEquals(1, account.getTransactions().size());
        assertEquals(b1, account.getTransactions().head());
    }
}
