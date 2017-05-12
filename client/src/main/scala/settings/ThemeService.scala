package settings

import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $}

object ThemeService {
  private[this] var lastColor = ""
  private[this] var lastPattern = ""

  def applyColor(color: String) = {
    val hex = if (color.startsWith("#")) { color } else { "#" + color }
    if (lastColor != hex) {
      lastColor = hex
      updateStyle()
    }
  }

  def applyPattern(pattern: String) = {
    if (lastPattern != pattern) {
      lastPattern = pattern
      updateStyle()
    }
  }

  private[this] def styleBlock() = Seq(
    s"body { background-color: $lastColor; }",
    if (lastPattern == "none") { "" } else { s"body { background-image: url(/assets/images/background/$lastPattern.png); }" },
    s".theme { background-color: $lastColor; }",
    s".theme-text { color: $lastColor; }"
  ).mkString("\n")

  private[this] def updateStyle() = {
    utils.Logging.info(s"Updating style [$lastColor / $lastPattern}].")
    val styleEl = $("#theme-style")
    if (styleEl.length > 0) {
      styleEl.remove()
    }

    $("<style id=\"theme-style\">" + styleBlock() + "</style>").appendTo(dom.document.head)
  }
}
