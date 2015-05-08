package utils

import play.api.Logger

trait Logging {
  protected[this] val log = Logger("solitaire.gg." + this.getClass.getSimpleName.replace("$", ""))
}
