package services

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{OneForOneStrategy, Props, SupervisorStrategy}
import play.api.Logger
import play.api.libs.concurrent.Akka
import utils.metrics.{InstrumentedActor, MetricsServletActor}

object ActorSupervisor {
  def start() = {
    import play.api.Play.current
    val instance = Akka.system.actorOf(Props[ActorSupervisor], "actor-supervisor")
    Logger.info("Actor Supervisor [" + instance.path.toString + "] started for [scalataire].")
  }
}

private class ActorSupervisor extends InstrumentedActor  {
  override def receiveRequest = {
    case x => Logger.info("ActorSupervisor encountered unknown message: " + x.toString)
  }

  override def preStart() = {
    context.actorOf(Props[MetricsServletActor], "metrics-servlet")
  }

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ => Stop
  }
}
