package settings

import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $}

object ThemeService {
  private[this] var lastColor = ""
  private[this] var lastPattern: Option[String] = None

  def applyColor(color: String): Unit = {
    val hex = if (color.startsWith("#")) { color } else { "#" + color }
    if (lastColor != hex) {
      lastColor = hex
      updateStyle()
    }
  }

  def applyPattern(pattern: Option[String]): Unit = if (lastPattern != pattern) {
    lastPattern = pattern
    updateStyle()
  }

  private[this] def styleBlock() = Seq(
    s"body { background-color: $lastColor; }",
    lastPattern.map(p => s"body { background-image: url(/assets/images/background/$p.png); }").getOrElse(""),
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
