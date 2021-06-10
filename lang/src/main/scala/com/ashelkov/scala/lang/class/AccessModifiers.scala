package com.ashelkov.scala.lang.`class`

sealed trait AccessModifiers

// Accessible from anywhere
class Public extends AccessModifiers

// Alias for 'protected[class]'
protected class Protected extends AccessModifiers

// Accessible from anywhere in com.ashelkov.scala.lang
protected[lang] class Package extends AccessModifiers

// Accessible only from current package
private class Private extends AccessModifiers

// Alias for 'protected[lang]'
private[lang] class PackagePrivate extends AccessModifiers



class Example extends AccessModifiers {

  // Accessible from anywhere
  val a: Int = 1
  // Accessible from derived classes only
  protected val b: Int = 2
  // Accessible from derived classes, or any class in package 'com.ashelkov.scala.lang'
  protected[lang] val c: Int = 3
  // Accessible from class only
  private val d: Int = 4
  // Accessible from 'com.ashelkov.scala.lang' only
  private[lang] val e: Int = 5
  // Accessible from this instance of the object only
  private[this] val f: Int = 6
}
