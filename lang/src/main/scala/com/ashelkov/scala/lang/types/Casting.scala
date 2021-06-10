package com.ashelkov.scala.lang.types

object Casting {

  // Scala has the following unidirectional casting order
  val byte: Byte = 100
  val short: Short = byte
  val int: Int = short
  val long: Long = int
  val float: Float = long
  val double: Double = float

  val char: Char = 'a'
  val int2: Int = char

}
