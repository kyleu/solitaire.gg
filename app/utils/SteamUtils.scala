package utils

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.openid.SteamProvider
import com.mohiva.play.silhouette.impl.providers._
import com.mohiva.play.silhouette.impl.providers.openid.services.PlayOpenIDService
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, JsObject, Json}
import play.api.libs.ws.WS
import play.api.mvc.Request

import scala.concurrent.Future

object SteamUtils {
  private[this] lazy val apiKey = play.api.Play.current.configuration.getString("silhouette.steam.apiKey").getOrElse(throw new IllegalArgumentException())

  def steamService(steamSettings: OpenIDSettings) = new PlayOpenIDService(steamSettings) {
    override def verifiedID[B](implicit request: Request[B]): Future[OpenIDInfo] = {
      super.verifiedID.flatMap { info =>
        SteamUtils.getProfile(info.id).map { profile =>
          info.copy(attributes = profile)
        }
      }
    }
  }

  trait SteamProfileBuilder extends CommonSocialProfileBuilder { self: SteamProvider =>
    val profileParser = new SocialProfileParser[OpenIDInfo, CommonSocialProfile] {
      override def parse(info: OpenIDInfo) = {
        val loginInfo = LoginInfo("steam", info.id)
        Future.successful(CommonSocialProfile(
          loginInfo = loginInfo,
          firstName = info.attributes.get("username"),
          fullName = info.attributes.get("fullName"),
          avatarURL = info.attributes.get("avatar")
        ))
      }
    }
  }

  def getProfile(id: String) = {
    val url = "https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + apiKey + "&steamids=" + id
    WS.url(url).get().map { response =>
      var ret = Seq.empty[(String, String)]
      val json = Json.parse(response.body)
      val content = (json \ "response").asOpt[JsObject].flatMap(x => (x \ "players").asOpt[JsArray].flatMap(_.value.headOption)).flatMap(_.asOpt[JsObject])
      content.foreach { info =>
        info.value.get("personaname").foreach { x =>
          ret = ret :+ ("username" -> x.as[String])
        }
        info.value.get("avatarfull").foreach { x =>
          ret = ret :+ ("avatar" -> x.as[String])
        }
        info.value.get("realname").foreach { x =>
          ret = ret :+ ("fullName" -> x.as[String])
        }
      }
      ret.toMap
    }
  }
}
