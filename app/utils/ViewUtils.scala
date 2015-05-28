package utils

import play.twirl.api.Html

object ViewUtils {
  def th(key: String, label: String, selected: String, link: Boolean = true) = {
    val ret = if (!link) {
      label
    } else if (selected == key) {
      label + " <span class=\"caret\"></span>"
    } else {
      "<a href=\"?sortBy=" + key + "\">" + label + "</a></th>"
    }
    Html("<th nowrap=\"nowrap\" class=\"th-" + key + "\">" + ret + "</th>")
  }
}
