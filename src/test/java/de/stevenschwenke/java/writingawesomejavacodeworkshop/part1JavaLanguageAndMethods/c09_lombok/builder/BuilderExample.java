package de.stevenschwenke.java.writingawesomejavacodeworkshop.part1JavaLanguageAndMethods.c09_lombok.builder;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

@Builder
public class BuilderExample {
    @NonNull private String name;
    @NonNull private int age;
    @Singular private Set<String> occupations;
}