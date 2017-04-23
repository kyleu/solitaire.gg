package navigation

import settings.SettingsService
import org.scalajs.jquery.{JQueryEventObject, jQuery => $}

class MenuService(settings: SettingsService, navigation: NavigationService) {
  private[this] var menuShown = false
  private[this] var priorState: NavigationService.State = NavigationService.State.Loading
  private[this] val toggle = $("#menu-toggle")
  if (toggle.length != 1) {
    throw new IllegalStateException(s"Found [${toggle.length}] menu toggles.")
  }

  toggle.on("click", (e: JQueryEventObject) => {
    menuShown = !menuShown
    if (menuShown) {
      priorState = navigation.getState
      navigation.navigate(NavigationService.State.Menu)
    } else {
      navigation.navigate(priorState)
    }
  })
}
