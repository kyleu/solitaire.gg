package services.database

import services.file.FileService
import util.DateUtils

import scala.sys.process._

object BackupRestore {
  private[this] val backupRoot = FileService.getDir("backup")

  def backup() = {
    val filename = s"${util.Config.projectId}-backup-${DateUtils.today}.sql"
    val f = new java.io.File(backupRoot, filename)
    if (f.exists) {
      throw new IllegalStateException(s"File [$filename] already exists.")
    }

    val zipFilename = filename + ".gz"
    val zf = new java.io.File(backupRoot, zipFilename)
    if (zf.exists) {
      throw new IllegalStateException(s"File [$zipFilename] already exists.")
    }

    val path = backupRoot.getAbsolutePath + "/" + filename
    val dumpResult = Seq("pg_dump", "-Fp", "-h", "localhost", "-O", "-f", path, "-d", "solitaire").!!
    if (!f.exists) {
      throw new IllegalStateException(s"Backup to file [$filename] failed: [$dumpResult].")
    }

    val gzipResult = Seq("gzip", path).!!
    if (!zf.exists) {
      throw new IllegalStateException(s"Compression of file [$zipFilename] failed: [$gzipResult].")
    }

    zipFilename
  }

  def restore(key: String) = {
    key
  }
}
