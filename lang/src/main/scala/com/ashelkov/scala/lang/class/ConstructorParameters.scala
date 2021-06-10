package com.ashelkov.scala.lang.`class`

/**
 * In general:
 *   - If you need a parameter at construction time, add it to the constructor
 *   - If you want it hidden from users, add it to the body
 *   - If both, add an unmodified parameter to the constructor and use it to initialize a hidden parameter in the body
 *
 * @param a Never used beyond object construction, so it's garbage collected away (not a parameter of the class)
 * @param b Used beyond object construction, so defaults to `private[this]`
 * @param c Public val with default getters and setters
 * @param d Private val, however still exposed to the world (even though its inaccessible)
 */
class ConstructorParameters(a: Int, b: Int, val c: Int, private val d: Int) {

  private val aa: Int = a;

  def print(): Unit = {
    println(aa)
    println(b)
  }

}
