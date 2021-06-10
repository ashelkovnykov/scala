# Operators

```
<-         // Used on for-comprehensions, to separate pattern from generator
=>         // Used for function types, function literals and import renaming
( )        // Delimit expressions and parameters
[ ]        // Delimit type parameters
{ }        // Delimit blocks
.          // Method call and path separator
// /* */   // Comments
#          // Used in type notations
:          // Type ascription or context bounds
<: >: <%   // Upper, lower and view bounds
<? <!      // Start token for various XML elements
" """      // Strings
'          // Indicate symbols and characters
@          // Annotations and variable binding on pattern matching
`          // Denote constant or enable arbitrary identifiers
,          // Parameter separator
;          // Statement separator
_*         // vararg expansion
```

## Underscore (`_`)

```
import scala._                  // Wild card -- all of Scala is imported
import scala.{ Predef => _, _ } // Exception, everything except Predef
def f[M[_]]                     // Higher kinded type parameter
def f(m: M[_])                  // Existential type
_ + _                           // Anonymous function placeholder parameter
m _                             // Eta expansion of method into method value
m(_)                            // Partial function application
_ => 5                          // Discarded parameter
case _ =>                       // Wild card pattern -- matches anything
f(xs: _*)                       // Sequence xs is passed as multiple parameters to f(ys: T*)
case Seq(xs @ _*)               // Identifier xs is bound to the whole matched sequence
```
