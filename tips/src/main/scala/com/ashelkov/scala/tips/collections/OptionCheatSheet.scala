package com.ashelkov.scala.tips.collections

object OptionCheatSheet {

  val x: Int = 42

  def example(option: Option[Int]) {

    // Before: val a: Option[Int] = None
    //
    // Some immutable collections provide singleton "empty" implementations. Not all factory methods check the length
    // of created collections, so by explicitly using the singleton implementations, we can reduce either heap space
    // (by not making a new empty collection) or runtime (by not checking the length of a newly created collection).
    //
    val option = Option.empty[Int]

    // Before: if (x != null) Some(x) else None
    Option(x)

    // Before:
    // Option(42)
    //
    // Avoids a redundant null check
    //
    Some(42)

    // Before: !option.isEmpty
    option.nonEmpty

    // Before: option == None
    option.isEmpty

    // Before: option != None
    option.isDefined

    // Before option == Some(42)
    option.contains(42)

    // Before:
    // option match {
    //  case None => None
    //  case Some(x) => Some(foo(x))
    // }
    option.flatMap(x => Some(foo(x)))

    // Before:
    // option match {
    //   case None => None
    //   case Some(x) => Some(foo(x))
    // }
    option.map(foo)

    // Before:
    // option match {
    //   case None => {}
    //   case Some(x) => bar(x)
    // }
    option.foreach(bar)

    // Before:
    // option match {
    //   case None => false
    //   case Some(x) => true
    // }
    option.isDefined

    // Before:
    // option match {
    //   case None => true
    //   case Some(x) => false
    // }
    option.isEmpty

    // Before:
    // option match {
    //   case None => true
    //   case Some(x) => gaz(x)
    // }
    option.forall(gaz)

    // Before:
    // option match {
    //   case None => false
    //   case Some(x) => gaz(x)
    // }
    option.exists(gaz)

    // Before:
    // option match {
    //   case None => Some(42)
    //   case Some(x) => Some(x)
    // }
    option.orElse(Some(42))

    // Before:
    // option match {
    //   case None => 42
    //   case Some(x) => x
    // }
    option.getOrElse(42)

    // Before: option.map(foo).getOrElse(x)
    option.fold(x)(foo)

    // Before: option.getOrElse(null)
    option.orNull

    // Before:
    // option match {
    //   case None => Nil
    //   case Some(x) => x :: Nil
    // }
    option.toList
  }

  private def foo(i: Int): Int = i

  private def bar(i: Int): Unit = {}

  private def gaz(i: Int): Boolean = true
}
