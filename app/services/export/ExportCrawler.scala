package services.export

import better.files._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class ExportCrawler(ws: WSClient, baseUrl: String, outPath: File, debug: Boolean) {
  private[this] val prodAssets = Seq(
    "assets/stylesheets/gg.min.css",
    "assets/lib/jquery/jquery.min.js",
    "assets/lib/phaser/phaser-arcade-physics.min.js",
    "assets/lib/colorpicker/colorpicker.min.js",
    "assets/lib/materializecss/fonts/roboto/Roboto-Light.woff",
    "assets/lib/materializecss/fonts/roboto/Roboto-Light.woff2",
    "assets/lib/materializecss/fonts/roboto/Roboto-Thin.woff",
    "assets/lib/materializecss/fonts/roboto/Roboto-Thin.woff2",
    "assets/client-opt.js"
  )

  private[this] val debugAssets = Seq(
    "assets/stylesheets/gg.min.css",
    "assets/stylesheets/phaser-debug.min.css",
    "assets/lib/jquery/jquery.js",
    "assets/lib/phaser/phaser-debug.js",
    "assets/lib/phaser/phaser-arcade-physics.js",
    "assets/lib/colorpicker/colorpicker.js",
    "assets/lib/materializecss/fonts/roboto/Roboto-Light.woff",
    "assets/lib/materializecss/fonts/roboto/Roboto-Light.woff2",
    "assets/lib/materializecss/fonts/roboto/Roboto-Thin.woff",
    "assets/lib/materializecss/fonts/roboto/Roboto-Thin.woff2",
    "assets/client-fastopt.js"
  )

  private[this] val folders = Seq(
    "public/audio" -> "assets/audio",
    "public/images" -> "assets/images"
  )

  def get(path: String) = ws.url(baseUrl + path).get().map {
    case x if x.status != 200 => throw new IllegalStateException(s"Status [${x.status}:${x.statusText}] from [$path].")
    case x =>
      val f = outPath / path
      f.createIfNotExists(asDirectory = false, createParents = true)
      Seq(f.writeByteArray(x.bodyAsBytes.toArray))
  }

  val assets = if (debug) { debugAssets } else { prodAssets }

  def crawlLocal() = assets.foldLeft(Future.successful(Seq.empty[File])) { (x, y) =>
    x.flatMap { f =>
      get(y).map(f ++ _)
    }
  }.map { ret =>
    folders.foreach { folder =>
      val d = folder._1.toFile
      if (!d.isDirectory) {
        throw new IllegalStateException(s"Missing folder [$folder].")
      }
      val o = outPath / folder._2
      o.createIfNotExists(asDirectory = true, createParents = true)
      d.copyTo(o, overwrite = true)
    }
    ret
  }
}
