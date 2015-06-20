package models.database

import com.github.mauricio.async.db.RowData

class Row(val rowData: RowData) {
  def asOpt[T](s: String) = rowData(s) match {
    case null => None
    case x => Some(x.asInstanceOf[T])
  }

  def as[T](s: String) = rowData(s) match {
    case null => throw new IllegalArgumentException(s"Column [$s] is null.")
    case x => x.asInstanceOf[T]
  }
}
