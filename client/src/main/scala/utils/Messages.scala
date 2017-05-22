package utils

import scala.scalajs.js
import scala.scalajs.js.annotation.JSBracketAccess

object Messages {
  @js.native
  trait MessageObject extends js.Object {
    @JSBracketAccess
    def apply(key: String): Any = js.native
  }

  lazy val jsMessages = {
    val ret = js.Dynamic.global.messages.asInstanceOf[MessageObject]
    if (ret == None.orNull) {
      throw new IllegalStateException("Missing localization object [messages].")
    }
    ret
  }

  def apply(s: String, args: Any*) = {
    val msg = Option(jsMessages(s)) match {
      case Some(x) => x.toString match {
        case "undefined" => s
        case y => y
      }
      case None => s
    }
    args.zipWithIndex.foldLeft(msg) { (x, y) =>
      x.replaceAllLiterally(s"{${y._2}}", y._1.toString)
    }
  }

  def numberAsString(i: Int, properCase: Boolean = false) = {
    val ret = i match {
      case 0 => this("general.number.zero")
      case 1 => this("general.number.one")
      case 2 => this("general.number.two")
      case 3 => this("general.number.three")
      case 4 => this("general.number.four")
      case 5 => this("general.number.five")
      case 6 => this("general.number.six")
      case 7 => this("general.number.seven")
      case 8 => this("general.number.eight")
      case 9 => this("general.number.nine")
      case 10 => this("general.number.ten")
      case 11 => this("general.number.eleven")
      case 12 => this("general.number.twelve")
      case _ => i.toString
    }
    if (properCase) {
      ret.head.toUpper + ret.tail
    } else {
      ret
    }
  }

}
