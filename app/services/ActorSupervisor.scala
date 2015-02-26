package services

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{OneForOneStrategy, Props, SupervisorStrategy}
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.{InstrumentedActor, MetricsServletActor}

object ActorSupervisor extends Logging {
  def start() {
    import play.api.Play.current
    val instance = Akka.system.actorOf(Props[ActorSupervisor], "actor-supervisor")
    log.info("Actor Supervisor [" + instance.path.toString + "] started for [scalataire].")
  }
}

private class ActorSupervisor extends InstrumentedActor with Logging {
  override def receiveRequest = {
    case x => log.info("ActorSupervisor encountered unknown message: " + x.toString)
  }

  override def preStart() {
    context.actorOf(Props[MetricsServletActor], "metrics-servlet")
  }

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ => Stop
  }
}
