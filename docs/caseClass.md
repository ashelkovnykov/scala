# `case` Classes

`case` classes differ from other classes in that they get:

1. Immutable
2. Default implementation of `apply`
3. Default implementation of `copy`
4. Prettier default `toString`
5. Default serialization
6. Modified default implementations of `equals` and `hashCode` which compare structural (not reference) equality
7. Pattern matching using a default `unapply` method
8. Inherit from `scala.Product`

## `case` Objects

Only 4 and 5 apply to `case` Objects, since they are almost always singletons.
