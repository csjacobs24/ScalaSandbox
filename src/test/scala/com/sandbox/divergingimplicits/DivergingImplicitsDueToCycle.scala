package com.sandbox.divergingimplicits

import org.scalatest.FlatSpec

/** Uncommenting any of the method calls in this class will cause the failure described in the comments */
class DivergingImplicitsDueToCycle extends FlatSpec {

  implicit def fooOne(implicit x: Int): String = x.toString
  implicit def fooTwo(implicit x: String): Int = x.toInt

  def fooThree(implicit x: Int): Unit = {}
  def fooFour(implicit x: String): Unit = {}

  // The compiler sees that fooThree requires an implicit Int, looks to fooTwo to get its Int,
  // sees that an implicit String is required there, looks to fooOne to get its String, sees that
  // an implicit Int is required, looks to fooTwo to get its Int, and gives up because it's in a
  // cycle that will never end.
  "fooThree" should "fail with 'diverging implicit expansion for type String starting method fooOne' " in {
    assertDoesNotCompile("fooThree")
    //  fooThree
  }

  // Similarly, the compiler sees that fooFour requires an implicit String, looks to fooOne to get its
  // String, sees that an implicit Int is required, looks to fooTwo to get its Int, sees that an implicit
  // String is required, looks to fooOne to get its String, and gives up because it's in a cycle.
  "fooFour" should "fail with 'diverging implicit expansion for type Int starting method fooTwo' " in {
    assertDoesNotCompile("fooFour")
    //  fooFour
  }

  // Here's an even dumber way to do this. The compiler sees that fooFive needs an implicit Double,
  // looks to fooFive to provide it, sees that an implicit Double is required there, looks to fooFive
  // to provide it...
  implicit def fooFive(implicit x: Double): Double = x
  "fooFive" should "fail with 'diverging implicit expansion for type Double starting with method fooFive' " in {
    assertDoesNotCompile("fooFive")
    //  fooFive
  }

}
