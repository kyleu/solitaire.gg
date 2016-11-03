package screenshots

import java.nio.file.{Files, Paths}

import models.rules.GameRulesSet

object ScreenshotCreator {
  val outDir = Paths.get(".", "offline", "build", "screenshots")
  val baseUrl = "http://solitaire.local/play/"

  if (!Files.exists(outDir)) {
    throw new IllegalStateException(s"Directory [$outDir] does not exist.")
  }

  def processAll() = {
    val startMs = System.currentTimeMillis
    val set = GameRulesSet.all
    println(s"Creating [${set.size}] screenshots...")
    for (rules <- set) {
      if (hasAllScreenshots(rules.id)) {
        println(s"Skipping already-generated screenshots for [${rules.id}].")
      } else {
        processRules(rules.id)
      }
    }
    println(s"Screenshot creation complete in [${System.currentTimeMillis - startMs}ms].")
    s"Ok, rendered [${set.size}] screenshots."
  }

  def processRules(rules: String) = {
    import scala.sys.process._

    println(s"  Creating screenshots for [$rules]...")
    val startMs = System.currentTimeMillis

    "killall Safari".!

    s"open -a Safari".!
    Thread.sleep(1000)

    s"./util/screenshotCreator/bin/safari-set-url.sh $baseUrl$rules".!!
    Thread.sleep(7000)

    val windowId = "./util/screenshotCreator/bin/safari-window-id.sh".!!.trim
    println(s"Using window id [$windowId].")

    s"./util/screenshotCreator/bin/safari-resize.sh 0 0 1280 782".!!
    Thread.sleep(1000)
    s"screencapture -l$windowId -o -x $outDir/$rules-hd-browser.png".!!
    s"convert $outDir/$rules-hd-browser.png -crop +0+124 +repage $outDir/$rules-hd.png".!!

    s"./util/screenshotCreator/bin/safari-resize.sh 0 0 900 662".!!
    Thread.sleep(1000)
    s"screencapture -l$windowId -o -x $outDir/$rules-landscape-browser.png".!!
    s"convert $outDir/$rules-landscape-browser.png -crop +0+124 +repage $outDir/$rules-landscape.png".!!

    s"./util/screenshotCreator/bin/safari-resize.sh 0 0 400 662".!!
    Thread.sleep(1000)
    s"screencapture -l$windowId -o -x $outDir/$rules-portrait-browser.png".!!
    s"convert $outDir/$rules-portrait-browser.png -crop +0+124 +repage $outDir/$rules-portrait.png".!!

    println(s"  Screenshots complete for [$rules] in [${System.currentTimeMillis - startMs}ms].")

    s"Ok, rendered a screenshot for [$rules]."
  }

  private[this] def hasAllScreenshots(id: String) = {
    val hd = outDir.resolve(s"$id-hd.png")
    val l = outDir.resolve(s"$id-landscape.png")
    val p = outDir.resolve(s"$id-portrait.png")
    Files.exists(hd) && Files.exists(l) && Files.exists(p)
  }
}
