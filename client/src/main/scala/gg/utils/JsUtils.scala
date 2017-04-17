package gg.utils

import scala.scalajs.js

object JsUtils {
  def as[T <: js.Any](x: Any): T = x.asInstanceOf[T]
  def asDynamic(o: js.Any) = o.asInstanceOf[js.Dynamic]
}
