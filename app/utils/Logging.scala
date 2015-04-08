package utils

import play.api.Logger

trait Logging {
  protected[this] val log = Logger("scalataire." + this.getClass.getSimpleName.replace("$", ""))
}
