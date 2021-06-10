package com.ashelkov.scala.tips.misc

object ArrayMapWithIndex {

  def slowMapIndex[T, V](a: Array[T], f: (Int, T) => V): Array[V] = {
    a.zipWithIndex.map { case (t, i) => f(i, t) }
  }

  def fasterMapIndex[T, V](a: Array[T], f: (Int, T) => V) {
    for (i <- a.indices) yield f(i, a(i))
  }

  def fastestMapIndex[T, V](a: Array[T], f: (Int, T) => V) {
    Array.tabulate(a.length) { i => f(i, a(i)) }
  }

}
