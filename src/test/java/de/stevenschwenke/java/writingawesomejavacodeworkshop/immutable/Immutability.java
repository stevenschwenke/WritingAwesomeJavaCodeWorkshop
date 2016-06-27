package de.stevenschwenke.java.writingawesomejavacodeworkshop.immutable;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * This example demonstrates immutability in Java.
 *
 * <ul> <li>more and more functional programming in Java</li> <li>2 main concepts: functions
 * (lambdas) and immutable objects</li> <li>immutable objects not native in Java, concept that has
 * to be implemented by developer</li> <li>immutable object = object that does not change. -> no
 * System.out.println.</li> <li>pro: immutable code very robust, easy to pass as a parameter because
 * changes don't break things</li> <li>but: not usable with some libraries, for example JPA (setter
 * required)</li> <li>However, not always a good idea, see http://programmers.stackexchange.com/questions/221762/why-doesnt-java-8-include-immutable-collections</li>
 * </ul>
 */
public class Immutability {

    @Test
    public void someImmutability() {
        AccountBalance b1 = new AccountBalance(42.4);
        assertEquals(42.4, b1.getBalance());

        b1 = b1.add(2);
        assertEquals(44.4, b1.getBalance());

        Account account = new Account("Stevens Account", new ArrayList<>());
        assertEquals(0, account.getTransactions().size());

        account = account.addTransaction(b1);
        assertEquals(1, account.getTransactions().size());
        assertEquals(b1, account.getTransactions().head());

        account = account.rename("Former Stevens Account");
        assertEquals("Former Stevens Account", account.getName());
        assertEquals(1, account.getTransactions().size());
        assertEquals(b1, account.getTransactions().head());
    }
}
