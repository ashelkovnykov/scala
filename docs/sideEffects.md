# Side Effects

A "side effect" is any action other than returning a value that is observable outside of a function's or expression's
scope. For example:

* I/O operations
* Variable modification (accessible outside of scope)
* Object state modification (accessible outside of scope)
* Throwing an uncaught exception

The presence of side effects means that certain operations cannot be re-ordered or optimized out. For this reason, side
effects should be avoided whenever possible. If avoiding side effects is not an option, the side effects should be
separated from pure code.
