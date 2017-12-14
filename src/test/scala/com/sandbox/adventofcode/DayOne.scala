package com.sandbox.adventofcode

import org.scalatest.{FlatSpec, Matchers}

class DayOne extends FlatSpec with Matchers {

  behavior of "Part One Puzzle"

  it should "map 1122 to 3" in {
    partOnePuzzle("1122") shouldBe 3
  }

  it should "map 12345 to 0" in {
    partOnePuzzle("12345") shouldBe 0
  }

  it should "map 13571 to 1" in {
    partOnePuzzle("13571") shouldBe 1
  }

  it should "map 11398846209873342309482 to 12" in {
    partOnePuzzle("11398846209873342309482") shouldBe 12
  }

  it should "map 113988462098733423094821 to 13" in {
    partOnePuzzle("113988462098733423094821") shouldBe 13
  }

  it should "map 111111 to 6" in {
    partOnePuzzle("111111") shouldBe 6
  }

  behavior of "Part Two Puzzle"

  it should "map 1212 to 6" in {
    partTwoPuzzle("1212") shouldBe 6
  }

  it should "map 1221 to 0" in {
    partTwoPuzzle("1221") shouldBe 0
  }

  it should "map 123425 to 4" in {
    partTwoPuzzle("123425") shouldBe 4
  }

  it should "map 123123 to 12" in {
    partTwoPuzzle("123123") shouldBe 12
  }

  it should "map 12131415 to 4" in {
    partTwoPuzzle("12131415") shouldBe 4
  }

  /** For a bunch of digits, compute the sum of all digits whose following digit is equal to it. The list of digits
    * wraps, so 1121 gives 2: one for the first one, because it's followed by another 1, and one for the last one,
    * because its "following" digit (the first digit) is also a 1.
    */
  def partOnePuzzle(numberString: String): Int = {
    generalPuzzle(numberString, 1)
  }

  /** Same as part one, except now rather than checking whether each digit matches the one after it, we check whether
    * each digit matches the one half the number of digits down the line from it. 1212 gives 6 because there are 4
    * digits, and the first one matches the digit 2 down from it, the first two as well, the second 1, and the second 2
    * as well.
    */
  def partTwoPuzzle(numberString: String): Int = {
    val length = numberString.length
    require(length % 2 == 0)
    generalPuzzle(numberString, length / 2)
  }

  /** For a bunch of digits and an integer, compute the sum of all digits for which the digit that many down the line
    * is equal to it. The list of digits wraps, so howManyToSkip == 1, 1121 gives 2: one for the first one, because
    * it's followed by another 1, and one for the last one, because its "following" digit (the first digit) is also a 1.
    * For howManyToSkip == 3, 123123 would give 12 because, with wrapping, every digit equals the one 3 down from it and
    * their sum is 12.
    */
  def generalPuzzle(numberString: String, howManyToSkip: Int): Int = {
    // To simulate the wrapping, append the first howManyToSkip to the end of the array
    val digits = numberString ++ numberString.take(howManyToSkip)
    digits.sliding(howManyToSkip + 1).filter(digitPair => digitPair.head == digitPair.last).map(_(0).toString.toInt).sum
  }
}
