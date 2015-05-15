package services.user

import com.mohiva.play.silhouette.api.util.{ PlayHTTPLayer, Clock }
import com.mohiva.play.silhouette.api.{ EventBus, Environment }
import com.mohiva.play.silhouette.impl.authenticators._
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import com.mohiva.play.silhouette.impl.services.{ DelegableAuthInfoService, GravatarService }
import com.mohiva.play.silhouette.impl.util.{ PlayCacheLayer, SecureRandomIDGenerator, BCryptPasswordHasher, DefaultFingerprintGenerator }
import models.user.User

object AuthenticationEnvironment extends Environment[User, CookieAuthenticator] {
  private[this] val fingerprintGenerator = new DefaultFingerprintGenerator(false)

  override val identityService = UserService

  private[this] val httpLayer = new PlayHTTPLayer

  val hasher = new BCryptPasswordHasher()

  val idGenerator = new SecureRandomIDGenerator()

  val clock = Clock()

  val authInfoService = new DelegableAuthInfoService(PasswordInfoService, OAuth1InfoService, OAuth2InfoService)

  val credentials = new CredentialsProvider(authInfoService, hasher, Seq(hasher))

  private val sap = new SocialAuthProviders(play.api.Play.current.configuration, httpLayer, hasher, authInfoService, credentials, idGenerator, clock)
  val providersSeq = sap.providers
  override val providers = sap.providers.toMap

  val avatarService = new GravatarService(httpLayer)

  val cache = new PlayCacheLayer

  override val eventBus = EventBus()

  override val authenticatorService = {
    val cfg = play.api.Play.current.configuration.getConfig("silhouette.authenticator.cookie").getOrElse {
      throw new IllegalArgumentException("Missing cookie configuration.")
    }
    new CookieAuthenticatorService(CookieAuthenticatorSettings(
      cookieName = cfg.getString("name").getOrElse(throw new IllegalArgumentException()),
      cookiePath = cfg.getString("path").getOrElse(throw new IllegalArgumentException()),
      cookieDomain = None,
      secureCookie = cfg.getBoolean("secure").getOrElse(throw new IllegalArgumentException()),
      httpOnlyCookie = true,
      useFingerprinting = true,
      cookieMaxAge = cfg.getInt("maxAge"),
      authenticatorIdleTimeout = cfg.getInt("idleTimeout"),
      authenticatorExpiry = cfg.getInt("expiry").getOrElse(throw new IllegalArgumentException())
    ), SessionInfoService, fingerprintGenerator, idGenerator, clock)
  }
  //  override val authenticatorService = {
  //    val cfg = play.api.Play.current.configuration.getConfig("silhouette.authenticator.session").getOrElse {
  //      throw new IllegalArgumentException("Missing session configuration.")
  //    }
  //    new SessionAuthenticatorService(SessionAuthenticatorSettings(
  //      sessionKey = cfg.getString("sessionKey").getOrElse(throw new IllegalArgumentException()),
  //      encryptAuthenticator = cfg.getBoolean("encryptAuthenticator").getOrElse(throw new IllegalArgumentException()),
  //      useFingerprinting = cfg.getBoolean("useFingerprinting").getOrElse(throw new IllegalArgumentException()),
  //      authenticatorIdleTimeout = cfg.getInt("authenticatorIdleTimeout"),
  //      authenticatorExpiry = cfg.getInt("authenticatorExpiry").getOrElse(throw new IllegalArgumentException())
  //    ), fingerprintGenerator, clock)
  //  }
}
