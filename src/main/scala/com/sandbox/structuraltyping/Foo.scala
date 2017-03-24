package com.sandbox.structuraltyping

object Foo extends App {
  type Bar = {
    def gimmeAnInt(): Int
  }

  def printAnInt(bar: Bar): Unit = println(bar.gimmeAnInt())

  /** GimmeAOne and GimmeATwo are different types and don't extend a common superclass.
    * But we can pass them to the same method! Using this pattern, Scala allows for
    * "structural typing" -- the analog in a statically typed language to what would be
    * called duck-typing in a dynamically typed language. The compiler actually checks
    * to make sure that the types passed to printAnInt have the gimmeAnInt method.
    */
  printAnInt(new GimmeAOne) // prints 1
  printAnInt(new GimmeATwo) //prints 2
}
