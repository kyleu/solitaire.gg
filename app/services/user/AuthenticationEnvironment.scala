package services.user

import com.mohiva.play.silhouette.api.util.{PlayHTTPLayer, Clock}
import com.mohiva.play.silhouette.api.{EventBus, Environment}
import com.mohiva.play.silhouette.impl.authenticators.{SessionAuthenticatorService, SessionAuthenticatorSettings, SessionAuthenticator}
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import com.mohiva.play.silhouette.impl.services.{DelegableAuthInfoService, GravatarService}
import com.mohiva.play.silhouette.impl.util.{BCryptPasswordHasher, DefaultFingerprintGenerator}
import models.user.User

object AuthenticationEnvironment extends Environment[User, SessionAuthenticator] {
  private[this] val config = play.api.Play.current.configuration

  private[this] val fingerprintGenerator = new DefaultFingerprintGenerator(false)

  override val identityService = UserService

  private[this] val httpLayer = new PlayHTTPLayer

  val hasher = new BCryptPasswordHasher()

  val authInfoService = new DelegableAuthInfoService(PasswordInfoService, OAuth1InfoService, OAuth2InfoService)

  val credentials = new CredentialsProvider(authInfoService, hasher, Seq(hasher))

  private val sap = new SocialAuthProviders(config, httpLayer, hasher, authInfoService, credentials)
  val providersSeq = sap.providers
  override val providers = sap.providers.toMap

  val avatarService = new GravatarService(httpLayer)

  override val eventBus = EventBus()

  override val authenticatorService = new SessionAuthenticatorService(SessionAuthenticatorSettings(
    sessionKey = config.getString("silhouette.authenticator.sessionKey").getOrElse(throw new IllegalArgumentException()),
    encryptAuthenticator = config.getBoolean("silhouette.authenticator.encryptAuthenticator").getOrElse(throw new IllegalArgumentException()),
    useFingerprinting = config.getBoolean("silhouette.authenticator.useFingerprinting").getOrElse(throw new IllegalArgumentException()),
    authenticatorIdleTimeout = config.getInt("silhouette.authenticator.authenticatorIdleTimeout"),
    authenticatorExpiry = config.getInt("silhouette.authenticator.authenticatorExpiry").getOrElse(throw new IllegalArgumentException())
  ), fingerprintGenerator, Clock())
}
