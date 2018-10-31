package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.lazyGetter;

import org.junit.jupiter.api.Test;

public class GetterLazyExampleTest {

    @Test
    public void getterLazyExampleTest() throws Exception {
        GetterLazyExample getterLazyExample = new GetterLazyExample();

        // "expensive() called!" only once in terminal, but method called three times:
        getterLazyExample.getCached();
        getterLazyExample.getCached();
        getterLazyExample.getCached();
    }
}
