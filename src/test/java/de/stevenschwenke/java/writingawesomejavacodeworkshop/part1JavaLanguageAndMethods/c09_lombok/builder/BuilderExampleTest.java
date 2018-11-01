package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.builder;

import org.junit.jupiter.api.Test;

class BuilderExampleTest {

    @Test
    void name() {
        BuilderExample builder1 = BuilderExample.builder().name("Name").age(12).build();

//        BuilderExample builder2 = BuilderExample.builder().age(12).build(); //NPE

        // @Singular builds methods to add objects to collections:
        BuilderExample builder2 = BuilderExample.builder().name("name").age(12).occupation("occupation1").occupation("occupation2").build();
    }
}
