package utils

import play.api.Logger

trait Logging {
  protected val log = Logger("scalataire." + this.getClass.getSimpleName.replace("$", ""))
}
