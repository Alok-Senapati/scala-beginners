package com.learning.functions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.learning.functions.recursion.RecursionUnitTest

class RecursionTestSpec extends AnyFlatSpec with Matchers {
  "RecursionUnitTest.factorial(5)" should "Calculates Factorial of 5 that is 120" in {
    RecursionUnitTest.factorial(5) shouldEqual (120)
  }

  "RecursionUnitTest.factorial(1)" should "Calculates Factorial of 1 that is 1" in {
    RecursionUnitTest.factorial(1) shouldEqual (1)
  }
}
