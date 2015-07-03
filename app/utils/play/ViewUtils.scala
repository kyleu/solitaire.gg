package utils.play

import _root_.play.twirl.api.Html
import utils.cache.CacheService

object ViewUtils {
  def th(key: String, label: String, selected: String, link: Boolean = true) = {
    val ret = if (!link) {
      label
    } else if (selected == key) {
      s"""$label <span class="caret"></span>"""
    } else {
      s"""<a href="?sortBy=$key">$label</a></th>"""
    }
    Html(s"""<th nowrap="nowrap" class="th-$key">$ret</th>""")
  }

  def cachedTemplate(key: String, html: => Html) = CacheService.getTemplate(key) match {
    case Some(x) => x
    case None => CacheService.cacheTemplate(key, html)
  }
}
