// Copyright 2010-2014 Dire Wolf Digital, LLC. All rights reserved.
package utils.web

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import play.api.mvc._

object RoutingImports {
  private val dateFormat = "yyyy-MM-dd"
  private val dateFormatter = DateTimeFormat.forPattern(dateFormat)

  implicit object queryStringLocalDateBinder extends QueryStringBindable.Parsing[LocalDate](
    dateString => dateFormatter.parseLocalDate(dateString),
    date => dateFormatter.print(date),
    (key: String, e: Exception) => s"Cannot parse parameter $key as org.joda.time.LocalDate: ${e.getMessage}"
  )

  implicit object pathLocalDateBinder extends PathBindable.Parsing[LocalDate](
    dateString => dateFormatter.parseLocalDate(dateString),
    date => dateFormatter.print(date),
    (key: String, e: Exception) => s"Cannot parse parameter $key as org.joda.time.LocalDate: ${e.getMessage}"
  )
}
