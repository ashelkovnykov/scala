# Enumerations vs. `case` Classes

What makes a good enumeration class?

1. It contains a naturally-ordered, closed set of named members
   1. There is a fixed number of members.
   2. Members are naturally-ordered and explicitly indexed
   3. Each member has a unique name within the set of all members
2. All members can be easily iterated through, based on their index
3. A member can be retrieved by its case-sensitive name
4. A member can be retrieved by its index
5. Members can be easily and efficiently serialized
6. Members can be extended to hold singleton data
7. Members can leverage Scala's exhaustive pattern matching

What are the options for enumerations in Scala?

1. Java `enum`
2. Scala `sealed trait` + `case object`
3. Scala `Enumeration`

What are the drawbacks of each?

1. Java `enum` lacks Scala's exhaustive pattern matching and requires making a mixed Java/Scala project.
2. Scala `case object`s are not naturally ordered, cannot automatically be iterated through by some index, and cannot automatically be retrieved by name or index.
3. Scala `Enumeration`s lack exhaustive pattern matching.

What should I use?

* Scala 2: The "Enumeratum" library
* Scala 3: Native enumarations