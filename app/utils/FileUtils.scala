package utils

import java.io.File

object FileUtils {
  private[this] def listRawFiles(f: File) = if (f.isDirectory) {
    f.listFiles().toSeq
  } else {
    throw new IllegalStateException(s"File [${f.getPath}] is not a directory.")
  }

  def listFiles(f: File) = listRawFiles(f).filter(_.isFile).sortBy(_.getName)
  def listDirectories(f: File) = listRawFiles(f).filter(_.isDirectory).sortBy(_.getName)

  def getChildDir(root: File, name: String, createIfNeeded: Boolean = true) = {
    if ((!root.exists) || (!root.isDirectory)) {
      throw new IllegalStateException(s"File [$name] cannot be created, as parent [${root.getPath}] is not a directory.")
    }
    val f = new File(root, name)
    if (!f.exists && createIfNeeded) {
      f.mkdir()
    }
    f
  }
}
