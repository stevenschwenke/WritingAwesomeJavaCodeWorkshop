# Lombok
- [projectlombok.org](https://projectlombok.org)
- "never write another getter or equals method again"

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

## Issue: Internal API calls
- ["Code generating beans - mutable and immutable](http://blog.joda.org/2016/09/code-generating-beans.html) by [Stephen Colebourne](https://www.blogger.com/profile/01454237967846880639): _Immutables_ uses annotation processors to generate code during compilation. Developer has to write abstract classes or interfaces from which classes are generated. Lombok "hacks into the internal APIs of Eclipse and the Java compiler" and allows for code generation in the same class.
- ["What do you think of Lombok? Reddit](https://www.reddit.com/r/java/comments/6ilt97/what_do_you_think_of_project_lombok/): Lombok seems to have evolved so it doesn't use internal API calls anymore. However, not sure. 

## Other issues
- ["Is it safe to use Lombok?"](https://stackoverflow.com/questions/3852091/is-it-safe-to-use-project-lombok/3853538), see second comment (not "right" answer)

## Tasks
- read article in "other issues" and find out about those issues
- decide for main project