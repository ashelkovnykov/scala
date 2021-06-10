package com.ashelkov.scala.tips.collections

object SetCheatSheet {

  def example(): Unit = {

    val x: Int = 36

    // Before: Set[Int[()
    //
    // Some immutable collections provide singleton "empty" implementations. Not all factory methods check the length
    // of created collections, so by explicitly using the singleton implementations, we can reduce either heap space
    // (by not making a new empty collection) or runtime (by not checking the length of a newly created collection).
    //
    val set = Set.empty[Int]

    // Before: !set.isEmpty
    set.nonEmpty

    // Before :
    // set.length > 0
    // set.length != 0
    // set.length == 0
    //
    // Computing length may be a O(n) computation for some Set subclasses, whereas emptiness is always a O(1)
    // computation.
    //
    set.nonEmpty
    set.nonEmpty
    set.isEmpty

    // Before:
    // set.length > n
    //
    // Computing length may be a O(length) computation for some Set subclasses, whereas using 'lengthCompare' reduces it
    // to a O(min(length, n)) computation.
    //
    set.sizeCompare(x) > 0

    // Before: set.filter(!(x => x == 0))
    set.filterNot(_ == 0)

    // Before:
    // set.filter(x => (x == 0)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    set.count(_ == 0)

    // Before: set.reduce(_ + _) OR set.fold(42)(_ + _)
    set.sum
    set.sum + 42

    // Before: set.reduce(_ * _) OR set.fold(42)(_ + _)
    set.product
    set.product * 42

    // Before:
    // set.filter(_ > 10).filter(> _ 20)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    set.filter(x => ((x > 10) && (x < 20)))

    // Before:
    // set.map(foo).map(gaz)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    set.map(foo.andThen(gaz))

    // Before:
    // val setA = set.filter(gaz)
    // val setB = set.filterNot(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    var (setA, setB) = set.partition(gaz)

    // Before:
    // val setA = set.takeWhile(gaz)
    // val setB = set.dropWhile(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    val (setC, setD) = set.span(gaz)

    // Before: set.map(bar)
    set.foreach(bar)

    // Before:
    // setA ++ setB
    setA ++= setB

    // Before:
    // setA.sameElements(setB)
    //
    // 'sameElements' can return non-deterministic results, as it will attempt to respect the order of elements, even
    // for unordered sets.
    //
    setA == setB

    // Before:
    // Seq[Int](1, 2, 3).filter(set(_))
    //
    // 'Set[T]' inherits from 'Function1[T, Boolean], so it can be called directly.
    //
    Seq[Int](1, 2, 3).filter(set)

    // Before: setA.filter(setB)
    setA.intersect(setB)

    // Before: setA.filterNot(setB)
    setA.diff(setB)
  }

  private def foo(i: Int): Int = i

  private def bar(i: Int) = {}

  private def gaz(i: Int): Boolean = true
}
