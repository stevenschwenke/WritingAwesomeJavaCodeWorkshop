# Immutability
- more and more functional programming in Java
- 2 main concepts: functions (lambdas) and immutable objects
- immutable objects not native in Java, concept that has to be implemented by developer
- immutable object = object that does not change. -> no System.out.println.

## Pros
- immutable code very robust, easy to pass as a parameter because changes don't break things
- code easier to read
- immutables in Java thread-safe (can be shared between different threads)

## Cons
- but: not usable with some libraries, for example JPA (setter required)

## Immutables in Java
A truly immutable Java class has
1. no methods to change state of object AND
1. methods of class must not be overriden (mark class as _final_) AND
1. attributes referencing potential immutable objects have to be private (so changes from outside the object are not possible) AND
1. class has to have exclusive access to all potential immutable components (create defensive copies from attributes in constructor of immutable class and in all getter-methods) AND
1. every attributes declared as _final_

Example code for hand-crafted immutable example see package _"cc1_withPlainJavaAndAFoundation"_.

### Examples for Immutables in Java
- Wrapper-classes like Boolean, Integer, Long, ..., String (even if _hash_-method is not _final_)
- classes of new Time-API: LocalDate, LocalTime, Instant, ...
- However _BigInteger_, _BigDecimal_ are NOT immutables because methods can be overriden 

## immutables.org
- handcrafted code for immutables error-prone and boring to write
- [Immutables.org](http://immutables.github.io/) = Java annotation processor to generate value objects
- [IntelliJ IDEA setup](https://immutables.github.io/apt.html#intellij-idea)
- extended examples see package _"cc2_immutablesOrg"_

## Immutable Collections
- not native in Java
    
### Libraries
 - [PCollections](https://pcollections.org/)
 - [Google Guava](https://github.com/google/guava/wiki/ImmutableCollectionsExplained)
 - [a-foundation](https://github.com/arnohaase/a-foundation)
 - [vavr](http://www.vavr.io/)
 
### Immutable Collections in Java 9
- many articles about immutable collections in Java 9 by the new factory methods for collections
- however, [JEP 269](http://openjdk.java.net/jeps/269): _"It is not a goal to provide unmodifiable collection types."_, focus on API that provides _"static factory methods on the collection interfaces that will create compact, unmodifiable collection instances. The API is deliberately kept minimal."_
- syntax in Java 9:
    ```java
    Set<String> immutableSet = Set.of("darth", "plagueis", "the", "wise");

    Map<String, Integer> immutableMap = Map.of("have", 1, "the", 2, "high", 3, "ground", 4);
    ```
- danger of this implementation: mutable and immutable collections have same interface, so immutableSet from above will have _add_ and _remove_ methods

## Stuff
- However, not always a good idea, see http://programmers.stackexchange.com/questions/221762/why-doesnt-java-8-include-immutable-collections

# Sources
- "In Stein gemeißelt - Mit Ummutables in Java arbeiten", Dr. Martin Fluch, [Kaffee Klatsch 03/17](http://www.bookware.de/kaffeeklatsch/archiv/KaffeeKlatsch-2017-03.pdf)
