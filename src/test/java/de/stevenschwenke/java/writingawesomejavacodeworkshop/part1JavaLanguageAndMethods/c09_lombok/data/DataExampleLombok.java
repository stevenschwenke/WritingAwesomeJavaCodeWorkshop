package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
public class DataExampleLombok {
    private final String name;
    @Setter(AccessLevel.PACKAGE)
    private int age;
    private double score;
    private String[] tags;

    @ToString(includeFieldNames = true)
    @Data(staticConstructor = "of")
    public static class Exercise<T> {
        private final String name;
        private final T value;
    }
}
