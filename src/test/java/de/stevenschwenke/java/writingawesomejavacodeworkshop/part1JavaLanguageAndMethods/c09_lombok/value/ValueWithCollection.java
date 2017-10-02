package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.value;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;

import java.util.List;

@Value
public class ValueWithCollection {
    String name;

    @Wither(AccessLevel.PACKAGE)
    @NonFinal
    int age;

    double score;

    protected String[] tags;

    private List<String> blubbers;

    @ToString(includeFieldNames = true)
    @Value(staticConstructor = "of")
    public static class Exercise<T> {
        String name;
        T value;
    }
}
