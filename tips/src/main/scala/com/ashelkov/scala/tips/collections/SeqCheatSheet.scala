package com.ashelkov.scala.tips.collections

object SeqCheatSheet {

  def example(): Unit = {

    val x: Int = 36

    // Before: Seq[Int]()
    //
    // Some immutable collections provide singleton "empty" implementations. Not all factory methods check the length
    // of created collections, so by explicitly using the singleton implementations, we can reduce either heap space
    // (by not making a new empty collection) or runtime (by not checking the length of a newly created collection).
    //
    var seq = Seq.empty[Int]

    // Before: seq.size
    //
    // Calls to 'size' are implemented using implicit conversions, so intermediate wrapping objects are created for each
    // call.
    //
    seq.length

    // Before: !seq.isEmpty
    seq.nonEmpty

    // Before :
    // seq.length > 0
    // seq.length != 0
    // seq.length == 0
    //
    // Computing length may be a O(n) computation for some Seq subclasses, whereas emptiness is always a O(1)
    // computation.
    //
    seq.nonEmpty
    seq.nonEmpty
    seq.isEmpty

    // Before:
    // seq.length > n
    //
    // Computing length may be a O(length) computation for some Seq subclasses, whereas using 'lengthCompare' reduces it
    // to a O(min(length, n)) computation.
    //
    seq.lengthCompare(x) > 0

    // Before:
    // seq(0)
    //
    // For some collection classes, property access is slightly faster than method calls.
    //
    seq.head

    // Before:
    // seq(seq.length - 1)
    //
    // Some collections can retreive the last element in O(1) time. Also avoids redundant length computations.
    //
    seq.last

    // Before: if (i < seq.length) Some(seq(i)) else Option.empty
    seq.lift(x)

    // Before: seq.lift(0)
    seq.headOption

    // Before: if (seq.nonEmpty) Some(seq.last) else Option.empty
    seq.lastOption

    // Before: Range(0, seq.length)
    seq.indices

    // Before:
    // seq.zip(seq.indices)
    //
    // This alternative avoids a length computation and works with infinite streams.
    //
    seq.zipWithIndex

    // Before: seq.filter(!(x => x == 0))
    seq.filterNot(x => (x == 0))

    // Before:
    // seq.filter(x => (x == 0)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    seq.count(x => (x == 0))

    // Before:
    // Seq[Dummy](d1, d2, d3).sortWith(_.x < _.x)
    //
    // It's better to define an Ordering object and import it (implicitly or explicitly).
    //
    Seq[Dummy](d1, d2, d3).sortBy(_.x)

    // Before: seq.sortBy(identity)
    seq.sorted

    // Before:
    // seq.sorted.reverse
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    seq.sorted(Ordering[Int].reverse)

    // Before:
    // seq.sorted.head
    // seq.sorted.last
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    seq.min
    seq.max

    // Before: seq.reduce(_ + _) OR seq.fold(42)(_ + _)
    seq.sum
    seq.sum + 42

    // Before: seq.reduce(_ * _) OR seq.fold(42)(_ + _)
    seq.product
    seq.product * 42

    // Before:
    // seq.filter(_ > 10).filter(> _ 20)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    seq.filter(x => ((x > 10) && (x < 20)))

    // Before:
    // seq.map(foo).map(gaz)
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    seq.map(foo.andThen(gaz))

    // Before:
    // seq.sorted.filter(_ > 10)
    //
    // Sorting is O(n * log n), so reducing the size of n will greatly improve performance.
    //
    seq.filter(_ > 10).sorted

    // Before:
    // seq.map(foo).reverse
    //
    // This call avoids creating an intermediate collection, and therefore avoids using heap space or running GC.
    //
    seq.reverseMap(foo)

    // Before:
    // seq.drop(5).take(6)
    //
    // More concise, but for IndexedSeq also results in significant performance improvements.
    //
    seq.slice(5, 5 + 6)

    // Before:
    // val seqA = seq.take(n)
    // val seqB = seq.drop(n)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    var (seqA, seqB) = seq.splitAt(x)

    // Before:
    // val seqA = seq.filter(gaz)
    // val seqB = seq.filterNot(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    val (seqC, seqD) = seq.partition(gaz)

    // Before:
    // val seqA = seq.takeWhile(gaz)
    // val seqB = seq.dropWhile(gaz)
    //
    // Single-pass call instead of doing two passes to split list.
    //
    val (seqE, seqF) = seq.span(gaz)

    // Before: seq.map(bar)
    seq.foreach(bar)

    // Before:
    // seq :+ x
    // x +: seq
    // seqA ++ seqB
    // seqB ++ seqA
    seq :+= x
    seq +:= x
    seqA ++= seqB
    seqA ++:= seqB

    // Before:
    // seq.toSeq
    //
    // Certain 'Seq' derived classes will return lazy or unexpected collections. If possible, you should explicitly
    // declare what type of collection you're looking to create.
    //
    seq.toStream
    seq.toVector
  }

  class Dummy(val x: Int)

  val d1: Dummy
  val d2: Dummy
  val d3: Dummy

  private def foo(i: Int): Int = i

  private def bar(i: Int): Unit = {}

  private def gaz(i: Int): Boolean = true
}
