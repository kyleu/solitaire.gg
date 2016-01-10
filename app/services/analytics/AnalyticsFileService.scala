package services.analytics

import java.io.File

import org.joda.time.LocalDate
import utils.{ Config, FileUtils }

class AnalyticsFileService(cfg: Config) {
  private[this] lazy val rootDir = {
    val cacheDir = new File(cfg.fileCacheDir)
    if (!cacheDir.exists()) {
      throw new IllegalStateException(s"Cache directory [${cfg.fileCacheDir}] does not exist.")
    }
    FileUtils.getChildDir(cacheDir, "analytics")
  }

  def getFileSystemDetails = {
    val years = FileUtils.listDirectories(rootDir)
    val months = years.flatMap { yearDir =>
      FileUtils.listDirectories(yearDir).map(m => yearDir.getName -> m)
    }
    val days = months.flatMap { m =>
      FileUtils.listDirectories(m._2).map(d => (m._1, m._2.getName, d))
    }
    days.map { d =>
      val ld = new LocalDate(s"${d._1}-${d._2}-${d._3.getName}")
      val sizes = FileUtils.listFiles(d._3).map(f => f.getName -> f.length)
      ld -> sizes
    }
  }

  def dirFor(d: LocalDate) = {
    val f = FileUtils.getChildDir(rootDir, s"${d.getYear}/${d.getMonthOfYear}/${d.getDayOfMonth}")
    if (!f.exists()) {
      f.mkdirs()
    }
    f
  }

  def removeFiles(d: LocalDate) = {
    val files = FileUtils.listFiles(dirFor(d))
    files.foreach(_.delete())
    files.size
  }

  def removeAllFiles() = getFileSystemDetails.map(d => removeFiles(d._1))

  def getLogFile(d: LocalDate, filename: String) = new File(dirFor(d), filename)
}
