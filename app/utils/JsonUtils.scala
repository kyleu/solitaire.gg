package utils

import scala.io.Source

object JsonUtils {
  def cleanJson(json: String) = {
    var previousLine = ""

    Source.fromString(json).getLines().zipWithIndex.map { l =>
      val split = l._1.split(":")
      if (split.length > 1) {
        val value = split.drop(1).mkString(":")
        var fixed = value.trim()

        if(fixed.contains("// ")) {
          fixed = fixed.substring(0, fixed.indexOf("// "))
        }

        if(fixed.endsWith(", },")) {
          fixed = fixed.substring(0, fixed.length - 4) + " },"
        }

        fixed = fixed.replaceAllLiterally("\"", "\\\"").replaceAllLiterally("\\'", "XXXXX").replaceAllLiterally("'", "\"").replaceAllLiterally("XXXXX", "'")

        if(!fixed.startsWith("\"") && fixed.exists(c => (c >= 'A' && c <= 'Z') || c == 'x' || c == '|' || c == '&' || c == '*')) {
          if(fixed.endsWith("},")) {
            fixed = "\"" + fixed.substring(0, fixed.length - 2) + "\"},"
          } else if(fixed.endsWith(",")) {
            fixed = "\"" + fixed.substring(0, fixed.length - 1) + "\","
          } else {
            fixed = "\"" + fixed + "\""
          }
        }

        previousLine = fixed

        split(0) + ": " + fixed
      } else {
        if(l._1.startsWith("}") && previousLine.endsWith(",")) {
          "\"unused\": \"temp_hack\" " + l._1
        } else {
          l._1
        }
      }
    }.mkString("\n")
  }
}
