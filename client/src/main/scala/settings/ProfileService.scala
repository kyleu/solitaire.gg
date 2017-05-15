package settings

import java.util.UUID

import msg.rsp.Profile
import org.scalajs.dom

object ProfileService {
  private val deviceKey = "solitaire.gg.device"
}

class ProfileService(settingsService: SettingsService) {

  private[this] var profile: Option[Profile] = None
  private[this] val localUserId = UUID.randomUUID

  val deviceId = Option(dom.window.localStorage.getItem(ProfileService.deviceKey)) match {
    case Some(id) => UUID.fromString(id)
    case None =>
      val id = UUID.randomUUID
      dom.window.localStorage.setItem(ProfileService.deviceKey, id.toString)
      id
  }

  def getUserId = profile.map(_.userId).getOrElse(localUserId)
  def getUsername = profile.flatMap(_.username)

  def setProfile(p: Profile) = {
    settingsService.applyAndSave(p.settings)
    ThemeService.applyColorAndPattern(p.settings.backgroundColor, p.settings.backgroundPattern)
    profile = Some(p)
  }
}
