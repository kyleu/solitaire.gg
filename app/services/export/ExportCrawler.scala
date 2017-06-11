package services.export

import better.files.File
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class ExportCrawler(ws: WSClient, baseUrl: String, outPath: File) {
  private[this] val assets = Seq(
    "assets/stylesheets/gg.min.css",
    "assets/lib/jquery/jquery.min.js",
    "assets/lib/phaser/phaser-arcade-physics.min.js",
    "assets/lib/colorpicker/colorpicker.min.js"
  )

  def get(path: String): Future[Seq[File]] = {
    val f = outPath / path
    if (f.isDirectory) {
      Future.sequence(f.children.map(c => get(path + "/" + c.name)).toSeq).map(_.flatten)
    } else {
      ws.url(baseUrl + path).get().map {
        case x if x.status != 200 => throw new IllegalStateException(s"Status [${x.status}:${x.statusText}] from [$path].")
        case x =>
          f.createIfNotExists(asDirectory = false, createParents = true)
          Seq(f.writeByteArray(x.bodyAsBytes.toArray))
      }
    }
  }

  def crawlLocal() = {
    assets.foldLeft(Future.successful(Seq.empty[File])) { (x, y) =>
      x.flatMap { f =>
        get(y).map(f ++ _)
      }
    }
  }
}
