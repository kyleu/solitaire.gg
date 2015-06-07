package services.user

import com.mohiva.play.silhouette.api.util.{ PlayHTTPLayer, Clock }
import com.mohiva.play.silhouette.api.{ RequestProvider, EventBus, Environment }
import com.mohiva.play.silhouette.impl.authenticators._
import com.mohiva.play.silhouette.impl.providers.{ BasicAuthProvider, CredentialsProvider }
import com.mohiva.play.silhouette.impl.repositories.DelegableAuthInfoRepository
import com.mohiva.play.silhouette.impl.services.GravatarService
import com.mohiva.play.silhouette.impl.util.{ SecureRandomIDGenerator, BCryptPasswordHasher, DefaultFingerprintGenerator }
import models.user.User

object AuthenticationEnvironment extends Environment[User, CookieAuthenticator] {
  override implicit val executionContext = play.api.libs.concurrent.Execution.Implicits.defaultContext

  private[this] val fingerprintGenerator = new DefaultFingerprintGenerator(false)

  override val identityService = UserService

  private[this] val httpLayer = new PlayHTTPLayer

  val hasher = new BCryptPasswordHasher()

  val idGenerator = new SecureRandomIDGenerator()

  val clock = Clock()

  val authInfoService = new DelegableAuthInfoRepository(PasswordInfoService, OAuth1InfoService, OAuth2InfoService, OpenIdInfoService)

  val credentials = new CredentialsProvider(authInfoService, hasher, Seq(hasher))

  private[this] val sap = new SocialAuthProviders(play.api.Play.current.configuration, httpLayer, hasher, authInfoService, credentials, idGenerator, clock)

  val authProvider = new BasicAuthProvider(authInfoService, hasher, Nil)

  override val requestProviders = Seq(authProvider)
  val providersSeq = sap.providers
  val providersMap = sap.providers.toMap

  val avatarService = new GravatarService(httpLayer)

  override val eventBus = EventBus()

  override val authenticatorService = {
    val cfg = play.api.Play.current.configuration.getConfig("silhouette.authenticator.cookie").getOrElse {
      throw new IllegalArgumentException("Missing cookie configuration.")
    }
    new CookieAuthenticatorService(CookieAuthenticatorSettings(
      cookieName = cfg.getString("name").getOrElse(throw new IllegalArgumentException()),
      cookiePath = cfg.getString("path").getOrElse(throw new IllegalArgumentException()),
      cookieDomain = cfg.getString("domain"),
      secureCookie = cfg.getBoolean("secure").getOrElse(throw new IllegalArgumentException()),
      httpOnlyCookie = true,
      useFingerprinting = cfg.getBoolean("useFingerprinting").getOrElse(throw new IllegalArgumentException()),
      cookieMaxAge = cfg.getInt("maxAge"),
      authenticatorIdleTimeout = cfg.getInt("idleTimeout"),
      authenticatorExpiry = cfg.getInt("expiry").getOrElse(throw new IllegalArgumentException())
    ), SessionInfoService, fingerprintGenerator, idGenerator, clock)
  }
}
