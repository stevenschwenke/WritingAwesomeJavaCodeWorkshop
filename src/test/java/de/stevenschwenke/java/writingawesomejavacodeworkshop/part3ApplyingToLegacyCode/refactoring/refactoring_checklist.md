These checklists is extracted from "Refactoring" by Martin Fowler. It can be used together with code that has to be refactored, for example the ugly trivia game.

# checklist 1: identifying bad smells
[ ] Duplicated Code
[ ] Long method
[ ] Large Class
[ ] Long parameter list
[ ] Divergent Change
[ ] Shotgun Surgery
[ ] Feature Envy
[ ] Data Clumps
[ ] Primitive Obsession
[ ] Switch Statements
[ ] Parallel Inheritance Hierarchies
[ ] Lazy Class
[ ] Speculative Generality
[ ] Temporary Field
[ ] Message Chains
[ ] Middle Man
[ ] Inappropriate Intimacy
[ ] Alternative Classes with Different Interfaces
[ ] Refused Bequest
[ ] Comments

# checklist 2: doing the refactorings
## Simple refactorings
[ ] extract method
[ ] Renaming
[ ] Move Method
[ ] Replace Temporary Variables with Query
[ ] extract method 
[ ] replace temp with query
[ ] extract and move methods to create consistent classes
[ ] Introduce inheritance

## Composing methods
[ ] Extract method
[ ] Inline method
[ ] Inline temp 
[ ] Replace temp with query
[ ] Introduce explaining variable
[ ] Split temporary variable
[ ] Remove assignments to parameters
[ ] Replace method with method object

## Moving features between objects
[ ] Move method
[ ] Move field
[ ] Extract class
[ ] Inline class
[ ] Hide delegate
[ ] Remove middle man
[ ] Introduce foreign method
[ ] Introduce local extension

## Organizing Data
[ ] Self encapsulate field
[ ] Replace data value with object
[ ] Change value object to reference object
[ ] Change reference object to value object
[ ] Replace array with object
[ ] Duplicate observed data
[ ] Change unidirectional association to bidirectional
[ ] Change bidirectional association with unidirectional
[ ] Replace magic number with symbolic constant
[ ] Encapsulate field
[ ] Encapsulate collection
[ ] Replace record with data class
[ ] Replace type code with class
[ ] Replace type code with subclasses
[ ] Replace type code with state / strategy
[ ] Replace subclass with fields

## Simplifying conditional expressions
[ ] Decompose Conditional
[ ] Consolidate Conditional Expression
[ ] Consolidate duplicate conditional fragments
[ ] Remove control flag
[ ] Replace nested conditional with guard clauses
[ ] Replace conditional with polymorphism
[ ] Introduce null object
[ ] Introduce assertion