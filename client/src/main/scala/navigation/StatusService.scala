package navigation

object StatusService {
  case class Status(topBar: Boolean, gameName: Option[String])

  object Status {
    val default = Status(topBar = true, gameName = None)
  }

  private[this] var currentStatus = Status.default

  def apply(status: Status) = if (currentStatus == status) {
    utils.Logging.info("No changes, skipping status update.")
  } else {
    currentStatus = status
  }
}
