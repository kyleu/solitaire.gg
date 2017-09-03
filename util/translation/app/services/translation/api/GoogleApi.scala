package services.translation.api

import java.net.URLEncoder

import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global

@javax.inject.Singleton
class GoogleApi @javax.inject.Inject() (ws: WSClient) extends ApiProvider("Yandex") {
  def url(lang: String, text: String) = {
    val t = URLEncoder.encode(text, "utf8")
    s"https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=$lang&dt=t&q=$t"
  }

  override def translate(lang: String, key: String, text: String) = {
    ws.url(url(lang, text)).get().map { response =>
      val content = response.body
      val startIndex = content.indexOf('"') + 1
      val result = content.substring(startIndex, content.indexOf('"', startIndex + 1))
      Some(key -> result)
    }
  }
}
