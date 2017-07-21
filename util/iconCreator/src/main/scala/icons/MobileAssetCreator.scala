package icons

import better.files._

import scala.sys.process._

object MobileAssetCreator {
  case class Out(f: String, width: Int, height: Int, isSplash: Boolean = false)

  def log(msg: String) = println(msg)

  val srcDir = "." / "resources" / "design"
  val tmpDir = "." / "tmp" / "media"

  private[this] val icons = Seq(
    Out("android/icon-ldpi.png", 36, 36),
    Out("android/icon-mdpi.png", 48, 48),
    Out("android/icon-hdpi.png", 72, 72),
    Out("android/icon-xhdpi.png", 96, 96),
    Out("android/icon-xxhdpi.png", 144, 144),
    Out("android/icon-xxxhdpi.png", 192, 192),

    Out("firefoxos/logo.png", 60, 60),

    Out("ios/icon-60.png", 60, 60),
    Out("ios/icon-60@2x.png", 120, 120),
    Out("ios/icon-60@3x.png", 180, 180),
    Out("ios/icon-76.png", 76, 76),
    Out("ios/icon-76@2x.png", 152, 152),
    Out("ios/icon-40.png", 40, 40),
    Out("ios/icon-40@2x.png", 80, 80),
    Out("ios/icon.png", 57, 57),
    Out("ios/icon@2x.png", 114, 114),
    Out("ios/icon-72.png", 72, 72),
    Out("ios/icon-72@2x.png", 144, 144),
    Out("ios/icon-small.png", 29, 29),
    Out("ios/icon-small@2x.png", 58, 58),
    Out("ios/icon-50.png", 50, 50),
    Out("ios/icon-50@2x.png", 100, 100),
    Out("ios/icon-83.5@2x.png", 167, 167)
  )

  private[this] val splashes = Seq(
    Out("android/splash-landscape-ldpi.png", 320, 200, isSplash = true),
    Out("android/splash-landscape-mdpi.png", 480, 320, isSplash = true),
    Out("android/splash-landscape-hdpi.png", 800, 480, isSplash = true),
    Out("android/splash-landscape-xhdpi.png", 1280, 720, isSplash = true),
    Out("android/splash-landscape-xxhdpi.png", 1600, 960, isSplash = true),
    Out("android/splash-landscape-xxxhdpi.png", 1920, 1280, isSplash = true),

    Out("android/splash-portrait-ldpi.png", 200, 320, isSplash = true),
    Out("android/splash-portrait-mdpi.png", 320, 480, isSplash = true),
    Out("android/splash-portrait-hdpi.png", 480, 800, isSplash = true),
    Out("android/splash-portrait-xhdpi.png", 720, 1280, isSplash = true),
    Out("android/splash-portrait-xxhdpi.png", 960, 1600, isSplash = true),
    Out("android/splash-portrait-xxxhdpi.png", 1280, 1920, isSplash = true),

    Out("ios/splash-iphone.png", 320, 480, isSplash = true),
    Out("ios/splash-iphone@2x.png", 640, 960, isSplash = true),
    Out("ios/splash-ipad-portrait.png", 768, 1024, isSplash = true),
    Out("ios/splash-ipad-portrait@2x.png", 1536, 2048, isSplash = true),
    Out("ios/splash-ipad-landscape.png", 1024, 768, isSplash = true),
    Out("ios/splash-ipad-landscape@2x.png", 2048, 1536, isSplash = true),
    Out("ios/splash-iphone5.png", 640, 1136, isSplash = true),
    Out("ios/splash-iphone6.png", 750, 1334, isSplash = true),
    Out("ios/splash-iphone6-plus-portrait.png", 1242, 2208, isSplash = true),
    Out("ios/splash-iphone6-plus-landscape.png", 2208, 1242, isSplash = true)
  )

  private[this] def process(o: MobileAssetCreator.Out, bg: File, icon: File) = {
    log(s"Exporting [${o.f}]...")

    val outDir = tmpDir / "working"
    outDir.createIfNotExists(asDirectory = true)

    outDir.children.foreach(_.delete())
    val cropped = outDir / "cropped.png"
    s"convert ${bg.path} -crop ${o.width}x${o.height}+0+0 ${cropped.path}".!!

    val minArg = if (o.width < o.height) { o.width } else { o.height }
    val logoSize = (minArg * 0.8).toInt
    val logo = outDir / "icon.png"
    val args = "--export-dpi=200 --export-background-opacity=0 --without-gui"
    s"inkscape --export-png=${logo.path} $args --export-width=$logoSize --export-height=$logoSize ${icon.path}".!!

    val f = tmpDir / o.f
    f.createIfNotExists(createParents = true)
    s"composite ${logo.path} ${cropped.path} -background none -gravity center ${f.path}".!!
  }

  def main(args: Array[String]): Unit = {
    val startMs = System.currentTimeMillis
    tmpDir.createIfNotExists(asDirectory = true, createParents = true)
    log("Creating mobile app media...")

    val bg = srcDir / "background.png"
    val icon = srcDir / "icon-white.svg"

    icons.foreach(out => process(out, bg, icon))
    splashes.foreach(out => process(out, bg, icon))

    log(s"Completed creating mobile app media in [${System.currentTimeMillis - startMs}ms].")
  }
}
