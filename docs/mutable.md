# Immutable `var` vs. mutable `val`

When an immutable `val` would be grossly inefficient, one must choose between an immutable `var` or a mutable `val`. 
Which one should be preferred and under what circumstances?

A good guiding principle is, "Can I isolate all of the mutation to a single code block or function?" If so, then a
immutable `var` is preferable. If not, then a mutable `val` is preferred. This is because shared mutable objects are
protected in that their mutability is enforced through their API.

Note that using `var` is a clear indication that mutation is possible, as opposed to needing to infer mutation from a
class type.
