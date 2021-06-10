package com.ashelkov.scala.lang.types

/**
 * Suppose we want a function that takes in an array of anything and prints each member one by one.
 */
object ExistentialTypes {

  /**
   * This function definition won't work, because `Array` is not covariant (e.g. an `Array[String]` is not an
   * `Array[Any]`, even though `String` is `Any`).
   */
  def func1(a: Array[Any]): Unit = a.foreach(println)

  /**
   * This function definition will work, but it parameterizes the function. This means that we now need to determine if
   * our function should be covariant, contravariant, or invariant. It also means that if the compiler has trouble
   * inferring the type, it will need to be explicitly provided.
   */
  def func2[T <: Any](a: Array[T]): Unit = a.foreach(println)

  /**
   * This function is the definition that we're looking for. It declares that the input will be an array of some type,
   * but that we don't care what type, so long as it derives from `Any`.
   */
  def func3(a: Array[T] forSome { type T <: Any }): Unit = a.foreach(println)

  /**
   * @param a "a can have any type such that that type exists", i.e. a can have any type (identical to `a: Any`).
   * @param b "b can have any type such that that type derives from `Number`", i.e. b can be a `Number` or any child
   *          class (identical to 'b: Number`).`
   * @param c "c is an array of some type which is a type such that that type exists", i.e. c is an array of any type
   *          (identical to `c: Array[Any]`).
   * @param d "d can have any type such that that type is an Array of a type that exists".
   */
  def example(
    a: T forSome { type T },
    b: T forSome { type T <: Number },
    c: Array[U forSome { type U } ],
    d: Array[V] forSome { type V }): Unit = {}

}
