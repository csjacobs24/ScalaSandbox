package com.sandbox.structuraltyping

import org.scalatest.FunSuite

// Import this if you don't want compiler warnings.  Note that "reflective" here is still at compile-time.
// However, due to the shittiness of the JVM, it still incurs a runtime performance penalty http://www.scala-lang.org/old/node/6834
// However, if you dismiss structural typing for that reason alone I will come and beat you with a copy of "The Art of Computer Programming"
// until you demonstrate to me that the performance penalty makes a noticeable difference.
import language.reflectiveCalls

/** Using this pattern, Scala allows for "structural typing" -- the analog in a statically
  * typed language to what would be called duck-typing in a dynamically typed language. The
  * compiler actually checks to make sure that the types passed to printAnInt have the gimmeAnInt
  * method.
  */
class TestStructuralTyping extends FunSuite {

  test("Structural typing") {
    type Bar = {
      def gimmeAnInt(): Int
    }

    def getAnInt(bar: Bar): Int = bar.gimmeAnInt

    // GimmeAOne and GimmeATwo are different types and don't extend a common superclass.
    // But we can pass them both to getAnInt because both have the required gimmeAnInt method.
    assert(getAnInt(new GimmeAOne) == 1)
    assert(getAnInt(new GimmeATwo) == 2)

    // The GimmeAThree class doesn't have a gimmeAnInt method, so if you pass it to getAnInt,
    // the code won't compile.
    assertDoesNotCompile("getAnInt(new GimmeAThree)")
  }
}
