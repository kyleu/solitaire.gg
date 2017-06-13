package services.export

import better.files._
import models.settings.Language
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class ExportCrawler(ws: WSClient, baseUrl: String, outPath: File, debug: Boolean) {
  private[this] val langAssets = Language.values.filterNot(_ == Language.English).map { l =>
    s"strings.js?l=${l.value}" -> s"lang/strings.${l.value}.js"
  }

  private[this] val prodAssets = Seq(
    "strings.js",
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
    "strings.js",
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

  def get(path: String, output: Option[String] = None) = ws.url(baseUrl + path).get().map {
    case x if x.status != 200 => throw new IllegalStateException(s"Status [${x.status}:${x.statusText}] from [$path].")
    case x =>
      val f = outPath / output.getOrElse(path)
      f.createIfNotExists(asDirectory = false, createParents = true)
      Seq(f.writeByteArray(x.bodyAsBytes.toArray))
  }

  val assets = if (debug) { debugAssets } else { prodAssets }

  def crawlLocal() = {
    val langF = langAssets.foldLeft(Future.successful(Seq.empty[File])) { (x, y) =>
      x.flatMap { f =>
        get(y._1, Some(y._2)).map(f ++ _)
      }
    }
    val assetsF = assets.foldLeft(Future.successful(Seq.empty[File])) { (x, y) =>
      x.flatMap { f =>
        get(y).map(f ++ _)
      }
    }
    assetsF.flatMap(x => langF.map(y => x ++ y)).map { ret =>
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
}
