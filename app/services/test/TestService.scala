package services.test

import utils.Logging

import scala.util.{ Failure, Success, Try }

class TestService extends Logging with AccountTests with KnownGameTests with RulesTests with SolverTests with VariantTests {
  case class TestResult(content: String, children: Seq[TestResult] = Nil) {
    def toString(indentionLevel: Int): String = {
      (0 to indentionLevel).map(x => "  ").mkString + content + "\n" + children.map(_.toString(indentionLevel + 1)).mkString
    }
  }

  protected[this] def runTest(f: () => TestResult) = {
    val startMs = System.currentTimeMillis()
    val ret = Try {
      f()
    }
    ret match {
      case Success(result) =>
        val msg = "Test [" + result.content + "] completed successfully in [" + (System.currentTimeMillis - startMs) + "ms]."
        log.debug(msg)
        result
      case Failure(ex) =>
        val msg = "Test failed with [" + ex.getClass.getSimpleName + "] in [" + (System.currentTimeMillis - startMs) + "ms]."
        log.warn(msg, ex)
        TestResult(msg, Seq(TestResult(ex.getMessage)))
    }
  }

  def testAll() = TestResult("Running all tests.", Seq(
    testAccount(),
    testKnownGame(),
    testSolvers(),
    testVariants(),
    testAllGameRules()
  ))
}
