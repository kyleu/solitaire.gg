package screenshots

import java.nio.file.{ Files, Paths }

import models.game.rules.GameRulesSet

object ScreenshotCreator {
  val outDir = Paths.get(".", "offline", "build", "screenshots")
  val baseUrl = "http://solitaire.dev/play/"

  if(!Files.exists(outDir)) {
    throw new IllegalStateException(s"Directory [$outDir] does not exist.")
  }

  def processAll() = {
    val startMs = System.currentTimeMillis
    val set = GameRulesSet.all
    println(s"Creating [${set.size}] screenshots...")
    for(rules <- set) {
      processRules(rules.id)
    }
    println(s"Screenshot creation complete in [${System.currentTimeMillis - startMs}ms].")
    s"Ok, rendered [${set.size}] screenshots."
  }

  def processRules(rules: String) = {
    import scala.sys.process._

    println(s"  Creating screenshot for [$rules]...")
    val startMs = System.currentTimeMillis

    "killall Safari".!
    s"open -a Safari".!
    Thread.sleep(1000)

    s"./util/screenshotCreator/bin/safari-set-url.sh $baseUrl$rules".!!
    Thread.sleep(10000)

    val windowId = "./util/screenshotCreator/bin/safari-window-id.sh".!!.trim
    println(s"Using window id [$windowId].")

    s"screencapture -l$windowId -o -x $outDir/$rules-browser.png".!!
    println(s"  Screenshot complete for [$rules] in [${System.currentTimeMillis - startMs}ms].")

    s"Ok, rendered a screenshot for [$rules]."
  }
}
