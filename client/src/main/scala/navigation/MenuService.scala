package navigation

import models.settings.SettingsService
import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

class MenuService(settings: SettingsService, navigation: NavigationService) {
  private[this] var priorState: NavigationState = NavigationState.Loading
  private[this] val toggle = $("#menu-toggle")
  if (toggle.length != 1) {
    throw new IllegalStateException(s"Found [${toggle.length}] menu toggles.")
  }

  NavigationState.values.foreach { s =>
    TemplateUtils.clickHandler($(s"#menu-link-$s"), jq => navigation.navigate(s))
  }

  TemplateUtils.clickHandler(toggle, jq => if (navigation.getState == NavigationState.Menu) {
    navigation.navigate(priorState)
  } else {
    priorState = navigation.getState
    navigation.navigate(NavigationState.Menu)
  })
}
