package com.sandbox.divergingimplicits

import org.scalatest.FlatSpec

/** Uncommenting the method call in this class will cause the failure in the test description */
class DivergingImplicitsDueToInfiniteExpansion extends FlatSpec {
  implicit def fooOne[T](implicit a: Set[T]): T = a.head

  def fooTwo(implicit x: Set[Int]): Unit = {}

  // The compiler sees that fooTwo needs an implicit Set[Int]. It sees that fooOne[Set[Int]]
  // can provide it, but needs an implicit Set[Set[Int]]. It then sees that fooOne[Set[Set[Int]]]
  // can provide a Set[Set[Int]], but needs an implicit Set[Set[Set[Int]]]. It gives up because
  // this can clearly go on forever.
  "fooTwo" should "fail with 'diverging implicit expansion for type Set[Int] starting with method fooOne' " in {
    assertDoesNotCompile("fooTwo")
    //  fooTwo
  }
}
