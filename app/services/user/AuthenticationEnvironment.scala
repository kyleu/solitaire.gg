package services.user

import com.mohiva.play.silhouette.api.util.{PlayHTTPLayer, Clock}
import com.mohiva.play.silhouette.api.{Provider, EventBus, Environment}
import com.mohiva.play.silhouette.impl.authenticators.{SessionAuthenticatorService, SessionAuthenticatorSettings, SessionAuthenticator}
import com.mohiva.play.silhouette.impl.services.GravatarService
import com.mohiva.play.silhouette.impl.util.DefaultFingerprintGenerator
import models.user.User

object AuthenticationEnvironment extends Environment[User, SessionAuthenticator] {
  private[this] val config = play.api.Play.current.configuration

  private[this] val fingerprintGenerator = new DefaultFingerprintGenerator(false)

  override val identityService = UserService

  private[this] val httpLayer = new PlayHTTPLayer

  override val providers: Map[String, Provider] = new SocialAuthProviders(config, httpLayer).providers

  val avatarService = new GravatarService(httpLayer)

  override val eventBus = EventBus()

  override val authenticatorService = {
    new SessionAuthenticatorService(SessionAuthenticatorSettings(
      sessionKey = config.getString("silhouette.authenticator.sessionKey").get,
      encryptAuthenticator = config.getBoolean("silhouette.authenticator.encryptAuthenticator").get,
      useFingerprinting = config.getBoolean("silhouette.authenticator.useFingerprinting").get,
      authenticatorIdleTimeout = config.getInt("silhouette.authenticator.authenticatorIdleTimeout"),
      authenticatorExpiry = config.getInt("silhouette.authenticator.authenticatorExpiry").get
    ), fingerprintGenerator, Clock())
  }
}
