package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.nonnull;

import lombok.NonNull;

public class NonNullExampleLombok {
    private String name;

    public NonNullExampleLombok(@NonNull Person person) { // NPE in this line ...
        this.name = person.getName();   // ... not this one (too late).
    }
}
