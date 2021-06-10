package com.ashelkov.scala.lang.types

object TypeHierarchy {

  class Dummy

  // Root of object hierarchy - all objects descend from Any
  var any: Any = 0

  // Root of Scala primitives
  var anyVal: AnyVal = 0
  // Double
  anyVal = 3.14D
  // Float
  anyVal = 3.14F
  // Long
  anyVal = 314L
  // Int
  anyVal = 314
  // Short
  anyVal = 314
  // Byte
  anyVal = 31
  // Char
  anyVal = 'a'
  // Boolean
  anyVal = true
  // Unit
  anyVal = new Unit

  // Root of class objects
  var anyRef: AnyRef = 0
  // Built-in class
  anyRef = Seq(1, 2, 3)
  // Custom classes
  anyRef = new Dummy

  // Null descends from all objects
  val seq: Seq[Int] = null
  val dummy: Dummy = null
  val i: Int = null

  any = anyVal
  any = anyRef
  any = seq
  any = dummy
  any = i
  any = null
}
