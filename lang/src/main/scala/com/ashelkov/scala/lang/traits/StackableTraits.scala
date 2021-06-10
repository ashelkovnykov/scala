package com.ashelkov.scala.lang.traits

import scala.collection.mutable.ListBuffer

object StackableTraits {

  /**
   * Abstract queue class.
   *
   * @tparam T Some type
   */
  abstract class Queue[T] {
    def put(t: T): Unit
    def get(): T
  }

  /**
   * Core implementation of queue for integers.
   */
  class IntQueue extends Queue[Int] {

    private val buffer = ListBuffer.empty[Int]

    override def put(t: Int): Unit = buffer.addOne(t)

    override def get(): Int = buffer.remove(0)

  }

  /**
   * Stackable trait which can modify behaviour of an `IntQueue`.
   */
  trait Doubling extends IntQueue {
    abstract override def put(t: Int): Unit = super.put(2 * t)
  }

  /**
   * Example function demonstrating how the stackable trait `Doubling` affects the behaviour of the queue.
   */
  def example(): Unit = {

    val q1 = new IntQueue
    val q2 = new IntQueue with Doubling

    q1.put(1)
    q2.put(1)

    println(q1.get() + " vs. " + q2.get())

  }

}
