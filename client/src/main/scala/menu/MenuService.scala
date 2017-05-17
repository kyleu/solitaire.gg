package menu

import navigation.{NavigationService, NavigationState}
import org.scalajs.jquery.{jQuery => $}
import settings.SettingsService
import utils.TemplateUtils

class MenuService(settings: SettingsService, navigation: NavigationService) {
  private[this] var priorState: NavigationState = NavigationState.Loading

  private[this] val title = $("#nav-title")
  if (title.length != 1) {
    throw new IllegalStateException(s"Found [${title.length}] menu titles.")
  }
  TemplateUtils.clickHandler(title, jq => toggleMenu())

  private[this] val toggle = $("#menu-toggle")
  if (toggle.length != 1) {
    throw new IllegalStateException(s"Found [${toggle.length}] menu toggles.")
  }
  TemplateUtils.clickHandler(toggle, jq => toggleMenu())

  NavigationState.values.foreach { s =>
    TemplateUtils.clickHandler($(s"#menu-link-$s"), jq => navigation.navigate(s))
  }

  def toggleMenu() = if (navigation.getState == NavigationState.Menu) {
    navigation.navigate(priorState)
  } else {
    priorState = navigation.getState
    navigation.navigate(NavigationState.Menu)
  }
}
