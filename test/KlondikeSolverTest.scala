import java.util.UUID

import akka.testkit.TestActorRef
import org.specs2._

class KlondikeSolverTest extends Specification {
  override def is = s2"""
  This tests possible moves for Klondike, using a known seed.
    Game should start: ${gameId.toString}
  """

  val gameId = UUID.randomUUID()

  val seed = 0
}
