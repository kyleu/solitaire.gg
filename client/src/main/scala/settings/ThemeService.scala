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

  def applyColorAndPattern(color: String, pattern: Option[String]): Unit = {
    val hex = if (color.startsWith("#")) { color } else { "#" + color }
    if (lastColor != hex || lastPattern != pattern) {
      lastColor = hex
      lastPattern = pattern
      updateStyle()
    }
  }

  private[this] def styleBlock() = Seq(
    s"body { background-color: $lastColor; }",
    lastPattern.map(p => s"body { background-image: url(${navigation.NavigationService.assetRoot}images/background/$p.png); }").getOrElse(""),
    s".theme { background-color: $lastColor; }",
    s".theme-text { color: $lastColor; }"
  ).mkString("\n")

  private[this] def isBright = lastColor.stripPrefix("#") match {
    case x if x.length == 6 =>
      val rgb = Integer.parseInt(x, 16)
      val r = (rgb >> 16) & 0xff
      val g = (rgb >> 8) & 0xff
      val b = (rgb >> 0) & 0xff
      val luma = 0.2126 * r + 0.7152 * g + 0.0722 * b
      luma > 190
    case _ =>
      util.Logging.warn(s"Invalid color [$lastColor].")
      false
  }

  private[this] def updateStyle() = {
    //util.Logging.info(s"Updating style [$lastColor / $lastPattern}].")
    val styleEl = $("#theme-style")
    if (styleEl.length > 0) {
      styleEl.remove()
    }
    $("<style id=\"theme-style\">" + styleBlock() + "</style>").appendTo(dom.document.head)
    val body = $(dom.document.body)
    if (isBright) {
      if (body.hasClass("dark")) {
        body.removeClass("dark").addClass("bright")
      }
    } else {
      if (body.hasClass("bright")) {
        $(dom.document.body).removeClass("bright").addClass("dark")
      }
    }
  }
}
