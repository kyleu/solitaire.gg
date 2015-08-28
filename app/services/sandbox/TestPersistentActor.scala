package services.sandbox

import akka.persistence.{ SnapshotOffer, PersistentActor }
import utils.Logging

object TestPersistentActor {
  case class Cmd(data: String)
  case class Evt(data: String)

  case class ExampleState(events: List[String] = Nil) {
    def updated(evt: Evt): ExampleState = copy(evt.data :: events)
    def size: Int = events.length
    override def toString: String = events.reverse.toString()
  }
}

class TestPersistentActor extends PersistentActor with Logging {
  import services.sandbox.TestPersistentActor._

  override def persistenceId = "test-actor-1"
  log.info(s"Test actor started.")

  var state = ExampleState()
  private def updateState(event: Evt) = state = state.updated(event)
  def numEvents = state.size

  override def receiveCommand = {
    case Cmd(data) =>
      log.info(s"Test actor received [$data].")
      persistAsync(Evt(data))(updateState)
    case "snap" => saveSnapshot(state)
    case "print" => println(state)
  }

  override def receiveRecover = {
    case evt: Evt => updateState(evt)
    case SnapshotOffer(_, snapshot: ExampleState) => state = snapshot
    case x => log.info(s"Received recover [$x].")
  }
}
