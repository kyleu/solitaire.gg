package services.test

import java.util.UUID

import models.test.{Test, TestResult, Tree}
import util.Logging

import scala.util.control.NonFatal

object TestService extends Logging {
  val testGameId = UUID.fromString("00000000-0000-0000-0000-000000000000")

  def run(tree: Tree[Test]): Tree[TestResult] = {
    val result = run(tree.node)
    val childResults = tree.children.map(run)
    Tree(result, childResults)
  }

  def run(test: Test): TestResult = {
    val startMs = System.currentTimeMillis
    try {
      val result = test.run()
      TestResult(test.id, (System.currentTimeMillis - startMs).toInt, Some(result))
    } catch {
      case x: Error =>
        log.warn(s"Error encountered processing test [${test.id}].", x)
        TestResult(test.id, (System.currentTimeMillis - startMs).toInt, None, Some(x))
      case NonFatal(x) =>
        log.warn(s"Exception encountered processing test [${test.id}].", x)
        TestResult(test.id, (System.currentTimeMillis - startMs).toInt, None, Some(x))
    }
  }
}
