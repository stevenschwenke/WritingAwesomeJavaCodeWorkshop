# Lombok
- [projectlombok.org](https://projectlombok.org)
- "never write another getter or equals method again" + generation of other code
- also able to generate immutable classes like immutables.org 

## Installation
- add Maven dependency
- install Lombok plugin
- with plugin: in IntelliJ IDEA immediate generation of methods without running Maven or manual building

## Features
See packages. Here are only features that don't have example code.

### val
- basically dynamic typing
- ... not a fan of that ...

### cleanup
- call close()- methods automatically (InputStream, OutputStream etc.)

### toString
- create toString()-method
- possible to exclude attributes

### equals / hashCode
- create equals() and hashCode()-methods
- possible to exclude attributes

### SneakyThrows
- used to hide "throws"- part of method signature
- ... not a fan of that ...

### Synchronized
- "safer variant of the synchronized method modifier"

### Log
- instantiates a log-instance without having to choose from the various options

## Other solutions and comparison
- [Joda-Beans](http://www.joda.org/joda-beans/)
- [AutoValue](https://github.com/google/auto/tree/master/value)
- [VALJOGen](http://valjogen.41concepts.com)


- [Compare bean generation tools repository](https://github.com/jodastephen/compare-beangen)
- ["Lombok, AutoValue, and Immutables"](http://marxsoftware.blogspot.de/2016/06/lombok-autovalue-immutables.html) by [Dustin Marx](https://www.blogger.com/profile/10790950138196529391)

## Issue: Internal API calls / no "normal" annotation processor
- ["Code generating beans - mutable and immutable](http://blog.joda.org/2016/09/code-generating-beans.html) by [Stephen Colebourne](https://www.blogger.com/profile/01454237967846880639): _Immutables_ uses annotation processors to generate code during compilation. Developer has to write abstract classes or interfaces from which classes are generated. Lombok "hacks into the internal APIs of Eclipse and the Java compiler" and allows for code generation in the same class. => Only with Eclipse because of Eclipse compiler.
- ["What do you think of Lombok? Reddit](https://www.reddit.com/r/java/comments/6ilt97/what_do_you_think_of_project_lombok/): Lombok seems to have evolved so it doesn't use internal API calls anymore. However, not sure. 
- ["Is it safe to use Lombok?"](https://stackoverflow.com/questions/3852091/is-it-safe-to-use-project-lombok/3853538), comment further down: "Lombok breaks the rules of annotation processing, in that it doesn't generate new source files. This means it cant be used with another annotation processors if they expect the getters/setters or whatever else to exist."

 
- __Baseline:__ If you use IntelliJ IDEA and you have no other annotation processors running, everything is fine.

## Issue: Lombok doesn't know immutable collections
- see ValueWithCollectionTest.java
- __solution__: use [Configuration System](https://projectlombok.org/features/configuration) to disable @Value annotation and use [immutables.org](immutables.org/) for that specific task

## Other issues
- ["Is it safe to use Lombok?"](https://stackoverflow.com/questions/3852091/is-it-safe-to-use-project-lombok/3853538), see second comment (not "right" answer)
    - refactoring support: simply renaming an attribute causes the "refactor getter and setter" dialogue to open, however IntelliJ IDEA tries to refactor much more than just the getter and setter. => Problem: much more hassle when refactoring, even with installed Plugin. 
    - "If you have a class, say MyCompoundObject.java that has 2 members, both annotated with @Delegate, say myWidgets and myGadgets, when you call myCompoundObject.getThingies() from another class, it's impossible to know if it's delegating to the Widget or Gadget because you can no longer jump to source within the IDE." => Problem.
    - navigating through getter/setter (Ctrl + B) will bring you to the attribute => OK
    - JavaDoc above attributes is copied to generated getter/setter => OK
    - impossible to set breakpoints within getter/setter because they don't exist
    - reference search for attribute will show occurrences of getter and setter => OK
    - more assurance: Oliver Gierke added Lombok to Spring Data Rest + configuration system 
- There's an automated way to [delombok](https://projectlombok.org/features/delombok)
