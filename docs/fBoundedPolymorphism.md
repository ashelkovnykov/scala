# F-Bounded Type Polymorphism

F-bounded type polymorphism is a self-referential type constraint that usually comes up when trying to define a
polymorphic function that, though defined in terms of a supertype, will return a value of the same subtype as it
receives as input.

Consider the case that we have many different types of bank accounts, but all of them derive from a base `Account` class
which defines an API:

```
trait Account[T <: Account[T]] {
  def addFunds(amount: BigDecimal): T
}

class BrokerageAccount(total: BigDecimal) extends Account[BrokerageAccount] {
  def addFunds(amount: BigDecimal) = new BrokerageAccount(total + amount)
}

class SavingsAccount(total: BigDecimal) extends Account[SavingsAccount] {
  def addFunds(amount: BigDecimal) = new SavingsAccount(total + amount)
}
```

Suppose that we want implement a function that will debit an account for a maintenance fee. The following
straightforward function will enforce the type bounds using polymorphism at the call site:

```
object Account {
  val MAINTENANCE_FEE = BigDecimal("10.00")
  
  def debit[T <: Account[T]](account: T): T = {
    account.addFunds(-MAINTENANCE_FEE)
  }
}
```

Now suppose that we want to debit all of a customer's accounts for maintenance fees. Since a customer may have many
different types of accounts, to do so will require bounded existential types:

```
object Account {

  def debitAll(accounts: List[T forSome { type T <: Account[T] }]): List[T forSome { type T <: Account[T] }] {
    accounts.map { account => debit(account) }
  }
}
```

Note that the types of the individual members of the list are existentially bounded, not the type of the list. The above
code works well, but there are three issues: 1) the existential types clutter up the code and are difficult to
understand for novice Scala users; 2) the type bound is not as tight as it would ideally be (the result type of `debit`
just has to be derived from `Account[T]`, but it doesn't have to be identical to the type passed in); 3) F-bounded type
polymorphism fails for higher-kinded types due to cyclic references.
