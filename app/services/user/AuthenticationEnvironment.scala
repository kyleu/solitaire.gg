package services.user

import com.mohiva.play.silhouette.api.util.{ PlayHTTPLayer, Clock }
import com.mohiva.play.silhouette.api.{ EventBus, Environment }
import com.mohiva.play.silhouette.impl.authenticators._
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import com.mohiva.play.silhouette.impl.services.{ DelegableAuthInfoService, GravatarService }
import com.mohiva.play.silhouette.impl.util.{ PlayCacheLayer, SecureRandomIDGenerator, BCryptPasswordHasher, DefaultFingerprintGenerator }
import models.user.User
import utils.Config

object AuthenticationEnvironment extends Environment[User, CookieAuthenticator] {
  private[this] val config = play.api.Play.current.configuration

  private[this] val fingerprintGenerator = new DefaultFingerprintGenerator(false)

  override val identityService = UserService

  private[this] val httpLayer = new PlayHTTPLayer

  val hasher = new BCryptPasswordHasher()

  val idGenerator = new SecureRandomIDGenerator()

  val clock = Clock()

  val authInfoService = new DelegableAuthInfoService(PasswordInfoService, OAuth1InfoService, OAuth2InfoService)

  val credentials = new CredentialsProvider(authInfoService, hasher, Seq(hasher))

  private val sap = new SocialAuthProviders(config, httpLayer, hasher, authInfoService, credentials, idGenerator, clock)
  val providersSeq = sap.providers
  override val providers = sap.providers.toMap

  val avatarService = new GravatarService(httpLayer)

  val cache = new PlayCacheLayer

  override val eventBus = EventBus()

  override val authenticatorService = new CookieAuthenticatorService(CookieAuthenticatorSettings(
    cookieName = config.getString("silhouette.authenticator.cookie.name").getOrElse(throw new IllegalArgumentException()),
    cookiePath = config.getString("silhouette.authenticator.cookie.path").getOrElse(throw new IllegalArgumentException()),
    cookieDomain = None,
    secureCookie = config.getBoolean("silhouette.authenticator.cookie.secure").getOrElse(throw new IllegalArgumentException()),
    httpOnlyCookie = true,
    useFingerprinting = true,
    cookieMaxAge = config.getInt("silhouette.authenticator.cookie.maxAge"),
    authenticatorIdleTimeout = config.getInt("silhouette.authenticator.cookie.idleTimeout"),
    authenticatorExpiry = config.getInt("silhouette.authenticator.cookie.expiry").getOrElse(throw new IllegalArgumentException())
  ), SessionInfoService, fingerprintGenerator, idGenerator, clock)

  //  override val authenticatorService = new SessionAuthenticatorService(SessionAuthenticatorSettings(
  //    sessionKey = config.getString("silhouette.authenticator.session.sessionKey").getOrElse(throw new IllegalArgumentException()),
  //    encryptAuthenticator = config.getBoolean("silhouette.authenticator.session.encryptAuthenticator").getOrElse(throw new IllegalArgumentException()),
  //    useFingerprinting = config.getBoolean("silhouette.authenticator.session.useFingerprinting").getOrElse(throw new IllegalArgumentException()),
  //    authenticatorIdleTimeout = config.getInt("silhouette.authenticator.session.authenticatorIdleTimeout"),
  //    authenticatorExpiry = config.getInt("silhouette.authenticator.session.authenticatorExpiry").getOrElse(throw new IllegalArgumentException())
  //  ), fingerprintGenerator, clock)
}
