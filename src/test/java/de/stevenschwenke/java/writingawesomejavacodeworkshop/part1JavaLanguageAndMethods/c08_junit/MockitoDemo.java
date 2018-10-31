package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c08_junit;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * This class demonstrates the library Mockito.
 */
public class MockitoDemo {

    @Test
    public void mockitoUsageViaMockitoWebsite() {
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

        // you can mock concrete classes, not only interfaces
        mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        // Important difference in "grammar" of mocking:
        MyClass spy = spy(new MyClass());
        System.out.println("This will actually call the doSomeStuff method, see console:");
        when(spy.doSomeStuff()).thenReturn(1);
        System.out.println("This will not call the method - there's nothing here:");
        doReturn(1).when(spy).doSomeStuff();
    }

    class MyClass {
        int doSomeStuff() {
            System.out.println("Method doSomeStuff called!");
            return 0;
        }
    }
}
