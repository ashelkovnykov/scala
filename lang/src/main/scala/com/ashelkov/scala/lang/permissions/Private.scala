package com.ashelkov.scala.lang.permissions

/**
 * Example class to demonstrate the difference between `private[this]` and `private`.
 *
 * @param abc Accessible only by methods from within the same object
 * @param ghi Accessible by methods of all objects with the same class type
 */
class Private(private[this] val abc: Int, private val ghi: Int) {

  override def equals(obj: Object): Boolean = obj match {
    case other: Private =>
      // Allowed
      val a = this.ghi == other.ghi

      // Not Allowed
      val b = this.abc == other.abc

      a && b

    case _ => false
  }
}
