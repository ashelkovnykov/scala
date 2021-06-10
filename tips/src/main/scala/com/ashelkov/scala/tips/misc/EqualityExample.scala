package com.ashelkov.scala.tips.misc

object EqualityExample {

  //
  // Incorrect
  //

  /**
   * At first glance, this version appears to be okay. The problem is that it does not override the standard `equals`
   * method. Instead, it creates an overloaded alternative. Overloading is resolved using static types, not run-time
   * types. So long as the static type of an object is `BadEquals1`, this method will be called. However, once the
   * static type is `Object`, or some other parent type, `Object.equals` is called instead. That method has not been
   * overriden and therefore still compares equality using object identity. Therefore, any two references to identical
   * but separate objects will return false.
   *
   * @param x Some member
   * @param y Some other member
   */
  class BadEquals1(val x: Int, val y: Int) {

    def equals(other: BadEquals1): Boolean = {
      (this.x == other.x) && (this.y == other.y)
    }

  }

  /**
   * This version is better, however it may still fail in certain cases. For example, if you create two identical
   * `BadEquals2` objects, add one to a `HashSet`, and check for the other, the result could be either true or false.
   * This is because `equals` has been redefined without also redefining `hashCode`. When computing the hash of a
   * `BadEquals2` object, `Object.hashCode` will be used. The default implementation uses the object address to produce
   * the hash, which will almost certainly not match for two separate objects. This class violates the
   * "HashCode-Equality Contract", which states that if two objects are equal according to `equals`, they must also
   * produce identical results from the `hashCode` method.
   *
   * @param x Some member
   * @param y Some other member
   */
  class BadEquals2(val x: Int, val y: Int) {

    override def equals(obj: Any): Boolean = obj match {
      case other: BadEquals2 => (this.x == other.x) && (this.y == other.y)
      case _ => false
    }

  }

  /**
   * This version is better than the version above, but it defines `hashCode` in terms of mutable fields. Consider the
   * case where a new `BadEquals3` object is created, added to a `HashSet`, and then modified. This object would now
   * both be and not be a member of the set, because it's in the wrong hash bucket.
   *
   * @param x Some member
   * @param y Some other member
   */
  class BadEquals3(var x: Int, var y: Int) {

    override def equals(obj: Any): Boolean = obj match {
      case other: BadEquals3 => (this.x == other.x) && (this.y == other.y)
      case _ => false
    }

    override def hashCode(): Int = (41 * (41 + x)) + y

  }

  /**
   * This class is a base implementation of proper equality comparisons, but (as demonstrated below) it's not perfect.
   *
   * @param x Some member
   * @param y Some other member
   */
  class BaseEquals(val x: Int, val y: Int) {

    override def equals(obj: Any): Boolean = obj match {
      case other: BaseEquals => (this.x == other.x) && (this.y == other.y)
      case _ => false
    }

    override def hashCode(): Int = (41 * (41 + x)) + y

  }

  /**
   * This version appears to be correct:
   * - it overrides `equals` correctly
   * - it doesn't need to override `hashCode`, since member `z` is just a label
   *
   * However, the equality relation is not symmetrical. Suppose that there is a `BaseEquals` object "a" and a
   * `BadDerivedEquals` object "b". Suppose also that both have identical `x` and `y` values. The expression
   * `a.equals(b)` evaluates to true, but `b.equals(a)` evaluates to false: `a` is not a `BadDerivedEquals`.
   *
   * @param x Some member
   * @param y Some other member
   * @param z Some label
   */
  class BadDerivedEquals(override val x: Int, override val y: Int, z: String) extends BaseEquals(x, y) {

    override def equals(obj: Any): Boolean = obj match {
      case other: BadDerivedEquals => super.equals(obj) && this.z.equals(other.z)
      case _ => false
    }

  }

  //
  // Correct
  //

  /**
   * This definition introduces the additional requirement that classes explicitly specify whether or not they *can*
   * equal one another. That way, prior to doing any equality comparisons, the classes themselves can declare whether
   * it's even possible for them to be equal. This maintains the symmetry of their equality.
   *
   * Additional equality tips:
   * - Every non-final class should that overrides `equals` should have a `canEqual` method
   * - Any derived class that updates `equals` must update `canEqual`
   * - `canEqual` should always yield true for instances of the same class
   * - To calculate the hashcode, add 41 to the first field's hashcode, then multiply by 41 and add the second field's
   * hashcode, and so on
   * - If `equals` calls `super.equals`, start with `super.hashCode` as your base instead of the first field
   * - If hashcode computation affects performance, cache computed hashcodes
   *
   * @param x Some member
   * @param y Some other member
   */
  class CorrectBaseEquals(val x: Int, val y: Int) {

    override def equals(obj: Any): Boolean = obj match {
      case other: CorrectBaseEquals => other.canEqual(this) && (this.x == other.x) && (this.y == other.y)
      case _ => false
    }

    override def hashCode(): Int = (41 * (41 + x)) + y

    def canEqual(obj: Any): Boolean = obj match {
      case _: CorrectBaseEquals => true
      case _ => false
    }
  }

  /**
   * @param x Some member
   * @param y Some other member
   * @param z Some label
   */
  class CorrectDerivedEquals(override val x: Int, override val y: Int, z: String) extends CorrectBaseEquals(x, y) {

    override def equals(obj: Any): Boolean = obj match {
      case other: CorrectDerivedEquals => other.canEqual(this) && this.z.equals(other.z) && super.equals(obj)
      case _ => false
    }

    override def canEqual(obj: Any): Boolean = obj match {
      case _: CorrectDerivedEquals => true
      case _ => false
    }

  }

}
