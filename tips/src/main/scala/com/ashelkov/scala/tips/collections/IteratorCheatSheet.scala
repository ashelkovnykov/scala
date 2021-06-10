package com.ashelkov.scala.tips.collections

object IteratorCheatSheet {

  def example(): Unit = {

    val x: Int = 36

    // Before: Iterator[Int[()
    //
    // Some immutable collections provide singleton "empty" implementations. Not all factory methods check the length
    // of created collections, so by explicitly using the singleton implementations, we can reduce either heap space
    // (by not making a new empty collection) or runtime (by not checking the length of a newly created collection).
    //
    val iter = Iterator.empty[Int]

    // Before: !iter.isEmpty
    iter.nonEmpty

    // Before: iter1 == iter2
    iter.sameElements(iter)

    // Before: iter.filter(!(x => x == 0))
    iter.filterNot(x => (x == 0))

    // Before:
    // iter.filter(x => (x == 0)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    iter.count(x => (x == 0))

    // Before: iter.reduce(_ + _) OR iter.fold(42)(_ + _)
    iter.sum
    iter.sum + 42

    // Before: iter.reduce(_ * _) OR iter.fold(42)(_ + _)
    iter.product
    iter.product * 42

    // Before:
    // iter.filter(_ > 10).filter(> _ 20)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    iter.filter(x => (x > 10) && (x < 20))

    // Before:
    // iter.map(foo).map(gaz)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    iter.map(foo.andThen(gaz))

    // Before:
    // val iterA = seq.take(x)
    // val iterB = seq.drop(x)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    var (iterA, iterB) = iter.splitAt(x)

    // Before:
    // val mapA = iter.filter(gaz)
    // val mapB = iter.filterNot(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    val (iterC, iterD) = iter.partition(gaz)

    // Before:
    // val iterA = iter.takeWhile(gaz)
    // val iterB = iter.dropWhile(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    val (iterE, iterF) = iter.span(gaz)

    // Before: iter.map(bar)
    iter.foreach(bar)

    // Before: iterA ++ iterB
    iterA ++= iterB
  }

  private def foo(i: Int): Int = i

  private def bar(i: Int): Unit = {}

  private def gaz(i: Int): Boolean = true
}
