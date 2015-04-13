package services.test

trait SolverTests { this: TestService =>
  def testSolver() = runTest { () =>
    val solver = GameSolver("klondike", 0, Some(10))

    (0 to 1000).foreach { i =>
      solver.performMove()
    }
    val results = Seq(TestResult("Solver created."))
    TestResult("solver", results)
  }
}
