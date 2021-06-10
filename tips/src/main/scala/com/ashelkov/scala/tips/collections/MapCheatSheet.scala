package com.ashelkov.scala.tips.collections

object MapCheatSheet {

  def example(): Unit = {

    val x: Int = 36

    // Before: Map[Int, Int]()
    //
    // Some immutable collections provide singleton "empty" implementations. Not all factory methods check the length
    // of created collections, so by explicitly using the singleton implementations, we can reduce either heap space
    // (by not making a new empty collection) or runtime (by not checking the length of a newly created collection).
    //
    val map = Map.empty[Int, Int]

    // Before: !map.isEmpty
    map.nonEmpty

    // Before :
    // map.length > 0
    // map.length != 0
    // map.length == 0
    //
    // Computing length may be a O(n) computation for some Map subclasses, whereas emptiness is always a O(1)
    // computation.
    //
    map.nonEmpty
    map.nonEmpty
    map.isEmpty

    // Before:
    // map.length > n
    //
    // Computing length may be a O(length) computation for some Map subclasses, whereas using 'lengthCompare' reduces it
    // to a O(min(length, n)) computation.
    //
    map.sizeCompare(x) > 0

    // Before: map.filter(!(x => x == 0))
    map.filterNot(_._2 == 0)

    // Before:
    // map.filter(x => (x == 0)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    map.count(_._2 == 0)

    // Before:
    // map.filter(_ > 10).filter(> _ 20)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    map.filter { case (_, x) =>
      (x > 10) && (x < 20)
    }

    // Before:
    // val mapA = map.filter(gaz)
    // val mapB = map.filterNot(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    var (mapA, mapB) = map.partition(zag)

    // Before:
    // val mapA = map.takeWhile(gaz)
    // val mapB = map.dropWhile(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    val (mapC, mapD) = map.span(zag)

    // Before: map.map(bar)
    map.foreach(bar)

    // Before:
    // mapA ++ mapB
    mapA ++= mapB

    // Before:
    // mapA.sameElements(mapB)
    //
    // 'sameElements' can return non-deterministic results, as it will attempt to respect the order of elements, even
    // for unordered maps.
    //
    mapA == mapB

    // Before: map.map(_._1)
    map.keys

    // Before: map.map(_._2)
    map.values

    // Before:
    // map.filterKeys(gaz)
    // map.mapValues(foo)
    //
    // The 'filterKeys' and 'mapValues' methods don't actually generate a new 'Map' - they wrap an existing 'Map'. This
    // 'View' is also lazy-evaluated, meaning that the order of side-effects is not deterministic.
    //
    map.view.filterKeys(gaz)
    map.view.mapValues(foo)

    // Before:
    // map.view.mapValues(foo).mapValues(gaz)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    map.view.mapValues(foo.andThen(gaz))
  }

  private def foo(x: Int): Int = x

  private def bar(x: (Int, Int)): Unit = {}

  private def gaz(x: Int): Boolean = true

  private def zag(x: (Int, Int)): Boolean = true

}
