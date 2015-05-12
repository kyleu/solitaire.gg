package services.user

import com.mohiva.play.silhouette.api.util.{HTTPLayer, Clock}
import com.mohiva.play.silhouette.impl.providers.oauth1.TwitterProvider
import com.mohiva.play.silhouette.impl.providers.oauth1.services.PlayOAuth1Service
import com.mohiva.play.silhouette.impl.providers.{CredentialsProvider, OAuth1Settings, OAuth2Settings}
import com.mohiva.play.silhouette.impl.providers.oauth1.secrets.{CookieSecretSettings, CookieSecretProvider}
import com.mohiva.play.silhouette.impl.providers.oauth2.{GoogleProvider, FacebookProvider}
import com.mohiva.play.silhouette.impl.providers.oauth2.state.{CookieStateSettings, CookieStateProvider}
import com.mohiva.play.silhouette.impl.services.DelegableAuthInfoService
import com.mohiva.play.silhouette.impl.util.{BCryptPasswordHasher, SecureRandomIDGenerator}
import play.api.Configuration

class SocialAuthProviders(config: Configuration, httpLayer: HTTPLayer) {
  private[this] val idGenerator = new SecureRandomIDGenerator()
  private[this] val authInfoService = new DelegableAuthInfoService(PasswordInfoService, OAuth1InfoService, OAuth2InfoService)
  private[this] val passwordHasher = new BCryptPasswordHasher()

  val credentials = new CredentialsProvider(authInfoService, passwordHasher, Seq(passwordHasher))

  private[this] val oAuth1TokenSecretProvider = {
    new CookieSecretProvider(CookieSecretSettings(
      cookieName = config.getString("silhouette.oauth1TokenSecretProvider.cookieName").get,
      cookiePath = config.getString("silhouette.oauth1TokenSecretProvider.cookiePath").get,
      cookieDomain = config.getString("silhouette.oauth1TokenSecretProvider.cookieDomain"),
      secureCookie = config.getBoolean("silhouette.oauth1TokenSecretProvider.secureCookie").get,
      httpOnlyCookie = config.getBoolean("silhouette.oauth1TokenSecretProvider.httpOnlyCookie").get,
      expirationTime = config.getInt("silhouette.oauth1TokenSecretProvider.expirationTime").get
    ), Clock())
  }

  private[this] val oAuth2StateProvider = {
    new CookieStateProvider(CookieStateSettings(
      cookieName = config.getString("silhouette.oauth2StateProvider.cookieName").get,
      cookiePath = config.getString("silhouette.oauth2StateProvider.cookiePath").get,
      cookieDomain = config.getString("silhouette.oauth2StateProvider.cookieDomain"),
      secureCookie = config.getBoolean("silhouette.oauth2StateProvider.secureCookie").get,
      httpOnlyCookie = config.getBoolean("silhouette.oauth2StateProvider.httpOnlyCookie").get,
      expirationTime = config.getInt("silhouette.oauth2StateProvider.expirationTime").get
    ), idGenerator, Clock())
  }

  private[this] val facebook = {
    FacebookProvider(httpLayer, oAuth2StateProvider, OAuth2Settings(
      authorizationURL = config.getString("silhouette.facebook.authorizationURL"),
      accessTokenURL = config.getString("silhouette.facebook.accessTokenURL").get,
      redirectURL = config.getString("silhouette.facebook.redirectURL").get,
      clientID = config.getString("silhouette.facebook.clientID").getOrElse(""),
      clientSecret = config.getString("silhouette.facebook.clientSecret").getOrElse(""),
      scope = config.getString("silhouette.facebook.scope")
    ))
  }

  private[this] val google = {
    GoogleProvider(httpLayer, oAuth2StateProvider, OAuth2Settings(
      authorizationURL = config.getString("silhouette.google.authorizationURL"),
      accessTokenURL = config.getString("silhouette.google.accessTokenURL").get,
      redirectURL = config.getString("silhouette.google.redirectURL").get,
      clientID = config.getString("silhouette.google.clientID").getOrElse(""),
      clientSecret = config.getString("silhouette.google.clientSecret").getOrElse(""),
      scope = config.getString("silhouette.google.scope")
    ))
  }

  private[this] val twitter = {
    val settings = OAuth1Settings(
      requestTokenURL = config.getString("silhouette.twitter.requestTokenURL").get,
      accessTokenURL = config.getString("silhouette.twitter.accessTokenURL").get,
      authorizationURL = config.getString("silhouette.twitter.authorizationURL").get,
      callbackURL = config.getString("silhouette.twitter.callbackURL").get,
      consumerKey = config.getString("silhouette.twitter.consumerKey").getOrElse(""),
      consumerSecret = config.getString("silhouette.twitter.consumerSecret").getOrElse("")
    )
    TwitterProvider(httpLayer, new PlayOAuth1Service(settings), oAuth1TokenSecretProvider, settings)
  }

  val providers = Map("local" -> credentials, "facebook" -> facebook, "google" -> google, "twitter" -> twitter)
}
