package services.test

import models.test.{ Tree, TestResult, Test }
import utils.Logging

object TestService extends Logging {
  def run(tree: Tree[Test]): Tree[TestResult] = {
    val result = run(tree.node)
    val childResults = tree.children.map(run)
    Tree(result, childResults)
  }

  def run(test: Test): TestResult = {
    val startMs = System.currentTimeMillis
    try {
      val ret = test.run()
      TestResult(test.id, (System.currentTimeMillis - startMs).toInt, Some(ret))
    } catch {
      case x: Exception =>
        log.warn("Exception encountered processing test [" + test.id + "].", x)
        TestResult(test.id, (System.currentTimeMillis - startMs).toInt, None, Some(x))
    }
  }
}
